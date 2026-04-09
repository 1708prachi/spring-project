package com.eduhub.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class homecontroller {

	@GetMapping("/home")
	public String display() {
		return "welcome in spring boot web";
	}
}
