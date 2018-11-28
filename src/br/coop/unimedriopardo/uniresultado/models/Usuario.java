package br.coop.unimedriopardo.uniresultado.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;


@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	@Column(name = "email", length = 200)
	private String email;

	@NotNull
	@Column(name = "ativo", nullable = false)
	private Boolean ativo;
	
	@NotBlank
	@Column(name = "perfil", length = 100, nullable = false)
	private String perfil;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "prestador_id", foreignKey = @ForeignKey(name = "Fk_prestador_usuario"))
	private Prestador prestador;

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

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
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

	
}
