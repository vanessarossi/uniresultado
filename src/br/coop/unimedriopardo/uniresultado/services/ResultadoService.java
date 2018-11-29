package br.coop.unimedriopardo.uniresultado.services;


import org.springframework.web.multipart.MultipartFile;

import br.coop.unimedriopardo.uniresultado.models.Resultado;

public interface ResultadoService {
	
	public Resultado salvar(Resultado resultado, MultipartFile anexo);
	public Resultado pesquisaPorId(Integer id);
	public void deletar(Integer id);

}
