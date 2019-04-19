package com.quality.project.password;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConfigurePasswordController {

	@RequestMapping("/configurepassword")
	public ModelAndView showConfigPassword(HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("configurepassword");
		
		IConfigurePassword configPassword = new ConfigurePassword();
		
		IPasswordParam password = configPassword.getPasswordConfig();
		
		if(password.isHavingUppercase()) {
			request.setAttribute("uppercasecheckvalue", "checked");
		}else {
			request.setAttribute("uppercasecheckvalue", "unchecked");
		}
		
		if(password.isHavingLowercase()) {
			request.setAttribute("lowercasecheckvalue", "checked");
		}else {
			request.setAttribute("lowercasecheckvalue", "unchecked");
		}
		
		if(password.isHavingNumber()) {
			request.setAttribute("numbercheckvalue", "checked");
		}else {
			request.setAttribute("numbercheckvalue", "unchecked");
		}
		
		if(password.isHavingSpecialCharacter()) {
			request.setAttribute("specialcharcheckvalue", "checked");
		}else {
			request.setAttribute("specialcharcheckvalue", "unchecked");
		}
		
		modelAndView.addObject(password);

		return modelAndView;
	}

	@PostMapping("/updateconfig")
	public ModelAndView updatePasswordConfig(HttpServletRequest request,
			@RequestParam int passwordLength,
			@RequestParam(required = false) String uppercase,
			@RequestParam(required = false) String lowercase,
			@RequestParam(required = false) String number,
			@RequestParam(required = false) String specialChar) {
		
		ModelAndView modelAndView = new ModelAndView();
		IPasswordParam password = new PasswordParam();
		IConfigurePassword configPassword = new ConfigurePassword();
		
		
		password.setMinLengthOfPassword(passwordLength);
		
		if(null != uppercase) {
			password.setIsHavingUppercase(true);
		}else {
			password.setIsHavingUppercase(false);
		}
		
		if(null != lowercase) {
			password.setIsHavingLowercase(true);
		}else {
			password.setIsHavingLowercase(false);
		}
		
		if(null != number) {
			password.setIsHavingNumber(true);
		}else {
			password.setIsHavingNumber(false);
		}
		
		if(null != specialChar) {
			password.setIsHavingSpecialCharacter(true);
		}else {
			password.setIsHavingSpecialCharacter(false);
		}
	
		if(configPassword.setPasswordConfig(password)) {
			modelAndView.setViewName("redirect:/home");
			return modelAndView;	
		}else {
			modelAndView.setViewName("redirect:/error");
			request.setAttribute("errorMessage", "Password update failed");
			return modelAndView;
		}
	}

}
