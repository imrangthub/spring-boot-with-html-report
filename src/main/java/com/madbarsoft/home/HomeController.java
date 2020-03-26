package com.madbarsoft.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/home")
	public String getMsg() {

		System.out.println("From Home Controller");

		return "home";
	}

}
