package br.coop.unimedriopardo.uniresultado.models;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Length(min = 3, max = 150)
	@Column(name = "nome", length = 150, nullable = false)
	private String nome;

	@NotBlank
	@Length(min = 3, max = 100)
	@Column(name = "login", length = 100, nullable = false)
	private String login;

	@Column(name = "senha", length = 50)
	private String senha;

	@Email
	@Column(name = "email", length = 200)
	private String email;

	@NotNull
	@Column(name = "ativo", nullable = false)
	private Boolean ativo;

	@DateTimeFormat(style = "dd/MM/yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ultimo_acesso", columnDefinition = "DATETIME")
	private Date ultimoAcesso;

	@NotNull
	@Column(name = "forcar_alterar_senha", nullable = false)
	private Boolean forcarAlterarSenha;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Date getUltimoAcesso() {
		return ultimoAcesso;
	}

	public void setUltimoAcesso(Date ultimoAcesso) {
		this.ultimoAcesso = ultimoAcesso;
	}

	public Boolean getForcarAlterarSenha() {
		return forcarAlterarSenha;
	}

	public void setForcarAlterarSenha(Boolean forcarAlterarSenha) {
		this.forcarAlterarSenha = forcarAlterarSenha;
	}

	

}
