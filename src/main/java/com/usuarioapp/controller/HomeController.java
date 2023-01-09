package com.usuarioapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {
	
	@GetMapping({"/index","/home","/"})
	public String index() {
		return "home";
	}

}
