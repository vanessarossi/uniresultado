package br.coop.unimedriopardo.uniresultado.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "resultado")
public class Resultado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PK_SEQ_RESULTADO")
	@SequenceGenerator(sequenceName = "SEQ_RESULTADO", allocationSize = 1, name = "PK_SEQ_RESULTADO")
	private Integer id;

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
	
	@Lob
	@Column(name = "anexo" ,nullable=true)
	private String anexo;
	
	@NotBlank
	@Length(min = 1, max = 2)
	@Column(name = "status", length = 1, nullable = false)
	private String status;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data", columnDefinition = "DATE")
	private Date data;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data_cancelamento", columnDefinition = "DATE")
	private Date dataCancelamento;
	
	@JsonIgnore
	@OneToMany(mappedBy = "resultado", fetch = FetchType.EAGER, cascade = CascadeType.MERGE, orphanRemoval = true)
	private List<Exame> exames;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "prestador_id", foreignKey = @ForeignKey(name = "Fk_prestador_resultado"))
	private Prestador prestador;
	
	@JsonIgnore
	@OneToMany(mappedBy = "resultado", fetch = FetchType.EAGER, cascade = CascadeType.MERGE, orphanRemoval = true)
	private List<LogEnvio> logsEnvio;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public String getAnexo() {
		return anexo;
	}

	public void setAnexo(String anexo) {
		this.anexo = anexo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getDataCancelamento() {
		return dataCancelamento;
	}

	public void setDataCancelamento(Date dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}

	public List<Exame> getExames() {
		return exames;
	}

	public void setExames(List<Exame> exames) {
		this.exames = exames;
	}

	public Prestador getPrestador() {
		return prestador;
	}

	public void setPrestador(Prestador prestador) {
		this.prestador = prestador;
	}

	public List<LogEnvio> getLogsEnvio() {
		return logsEnvio;
	}

	public void setLogsEnvio(List<LogEnvio> logsEnvio) {
		this.logsEnvio = logsEnvio;
	}

	
}
