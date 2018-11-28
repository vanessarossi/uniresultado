package br.coop.unimedriopardo.uniresultado.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@RequestMapping("home")
	public String home() {
		return "home.index.tiles";
	}
	
	@RequestMapping("login")
	public String login() {
		return "home.index.tiles";
	}
}
