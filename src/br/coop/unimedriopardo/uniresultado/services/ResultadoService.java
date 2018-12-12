package br.coop.unimedriopardo.uniresultado.services;


import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import br.coop.unimedriopardo.uniresultado.models.Resultado;
import br.coop.unimedriopardo.uniresultado.models.Usuario;

public interface ResultadoService {
	
	public Resultado salvar(Resultado resultado, MultipartFile anexo);
	public Resultado pesquisaPorId(Integer id);
	public void cancelar(Integer id);
	public List<Resultado> listarPendentePorPrestador(Usuario usuarioLogado);
	public void enviarExamesSelecionados(List<Resultado> resultados);
	public void enviarExamesPendente(Usuario usuarioLogado);

}

