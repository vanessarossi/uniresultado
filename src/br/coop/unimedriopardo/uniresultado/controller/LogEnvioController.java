package br.coop.unimedriopardo.uniresultado.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import br.coop.unimedriopardo.uniresultado.model.LogEnvio;
import br.coop.unimedriopardo.uniresultado.model.Usuario;
import br.coop.unimedriopardo.uniresultado.service.LogEnvioService;
import br.coop.unimedriopardo.uniresultado.service.UsuarioService;

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
	public String list() {
		return "logEnvio.list.tiles";
	}
	
	
	@GetMapping("/pesquisa")
	public @ResponseBody Page<LogEnvio> pesquisaLog(
			@RequestParam(
						value = "data",
						required = true) String data,
			@RequestParam(
					value = "status",
					required = true) String status,
            @RequestParam(
            		value = "page",
                    required = false,
                    defaultValue = "0") int page,
            @RequestParam(
                    value = "size",
                    required = false,
                    defaultValue = "20") int size,
            		Principal principal) {
		
		PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.DESC,"dataEnvio");
		Usuario usuarioLogado = usuarioService.pesquisaPorLogin(principal.getName());
		Page<LogEnvio> logs = null;
		if (status.equals("T")) {
			logs = logEnvioService.listagemOrdenadaDoPrestador(usuarioLogado, data, pageRequest);
		}else {
			logs = logEnvioService.listagemOrdenadaDoPrestadorPorStatus(usuarioLogado, status, data, pageRequest);
		}
		return logs;
	}
	
	@GetMapping("/pesquisa/id")
	public @ResponseBody LogEnvio pesquisaLogId(
			@RequestParam(
						value = "id",
						required = true) int id) {
		return logEnvioService.pequisarPorId(id);
	}
}
