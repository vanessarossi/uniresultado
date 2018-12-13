package br.coop.unimedriopardo.uniresultado.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
	public String salvar(MultipartFile arquivo, @Valid Resultado resultado, BindingResult result,
			RedirectAttributes redirect) {
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

	@RequestMapping(value = "/arquivo/{id}", method = RequestMethod.GET)
	public void verArquivo(HttpServletResponse response, @ModelAttribute("id") Integer id, Model model) {
		Path arquivo = Paths.get(resultadoService.retornaCaminho(), id + ".pdf");
		if (Files.exists(arquivo)) { // inline
			response.setHeader("Content-Disposition", "inline");
			response.setContentType("application/pdf");
			try {
				Files.copy(arquivo, response.getOutputStream());
				response.getOutputStream().flush();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	@RequestMapping(value = "/enviar/selecionados", method = RequestMethod.POST)
	public String salvar(List<Resultado> resultados, RedirectAttributes redirect) {
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
