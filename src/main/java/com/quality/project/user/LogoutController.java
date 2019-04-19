package com.quality.project.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.quality.project.session.ISessionHandler;
import com.quality.project.session.SessionHandler;

@RestController
public class LogoutController {
	@RequestMapping("/checkSession")
	public boolean checkSessionExists() {		
		ISessionHandler sessionHandler = SessionHandler.getSession();		
		return sessionHandler.isSessionStarted();
	}
	
	@RequestMapping("/logout")
	public ModelAndView logout() {	
		ISessionHandler sessionHandler = SessionHandler.getSession();		
		sessionHandler.endSession();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/home");
		return mav;
	}
}
