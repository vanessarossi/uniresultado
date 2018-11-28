package br.coop.unimedriopardo.uniresultado.services;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.coop.unimedriopardo.uniresultado.models.Usuario;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService{

	@Override
	public Usuario salvar(Usuario usuario) {
		return null;
	}

	@Override
	public List<Usuario> listagemOrdenada() {
		return null;
	}

	@Override
	public Usuario pesquisaPorId(Integer id) {
		return null;
	}

	@Override
	public void deletar(Integer id) {
	}

	
}
