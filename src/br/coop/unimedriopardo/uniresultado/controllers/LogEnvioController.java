package br.coop.unimedriopardo.uniresultado.controllers;

import java.security.Principal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.coop.unimedriopardo.uniresultado.models.Usuario;
import br.coop.unimedriopardo.uniresultado.services.LogEnvioService;
import br.coop.unimedriopardo.uniresultado.services.UsuarioService;
import br.coop.unimedriopardo.uniresultado.util.ConversorDeData;


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
		model.addAttribute("logsEnvio",logEnvioService.listagemOrdenadaDoPrestador(usuarioLogado, new Date()));
		return "logEnvio.list.tiles";
	}
	
	@RequestMapping(value = "/pesquisar", method = RequestMethod.POST)
	public String consultar(@ModelAttribute("data") String data, Model model, Principal principal) {
		Usuario usuarioLogado = usuarioService.pesquisaPorLogin(principal.getName());
		model.addAttribute("logsEnvio",logEnvioService.listagemOrdenadaDoPrestador(usuarioLogado,new ConversorDeData().formatarStringParaData(data)));
		return "logEnvio.list.tiles";
	}
}
