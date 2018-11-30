package br.coop.unimedriopardo.uniresultado.controllers;

import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.coop.unimedriopardo.uniresultado.models.Resultado;
import br.coop.unimedriopardo.uniresultado.services.ResultadoService;


@Controller
@RequestMapping("/resultado")
public class ResultadoController {
	
	private final ResultadoService resultadoService;

	@Autowired
	public ResultadoController(ResultadoService resultadoService) {
		this.resultadoService = resultadoService;
	}
	
	@RequestMapping("/formulario")
	public String form(Model model) {
		model.addAttribute("resultado", new Resultado());
		return "resultado.form.tiles";
	}
	
	@RequestMapping("/conferencia")
	public String conferencia(Model model) {
		model.addAttribute("resultados", resultadoService.listarPendentePorPrestador());
		return "resultado.conferencia.tiles";
	}
	
	@RequestMapping("/envio")
	public String envio(Model model) {
		model.addAttribute("resultados", resultadoService.listarPendentePorPrestador());
		return "resultado.formEnvio.tiles";
	}
	
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public String salvar(MultipartFile arquivo,@Valid Resultado resultado,BindingResult result,RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return "resultado.form.tiles";
		}
		resultadoService.salvar(resultado, arquivo);
		return "redirect:/resultado/conferencia";
	}
	
	@RequestMapping(value = "/cancelar/{id}", method = RequestMethod.GET)
	public String excluir(@ModelAttribute("id") Integer id, Model model) {
		resultadoService.cancelar(id);
		return "redirect:/resultado/conferencia";
	}
	
	@RequestMapping(value = "/enviar/selecionados", method = RequestMethod.POST)
	public String salvar(List<Resultado> resultados ,RedirectAttributes redirect) {
		resultadoService.enviarExamesSelecionados(resultados);
		return "redirect:/logEnvio/listagem";
	}
	
	@RequestMapping(value = "/enviar/todos", method = RequestMethod.GET)
	public String salvar(RedirectAttributes redirect) {
		resultadoService.enviarExamesPendente();
		return "redirect:/logEnvio/listagem";
	}
	
}
