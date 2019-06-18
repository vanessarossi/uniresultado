package br.coop.unimedriopardo.uniresultado.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.coop.unimedriopardo.uniresultado.connect.ConnectionCS;
import br.coop.unimedriopardo.uniresultado.model.Exame;
import br.coop.unimedriopardo.uniresultado.model.MigraLaudo;
import br.coop.unimedriopardo.uniresultado.model.Resultado;
import br.coop.unimedriopardo.uniresultado.model.Usuario;
import br.coop.unimedriopardo.uniresultado.repository.RepositorioExame;
import br.coop.unimedriopardo.uniresultado.repository.RepositorioMigraLaudo;
import br.coop.unimedriopardo.uniresultado.repository.RepositorioResultado;

@Service
@Transactional
public class MigraLaudoServiceImpl implements MigraLaudoService{

	private final RepositorioMigraLaudo repositorioMigraLaudo;
	private final RepositorioResultado repositorioResultado;
	private final RepositorioExame repositorioExame;
	
	@Autowired
	public MigraLaudoServiceImpl(RepositorioMigraLaudo repositorioMigraLaudo, RepositorioResultado repositorioResultado, RepositorioExame repositorioExame) {
		super();
		this.repositorioMigraLaudo = repositorioMigraLaudo;
		this.repositorioResultado = repositorioResultado;
		this.repositorioExame = repositorioExame;
	}

	@Override
	public Page<MigraLaudo> listarPorPrestador(Usuario usuarioLogado, Pageable pageable) {
		Page<MigraLaudo> listaMigraLaudo = repositorioMigraLaudo.findByPrestador_id(usuarioLogado.getPrestador().getId(), pageable);
		return listaMigraLaudo;
	}

	@Override
	public void converterMigra(Usuario usuarioLogado) {
		List<MigraLaudo> listaMigra = repositorioMigraLaudo.findByPrestador_id(usuarioLogado.getPrestador().getId());
		ConnectionCS cs = new ConnectionCS();
		for (MigraLaudo migraLaudo : listaMigra) {
			try {
				Resultado resultadoEncontrado = cs.pesquisarInformacoesLaudo(migraLaudo.getAccessionNumber());
				Exame exame = resultadoEncontrado.getExames().get(1);
				exame.setCodigoExame(exame.getCodigoExame().replace(".","").replace("-", ""));
				if (resultadoEncontrado != null) {
					Resultado resultado = new Resultado();
					resultado.setNrCartaoBeneficiario(resultadoEncontrado.getNrCartaoBeneficiario());
					resultado.setNrExecucaoOperadora(resultadoEncontrado.getNrExecucaoOperadora());
					resultado.setAnexo(migraLaudo.getArquivo());
					resultado.setData(new Date());
					resultado.setPrestador(migraLaudo.getPrestador());
					resultado.setStatus("I");
					resultado.setTipoOperacao("L");
					resultado.setFormatoArquivo("pdf");
					resultado = repositorioResultado.save(resultado);
					
					List<Exame> examesDoJob =  resultado.getExames();
					for (Exame ex : examesDoJob) {
						if (ex.getCodigoExame() != exame.getCodigoExame()) {
							repositorioExame.delete(ex);
						}
					}
					repositorioMigraLaudo.delete(migraLaudo);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void excluirMigra(Integer id) {
		repositorioMigraLaudo.delete(id);
	}
}
