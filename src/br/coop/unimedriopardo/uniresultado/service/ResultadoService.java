package br.coop.unimedriopardo.uniresultado.service;


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
	public void importarExamesErroImportacao(Usuario usuarioLogado);
	
	public void enviarExamesPendente(Usuario usuarioLogado);
	public void enviarResultadosLimitados(Usuario usuarioLogado);
	
	public void converterResultadoEmPDF(Integer id);
	
	public Page<Resultado> listarImportadosPorPrestador(Usuario usuarioLogado, Pageable pageable);
	public Page<Resultado> listarErroValidacaoPorPrestador(Usuario usuarioLogado, Pageable pageable);
	public Page<Resultado> listarPendenteEnvioPorPrestador(Usuario usuarioLogado, Pageable pageable);
	public Page<Resultado> listarPorPrestador(Usuario usuarioLogado, Pageable pageable);
	public Page<Resultado> listarPorPrestadorEStatus(Usuario usuarioLogado, String status, Pageable pageable);
	
	public String retornaCaminho();

}

