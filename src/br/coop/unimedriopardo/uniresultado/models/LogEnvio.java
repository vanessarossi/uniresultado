package br.coop.unimedriopardo.uniresultado.models;

import java.util.Date;
import javax.persistence.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;


@Entity
@Table(name = "log_envio")
public class LogEnvio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data")
	private Date data;
	
	@NotBlank
	@Length(min = 1, max = 2)
	@Column(name = "status", length = 2, nullable = false)
	private String status;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_id", foreignKey = @ForeignKey(name = "Fk_usuario_log_exame"))
	private Usuario usuario;
	
	@Column(name = "resposta", length = 500, nullable = false)
	private String resposta;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "prestador_id", foreignKey = @ForeignKey(name = "Fk_prestador_log_exame"))
	private Prestador prestador;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "resultado_id", foreignKey = @ForeignKey(name = "Fk_resultado_log_exame"))
	private Resultado resultado;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getResposta() {
		return resposta;
	}
	
	public String getRespostaReplace() {
		return resposta.replaceAll("\"", "");
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public Prestador getPrestador() {
		return prestador;
	}

	public void setPrestador(Prestador prestador) {
		this.prestador = prestador;
	}

	public Resultado getResultado() {
		return resultado;
	}

	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}

}
