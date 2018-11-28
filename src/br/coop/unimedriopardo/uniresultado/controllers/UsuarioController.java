package br.coop.unimedriopardo.uniresultado.controllers;

import java.awt.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.coop.unimedriopardo.uniresultado.models.Usuario;
import br.coop.unimedriopardo.uniresultado.services.PrestadorService;
import br.coop.unimedriopardo.uniresultado.services.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	private final UsuarioService usuarioService;
	private final PrestadorService prestadorService;

	@Autowired
	public UsuarioController(UsuarioService usuarioService,PrestadorService prestadorService) {
		this.usuarioService = usuarioService;
		this.prestadorService = prestadorService;
	}
	
	@RequestMapping("/formulario")
	public String form(Model model) {
		model.addAttribute("prestadores", prestadorService.listagemOrdenada());
		model.addAttribute("usuario", new Usuario());
		return "usuario.form.tiles";
	}
	
	@RequestMapping("/listagem")
	public String list(Model model) {
		model.addAttribute("usuarios",usuarioService.listagemOrdenada());
		return "usuario.list.tiles";
	}
}
