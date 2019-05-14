package br.coop.unimedriopardo.uniresultado.model;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PK_SEQ_USUARIO")
	@SequenceGenerator(sequenceName = "SEQ_USUARIO", allocationSize = 1, name = "PK_SEQ_USUARIO")
	private Integer id;

	@NotBlank
	@Length(min = 3, max = 100)
	@Column(name = "nome", length = 100, nullable = false)
	private String nome;

	@NotBlank
	@Length(min = 3, max = 50)
	@Column(name = "login", length = 50, nullable = false)
	private String login;

	@Column(name = "senha", length = 150)
	private String senha;

	@Email
	@Length(min = 1, max = 50)
	@Column(name = "email", length = 200)
	private String email;

	@NotNull
	@Column(name = "ativo", nullable = false)
	private Integer ativo;

	@NotBlank
	@Column(name = "perfil", length = 100, nullable = false)
	private String perfil;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "prestador_id", referencedColumnName="id", foreignKey = @ForeignKey(name = "Fk_prestador_usuario"))
	private Prestador prestador;

	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JsonIgnore
	private List<LogEnvio> logsEnvio;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAtivo() {
		return ativo;
	}

	public void setAtivo(Integer ativo) {
		this.ativo = ativo;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
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
