package br.coop.unimedriopardo.uniresultado.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "resultado")
public class Resultado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Length(min = 1, max = 2)
	@Column(name = "tipo_operacao", length = 2, nullable = false)
	private String tipoOperacao;
	
	@NotBlank
	@Length(min = 1, max = 30)
	@Column(name = "nr_cartao_beneficiario", length = 30, nullable = false)
	private String nrCartaoBeneficiario;
	
	@NotBlank
	@Length(min = 1, max = 30)
	@Column(name = "nr_execucao_operadora", length = 30, nullable = false)
	private String nrExecucaoOperadora;
	
	@NotBlank
	@Length(min = 1, max = 10)
	@Column(name = "formato_arquivo", length = 10, nullable = false)
	private String formatoArquivo;
	
	
	@Column(name = "anexo", nullable=true)
	private byte[] anexo;
	
	@NotBlank
	@Length(min = 1, max = 1)
	@Column(name = "status", length = 1, nullable = false)
	private String status;
	
	@OneToMany(mappedBy = "resultado", fetch = FetchType.LAZY, cascade = CascadeType.MERGE, orphanRemoval = true)
	private List<Exame> exames;
	
	@OneToMany(mappedBy = "resultado", fetch = FetchType.LAZY, cascade = CascadeType.MERGE, orphanRemoval = true)
	private List<LogEnvio> logsEnvio;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(String tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	public String getNrCartaoBeneficiario() {
		return nrCartaoBeneficiario;
	}

	public void setNrCartaoBeneficiario(String nrCartaoBeneficiario) {
		this.nrCartaoBeneficiario = nrCartaoBeneficiario;
	}

	public String getNrExecucaoOperadora() {
		return nrExecucaoOperadora;
	}

	public void setNrExecucaoOperadora(String nrExecucaoOperadora) {
		this.nrExecucaoOperadora = nrExecucaoOperadora;
	}

	public String getFormatoArquivo() {
		return formatoArquivo;
	}

	public void setFormatoArquivo(String formatoArquivo) {
		this.formatoArquivo = formatoArquivo;
	}

	public byte[] getAnexo() {
		return anexo;
	}

	public void setAnexo(byte[] anexo) {
		this.anexo = anexo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Exame> getExames() {
		return exames;
	}

	public void setExames(List<Exame> exames) {
		this.exames = exames;
	}

	public List<LogEnvio> getLogsEnvio() {
		return logsEnvio;
	}

	public void setLogsEnvio(List<LogEnvio> logsEnvio) {
		this.logsEnvio = logsEnvio;
	}
}
