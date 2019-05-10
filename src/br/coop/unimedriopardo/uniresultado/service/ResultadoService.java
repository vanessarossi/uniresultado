package br.coop.unimedriopardo.uniresultado.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import br.coop.unimedriopardo.uniresultado.model.Resultado;
import br.coop.unimedriopardo.uniresultado.model.Usuario;

public interface ResultadoService {
	
	public Resultado salvar(Resultado resultado,Usuario usuario, MultipartFile anexo);
	public Resultado pesquisaPorId(Integer id);
	
	public void cancelar(Integer id);
	
	public void validarResultados(Usuario usuarioLogado);
	public void enviarExamesPendente(Usuario usuarioLogado);
	public void enviarResultadosSelecionado(List<Resultado> resultados);
	
	public void converterResultadoEmPDF(Integer id);
	
	public Page<Resultado> listarImportadosPorPrestador(Usuario usuarioLogado, Pageable pageable);
	public Page<Resultado> listarErroValidacaoPorPrestador(Usuario usuarioLogado, Pageable pageable);
	public Page<Resultado> listarPendenteEnvioPorPrestador(Usuario usuarioLogado, Pageable pageable);
	public Page<Resultado> listarPorPrestador(Usuario usuarioLogado, Pageable pageable);
	
	public String retornaCaminho();

}

