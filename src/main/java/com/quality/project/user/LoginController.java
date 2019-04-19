package com.quality.project.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.quality.project.password.PasswordEncrypt;
import com.quality.project.properties.AdminProperties;

@Controller
public class LoginController {

	PasswordEncrypt encryptPassword = new PasswordEncrypt();

	@RequestMapping("/login")
	public ModelAndView Register(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_LOGIN");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@PostMapping("/login-user")
	public ModelAndView registerUser(HttpServletRequest request,
			@RequestParam String userEmail, @RequestParam String userPassword) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		request.setAttribute("mode", "MODE_LOGIN");
		String hashPass = PasswordEncrypt.hashPassword(userPassword);

		IUserLogin login = new UserLogin();
		IAdminLogin adminLogin = new AdminLogin();
		boolean isValidForm = true;

		userEmail = userEmail.trim();

		if (userEmail
				.equals(AdminProperties.getProperty("app.admin.username"))) {
			if (adminLogin.ifValidAdminSetInSession(userPassword)) {
				modelAndView.setViewName("redirect:/home");
			}
		} else {
			if (!login.isValidUserEmail(userEmail)) {
				isValidForm = false;
				request.setAttribute("INVALID_USEREMAIL",
						"Please enter valid email");
			}

			if (!login.isValidPassword(userPassword) && isValidForm) {
				isValidForm = false;
				request.setAttribute("INVALID_PASSWORD",
						"Please enter password");
			}

			if (isValidForm) {
				boolean isAlreadyUser = login.isValidUser(userEmail);

				if (isAlreadyUser) {
					boolean areValidCredentials = login.login(userEmail,
							hashPass);

					if (areValidCredentials) {
						modelAndView.setViewName("redirect:/home");
						return modelAndView;
					} else {
						modelAndView.addObject("username", userEmail);
						request.setAttribute("INVALID_CREDENTIALS",
								"Password is wrong, please enter valid password");
					}

				} else {
					request.setAttribute("NOT_EXISTING_USER",
							"User does not exist, please register first!");
				}
			} else {
				modelAndView.addObject("username", userEmail);
			}

			if (modelAndView.getViewName().equals("login")) {
				request.setAttribute("mode", "MODE_LOGIN");
			}
		}

		return modelAndView;
	}

}