package br.coop.unimedriopardo.uniresultado.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.coop.unimedriopardo.uniresultado.model.Resultado;
import br.coop.unimedriopardo.uniresultado.model.Usuario;
import br.coop.unimedriopardo.uniresultado.service.ResultadoService;
import br.coop.unimedriopardo.uniresultado.service.UsuarioService;

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
	
	@RequestMapping("/listagem")
	public String list(Model model) {
		return "resultado.list.tiles";
	}
	
	@GetMapping("/pesquisa")
	public @ResponseBody Page<Resultado> pesquisaPaginacao(
            @RequestParam(
            		value = "page",
                    required = false,
                    defaultValue = "0") int page,
            @RequestParam(
                    value = "size",
                    required = false,
                    defaultValue = "10") int size,
            		Principal principal) {
		PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.DESC,"data");
		Usuario usuarioLogado = usuarioService.pesquisaPorLogin(principal.getName());
		return resultadoService.listarPorPrestador(usuarioLogado, pageRequest);
	}
	
	@RequestMapping("/conferencia")
	public String conferencia(Model model) {
		return "resultado.conferencia.tiles";
	}
	
	@GetMapping("/pesquisa/importado")
	public @ResponseBody Page<Resultado> pesquisaImportado(
            @RequestParam(
            		value = "page",
                    required = false,
                    defaultValue = "0") int page,
            @RequestParam(
                    value = "size",
                    required = false,
                    defaultValue = "20") int size,
            		Principal principal) {
		PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.DESC,"data");
		Usuario usuarioLogado = usuarioService.pesquisaPorLogin(principal.getName());
		return resultadoService.listarImportadosPorPrestador(usuarioLogado, pageRequest);
	}
	
	@GetMapping("/pesquisa/errovalidacao")
	public @ResponseBody Page<Resultado> pesquisaErroValidacao(
            @RequestParam(
            		value = "page",
                    required = false,
                    defaultValue = "0") int page,
            @RequestParam(
                    value = "size",
                    required = false,
                    defaultValue = "20") int size,
            		Principal principal) {
		PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.DESC,"data");
		Usuario usuarioLogado = usuarioService.pesquisaPorLogin(principal.getName());
		return resultadoService.listarErroValidacaoPorPrestador(usuarioLogado, pageRequest);
	}
	
	@RequestMapping("/form/envio")
	public String envio(Model model, Principal principal) {
		return "resultado.formEnvio.tiles";
	}
	
	@GetMapping("/pesquisa/envio")
	public @ResponseBody Page<Resultado> pesquisaEnvio(
            @RequestParam(
            		value = "page",
                    required = false,
                    defaultValue = "0") int page,
            @RequestParam(
                    value = "size",
                    required = false,
                    defaultValue = "20") int size,
            		Principal principal) {
		PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.DESC,"data");
		Usuario usuarioLogado = usuarioService.pesquisaPorLogin(principal.getName());
		return resultadoService.listarPendenteEnvioPorPrestador(usuarioLogado, pageRequest);
	}
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public String salvar(MultipartFile arquivo, @Valid Resultado resultado, BindingResult result,
			RedirectAttributes redirect, Principal principal) {
		if (result.hasErrors()) {
			return "resultado.form.tiles";
		}
		Usuario usuarioLogado = usuarioService.pesquisaPorLogin(principal.getName());
		resultadoService.salvar(resultado,usuarioLogado, arquivo);
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
		resultadoService.enviarResultadosSelecionado(resultados);
		return "redirect:/logEnvio/listagem";
	}

	@RequestMapping(value = "/enviar/todos", method = RequestMethod.GET)
	public String salvar(RedirectAttributes redirect, Principal principal) {
		Usuario usuarioLogado = usuarioService.pesquisaPorLogin(principal.getName());
		resultadoService.enviarExamesPendente(usuarioLogado);
		return "redirect:/logEnvio/listagem";
	}

}