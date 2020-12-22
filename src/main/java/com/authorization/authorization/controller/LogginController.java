package com.authorization.authorization.controller;

import com.authorization.authorization.model.AuthenticateUser;
import com.authorization.authorization.model.User;
import com.authorization.authorization.repository.UserRepository;
import com.authorization.authorization.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LogginController {

	@Autowired
	private UserRepository repository;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public String login(@RequestBody User user) {
		// create a login and verify
		repository.save(user);
		return "user created sucessfully";
	}

	@PostMapping("/authenticate")
	public String validate(@RequestBody AuthenticateUser user, Model model) throws Exception {
		// validate the user and get the jwt token
		String token=null;
		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
		} catch (Exception e) {
			throw new Exception("Invalid user");
		}
		token = jwtUtil.generateToken(user.getUserName());
		if(token !=null) {
			model.addAttribute(token, token);
		}

		return token;
	}

	@GetMapping("/")
	public String welcomMessage() {
		return "welcome";
	}
}
