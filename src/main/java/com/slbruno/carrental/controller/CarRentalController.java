package com.slbruno.carrental.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cr/hello")
public class CarRentalController {

	@GetMapping
	public String Hello() {
		return "Hi, car rental ok, 4º message SonarCloud...";
	}
}
