package br.coop.unimedriopardo.uniresultado.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import br.coop.unimedriopardo.uniresultado.services.LogEnvioService;


@Controller
@RequestMapping("/logEnvio")
public class LogEnvioController {
	
	private final LogEnvioService logEnvioService;
	
	@Autowired
	public LogEnvioController(LogEnvioService logEnvioService) {
		this.logEnvioService = logEnvioService;
	}
	
	@RequestMapping("/listagem")
	public String list(Model model) {
		//model.addAttribute("logsEnvio", logEnvioService.listagemOrdenada());
		return "logEnvio.list.tiles";
	}

}
