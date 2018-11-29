package br.coop.unimedriopardo.uniresultado.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import br.coop.unimedriopardo.uniresultado.models.Prestador;
import br.coop.unimedriopardo.uniresultado.services.PrestadorService;


@Controller
@RequestMapping("/prestador")
public class PrestadorController {
	
	private final PrestadorService prestadorService;

	@Autowired
	public PrestadorController(PrestadorService prestadorService) {
		this.prestadorService = prestadorService;
	}
	
	@RequestMapping("/formulario")
	public String form(Model model) {
		model.addAttribute("prestador", new Prestador());
		return "prestador.form.tiles";
	}
	
	@RequestMapping("/listagem")
	public String list(Model model) {
		model.addAttribute("prestadores",prestadorService.listagemOrdenada());
		return "prestador.list.tiles";
	}
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public String salvar(@ModelAttribute("prestador") @Valid Prestador prestador, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "prestador.form.tiles";
		}
		prestadorService.salvar(prestador);
		return "redirect:/prestador/listagem";
	}
	
	@RequestMapping(value = "/alterar/{id}", method = RequestMethod.GET)
	public String editar(@ModelAttribute("id") Integer id, Model model) {
		Prestador prestador = prestadorService.pesquisaPorId(id);
		model.addAttribute("prestador",prestador);
		return "prestador.form.tiles";
	}
	
	@RequestMapping(value = "/excluir/{id}", method = RequestMethod.GET)
	public String excluir(@ModelAttribute("id") Integer id, Model model) {
		prestadorService.deletar(id);
		return "redirect:/prestador/listagem";
	}
	
}
