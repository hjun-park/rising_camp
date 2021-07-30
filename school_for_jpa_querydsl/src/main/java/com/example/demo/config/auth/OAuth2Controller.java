package com.example.demo.config.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuth2Controller {

	@GetMapping("/")
	public String helloWorld() {
		return "Hello World";
	}

	@GetMapping("/restricted")
	public String restricted() {
		return "to see this text you need to be logged in!";
	}

	@GetMapping("/test")
	public String test() {
		return "test!";
	}
}
