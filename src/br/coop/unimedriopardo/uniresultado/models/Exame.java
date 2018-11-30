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

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "exame")
public class Exame {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank
	@Length(min = 1, max = 5)
	@Column(name = "codigo_tabela", length = 5, nullable = false)
	private String codigoTabela;
	
	@NotBlank
	@Length(min = 1, max = 10)
	@Column(name = "codigo_exame", length = 10, nullable = false)
	private String codigoExame;
	
	@NotBlank
	@Length(min = 1, max = 5)
	@Column(name = "qtde", length = 5, nullable = false)
	private String qtde;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "resultado_id", foreignKey = @ForeignKey(name = "Fk_resultado_exame"))
	private Resultado resultado;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigoTabela() {
		return codigoTabela;
	}

	public void setCodigoTabela(String codigoTabela) {
		this.codigoTabela = codigoTabela;
	}

	public String getCodigoExame() {
		return codigoExame;
	}

	public void setCodigoExame(String codigoExame) {
		this.codigoExame = codigoExame;
	}

	public String getQtde() {
		return qtde;
	}

	public void setQtde(String qtde) {
		this.qtde = qtde;
	}

	public Resultado getResultado() {
		return resultado;
	}

	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}

	

}
