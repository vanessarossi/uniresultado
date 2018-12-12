package br.coop.unimedriopardo.uniresultado.controllers;

import java.security.Principal;
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
import br.coop.unimedriopardo.uniresultado.models.Usuario;
import br.coop.unimedriopardo.uniresultado.services.ResultadoService;
import br.coop.unimedriopardo.uniresultado.services.UsuarioService;


@Controller
@RequestMapping("/resultado")
public class ResultadoController {
	
	private final ResultadoService resultadoService;
	private final UsuarioService usuarioService;

	@Autowired
	public ResultadoController(ResultadoService resultadoService, UsuarioService usuarioService) {
		this.resultadoService = resultadoService;
		this.usuarioService = usuarioService;
	}
	
	@RequestMapping("/formulario")
	public String form(Model model) {
		model.addAttribute("resultado", new Resultado());
		return "resultado.form.tiles";
	}
	
	@RequestMapping("/conferencia")
	public String conferencia(Model model, Principal principal) {
		Usuario usuarioLogado = usuarioService.pesquisaPorLogin(principal.getName());
		model.addAttribute("resultados", resultadoService.listarPendentePorPrestador(usuarioLogado));
		return "resultado.conferencia.tiles";
	}
	
	@RequestMapping("/envio")
	public String envio(Model model, Principal principal) {
		Usuario usuarioLogado = usuarioService.pesquisaPorLogin(principal.getName());
		model.addAttribute("resultados", resultadoService.listarPendentePorPrestador(usuarioLogado));
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
	public String salvar(RedirectAttributes redirect, Principal principal) {
		Usuario usuarioLogado = usuarioService.pesquisaPorLogin(principal.getName());
		resultadoService.enviarExamesPendente(usuarioLogado);
		return "redirect:/logEnvio/listagem";
	}
	
}
