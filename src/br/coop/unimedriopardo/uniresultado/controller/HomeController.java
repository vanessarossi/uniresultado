package br.coop.unimedriopardo.uniresultado.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@RequestMapping("home")
	public String home() {
		return "home.index.tiles";
	}
	
	@RequestMapping("identificacao")
	public String login() {
		return "login.index.tiles";
	}
}
