package com.quality.project.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.quality.project.password.ConfigurePassword;
import com.quality.project.password.IConfigurePassword;
import com.quality.project.password.IPasswordValidator;
import com.quality.project.password.PasswordValidator;

@Controller
public class RegistrationController {

	@RequestMapping("/register")
	public ModelAndView Register(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_REGISTER");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("registration");
		return modelAndView;
	}

	public String Registration(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_REGISTER");
		return null;
	}

	@PostMapping("/Register")
	public ModelAndView registerUser(HttpServletRequest request,
			@RequestParam String username, @RequestParam String userEmail,
			@RequestParam String userPhoneNumber,
			@RequestParam String userPassword,
			@RequestParam String userConfirmPassword) {
		ModelAndView modelAndView = new ModelAndView();
		boolean isValidForm = true;
		IUser user = new User();

		IConfigurePassword cofigPassword = new ConfigurePassword();
		IPasswordValidator pwValidator = new PasswordValidator();
		pwValidator.setPasswordParam(cofigPassword.getPasswordConfig());
		IRegistration registration = new Registration(pwValidator);

		username = username.trim();
		userEmail = userEmail.trim();
		userPhoneNumber = userPhoneNumber.trim();

		if (!registration.isValidUsername(username)) {
			isValidForm = false;
			request.setAttribute("INVALID_USERNAME", "Please enter username");
		}

		if (!registration.isValidEmail(userEmail)) {
			isValidForm = false;
			request.setAttribute("INVALID_USEREMAIL",
					"Please enter valid email ID");
		}

		if (!registration.isValidPhoneNumber(userPhoneNumber)) {
			isValidForm = false;
			request.setAttribute("INVALID_PHONENUMBER",
					"Please enter valid phone number");
		}

		if (!registration.isValidPassword(userPassword)) {
			isValidForm = false;
			request.setAttribute("INVALID_PASSWORD",
					registration.getPasswordConfigMsg());
		}

		user.setUsername(username);
		user.setUserEmailId(userEmail);
		user.setUserPassword(userPassword);
		user.setUserPhoneNumber(userPhoneNumber);

		if ((isValidForm) && (userPassword.equals(userConfirmPassword))) {
			boolean isUserRegistered = registration.register(user);

			if (isUserRegistered) {
				modelAndView.setViewName("redirect:/home");
			} else {
				modelAndView.setViewName("registration");
				request.setAttribute("mode", "MODE_REGISTER_USER_EXISTS");
			}
		} else {
			request.setAttribute("mode", "MODE_REGISTER");
			modelAndView.setViewName("registration");
			modelAndView.addObject("username", user.getUsername());
			modelAndView.addObject("userEmail", user.getUserEmailId());
			modelAndView.addObject("userPhoneNumber",
					user.getUserPhoneNumber());
			if (!userConfirmPassword.isEmpty()) {
				modelAndView.addObject("MODE_PASSWORD_DOES_NOT_MATCH",
						"Passwords does not match");
			}
		}
		return modelAndView;
	}
}
