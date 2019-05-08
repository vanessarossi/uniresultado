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
import br.coop.unimedriopardo.uniresultado.model.Prestador;
import br.coop.unimedriopardo.uniresultado.service.PrestadorService;


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
		return "prestador.list.tiles";
	}

	@GetMapping("/pesquisa")
	public @ResponseBody Page<Prestador> pesquisaPaginacao(
            @RequestParam(
            		value = "page",
                    required = false,
                    defaultValue = "0") int page,
            @RequestParam(
                    value = "size",
                    required = false,
                    defaultValue = "10") int size) {
		PageRequest pageRequest = new PageRequest(page, size, Sort.DEFAULT_DIRECTION,"nome");
		return prestadorService.listagemOrdenada(pageRequest);
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
