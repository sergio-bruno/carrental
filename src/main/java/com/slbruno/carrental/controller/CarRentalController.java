package com.slbruno.carrental.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cr/cars")
public class CarRentalController {

	@GetMapping
	public String Hello() {
		return "Hi, car rental ok, second message SonarCloud...";
	}
}
