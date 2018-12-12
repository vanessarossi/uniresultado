package br.coop.unimedriopardo.uniresultado.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.coop.unimedriopardo.uniresultado.models.Usuario;
import br.coop.unimedriopardo.uniresultado.services.LogEnvioService;
import br.coop.unimedriopardo.uniresultado.services.UsuarioService;


@Controller
@RequestMapping("/logEnvio")
public class LogEnvioController {
	
	private final LogEnvioService logEnvioService;
	private final UsuarioService usuarioService;
	
	@Autowired
	public LogEnvioController(LogEnvioService logEnvioService, UsuarioService usuarioService) {
		this.logEnvioService = logEnvioService;
		this.usuarioService = usuarioService;
	}
	
	@RequestMapping("/listagem")
	public String list(Model model, Principal principal) {
		Usuario usuarioLogado = usuarioService.pesquisaPorLogin(principal.getName());
		model.addAttribute("logsEnvio", logEnvioService.listagemOrdenadaDoPrestador(usuarioLogado));
		return "logEnvio.list.tiles";
	}

}
