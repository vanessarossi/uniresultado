package br.coop.unimedriopardo.uniresultado.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
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

import br.coop.unimedriopardo.uniresultado.model.MigraLaudo;
import br.coop.unimedriopardo.uniresultado.model.Resultado;
import br.coop.unimedriopardo.uniresultado.model.Usuario;
import br.coop.unimedriopardo.uniresultado.service.MigraLaudoService;
import br.coop.unimedriopardo.uniresultado.service.ResultadoService;
import br.coop.unimedriopardo.uniresultado.service.UsuarioService;

@Controller
@RequestMapping("/resultado")
public class ResultadoController {

	private final ResultadoService resultadoService;
	private final UsuarioService usuarioService;
	private final MigraLaudoService migraLaudoService;

	@Autowired
	public ResultadoController(ResultadoService resultadoService, UsuarioService usuarioService, MigraLaudoService migraLaudoService) {
		this.resultadoService = resultadoService;
		this.usuarioService = usuarioService;
		this.migraLaudoService = migraLaudoService;
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
	            		value = "status",
	                    required = false,
	                    defaultValue = "") String status,
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
		
		
		Page<Resultado> resultados;
		if(status.equals("T")) {
			resultados = resultadoService.listarPorPrestador(usuarioLogado, pageRequest);
		}else {
			resultados = resultadoService.listarPorPrestadorEStatus(usuarioLogado, status, pageRequest);
		}
		
		return resultados;
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
	
	@RequestMapping("/lista/migraLaudo")
	public String listaMigraLaudo(Model model) {
		return "resultado.list.migra.tiles";
	}
	
	@RequestMapping(value = "/excluir/migra/{id}", method = RequestMethod.GET)
	public String excluirMigra(@ModelAttribute("id") Integer id, Model model) {
		migraLaudoService.excluirMigra(id);
		return "redirect:/resultado/lista/migraLaudo";
	}
	
	@GetMapping("/pesquisa/migra")
	public @ResponseBody Page<MigraLaudo> pesquisaMigra(
            @RequestParam(
            		value = "page",
                    required = false,
                    defaultValue = "0") int page,
            @RequestParam(
                    value = "size",
                    required = false,
                    defaultValue = "20") int size,
            		Principal principal) {
		PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.DESC,"id");
		Usuario usuarioLogado = usuarioService.pesquisaPorLogin(principal.getName());
		return migraLaudoService.listarPorPrestador(usuarioLogado, pageRequest);
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
	
	@RequestMapping(value = "/validar")
	public String validar(RedirectAttributes redirect, Principal principal) {
		Usuario usuarioLogado = usuarioService.pesquisaPorLogin(principal.getName());
		resultadoService.validarResultados(usuarioLogado);
		return "redirect:/resultado/conferencia";
	}
	
	@RequestMapping(value = "/validar/migra")
	public String validarMigra(RedirectAttributes redirect, Principal principal) {
		Usuario usuarioLogado = usuarioService.pesquisaPorLogin(principal.getName());
		migraLaudoService.converterMigra(usuarioLogado);;
		return "redirect:/resultado/lista/migraLaudo";
	}
	
	
	@RequestMapping(value = "/importar/exames/erroValidacao")
	public String importarExamesErroImportacao(RedirectAttributes redirect, Principal principal) {
		Usuario usuarioLogado = usuarioService.pesquisaPorLogin(principal.getName());
		resultadoService.importarExamesErroImportacao(usuarioLogado);
		return "redirect:/resultado/conferencia";
	}
	
	@RequestMapping(value = "/cancelar/{id}", method = RequestMethod.GET)
	public String excluir(@ModelAttribute("id") Integer id, Model model) {
		resultadoService.cancelar(id);
		return "redirect:/resultado/conferencia";
	}
	
	@RequestMapping(value = "/editar/{id}", method = RequestMethod.GET)
	public String editar(@ModelAttribute("id") Integer id, Model model) {
		model.addAttribute("resultado",resultadoService.pesquisaPorId(id));
		return "resultado.form.tiles";
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

	@RequestMapping(value = "/enviar/parcialmente", method = RequestMethod.GET)
	public String enviarParcial(RedirectAttributes redirect, Principal principal) {
		Usuario usuarioLogado = usuarioService.pesquisaPorLogin(principal.getName());
		resultadoService.enviarResultadosLimitados(usuarioLogado);
		return "redirect:/logEnvio/listagem";
	}

	@RequestMapping(value = "/enviar/todos", method = RequestMethod.GET)
	public String enviarTodos(RedirectAttributes redirect, Principal principal) {
		Usuario usuarioLogado = usuarioService.pesquisaPorLogin(principal.getName());
		resultadoService.enviarExamesPendente(usuarioLogado);
		return "redirect:/logEnvio/listagem";
	}

}
