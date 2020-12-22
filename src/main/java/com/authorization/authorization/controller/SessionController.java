package com.authorization.authorization.controller;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {

	@GetMapping("/getSessionDetails")
	public String process(Model model, HttpSession session) {
		@SuppressWarnings("unchecked")
		List<String> messages = (List<String>) session.getAttribute("AuthSession");

		if (messages == null) {
			messages = new ArrayList<>();
		}
		model.addAttribute("sessionMessages", messages);

		return model.toString();
	}

	@PostMapping("/persistMessage")
	public String persistMessage(@RequestBody String token, HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		List<String> messages = (List<String>) request.getSession().getAttribute("AuthSession");
		if (messages == null) {
			messages = new ArrayList<>();
			request.getSession().setAttribute("AuthSession", messages);
		}
		messages.add(token);
		request.getSession().setAttribute("AuthSession", messages);
		return "redirect:/getSessionDetails";
	}

	@GetMapping("/destroy")
	public String destroySession(HttpServletRequest request) {
		request.getSession().invalidate();
		return "remove session time expiry or keep alive";
	}
}