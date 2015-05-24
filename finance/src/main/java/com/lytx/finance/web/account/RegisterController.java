package com.lytx.finance.web.account;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lytx.finance.service.UserService;
import com.lytx.finance.service.exception.UserExistsException;
import com.lytx.finance.service.exception.UserNotExistsException;
import com.lytx.finance.vo.User;

@Controller
@RequestMapping(value = "/register")
public class RegisterController {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String registerForm() {
		return "account/register";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String register(@Valid User user, RedirectAttributes redirectAttributes) {
		try {
			userService.registerUser(user);
			redirectAttributes.addFlashAttribute("username", user.getUsername());
			return "redirect:/login";
		} catch (UserExistsException e) {
			return "redirect:/";
		}
		
		
	}

	/**
	 * Ajax请求校验loginName是否唯一。
	 */
	@RequestMapping(value = "checkLoginName")
	@ResponseBody
	public String checkLoginName(@RequestParam("username") String username) {
		try {
			userService.getUserByName(username);
			return "true";
		} catch (UserNotExistsException e) {
			return "false";
		}
	}
}