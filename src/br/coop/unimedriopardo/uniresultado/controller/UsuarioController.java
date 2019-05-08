package br.coop.unimedriopardo.uniresultado.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.coop.unimedriopardo.uniresultado.model.Usuario;
import br.coop.unimedriopardo.uniresultado.service.PrestadorService;
import br.coop.unimedriopardo.uniresultado.service.UsuarioService;

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
		return "usuario.list.tiles";
	}
	
	@GetMapping("/pesquisa")
	public @ResponseBody Page<Usuario> pesquisaPaginacao(
            @RequestParam(
            		value = "page",
                    required = false,
                    defaultValue = "0") int page,
            @RequestParam(
                    value = "size",
                    required = false,
                    defaultValue = "10") int size) {
		PageRequest pageRequest = new PageRequest(page, size, Sort.DEFAULT_DIRECTION,"nome");
		return usuarioService.listagemOrdenada(pageRequest);
	}
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public String salvar(@ModelAttribute("usuario") @Valid Usuario usuario, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "usuario.form.tiles";
		}
		usuarioService.salvar(usuario);
		return "redirect:/usuario/listagem";
	}
	
	@RequestMapping(value = "/alterar/{id}", method = RequestMethod.GET)
	public String editar(@ModelAttribute("id") Integer id, Model model) {
		model.addAttribute("prestadores", prestadorService.listagemOrdenada());
		Usuario usuario = usuarioService.pesquisaPorId(id);
		model.addAttribute("usuario",usuario);
		return "usuario.form.tiles";
	}
	
	@RequestMapping(value = "/excluir/{id}", method = RequestMethod.GET)
	public String excluir(@ModelAttribute("id") Integer id, Model model) {
		usuarioService.deletar(id);
		return "redirect:/usuario/listagem";
	}
}
