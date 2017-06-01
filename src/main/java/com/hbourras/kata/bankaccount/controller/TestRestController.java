package com.hbourras.kata.bankaccount.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {
	
	@GetMapping("/")
	public String helloWorld() {
		return "Hamid BOURRAS - Bank Account Kata. Read README.txt file";
	}
}
