package br.coop.unimedriopardo.uniresultado.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.coop.unimedriopardo.uniresultado.models.Resultado;


@Controller
@RequestMapping("/resultado")
public class ResultadoController {
	
	@RequestMapping("/formulario")
	public String form(Model model) {
		model.addAttribute("resultado", new Resultado());
		return "resultado.form.tiles";
	}
	
}
