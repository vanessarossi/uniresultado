package br.coop.unimedriopardo.uniresultado.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "migra_laudo")
public class MigraLaudo {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PK_MIGRA_LAUDO")
	@SequenceGenerator(sequenceName = "SEQ_MIGRA_LAUDO", allocationSize = 1, name = "PK_MIGRA_LAUDO")
	private Integer id;

	@Column(name = "accession_number", length = 20, nullable = false)
	private String accessionNumber;
	
	@Lob
	@Column(name = "arquivo" ,nullable=true)
	private String arquivo;
		
	@ManyToOne
	@JoinColumn(name = "prestador_id", foreignKey = @ForeignKey(name = "Fk_migraLaudo_prestador"))
	private Prestador prestador;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccessionNumber() {
		return accessionNumber;
	}

	public void setAccessionNumber(String accessionNumber) {
		this.accessionNumber = accessionNumber;
	}

	public String getArquivo() {
		return arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}

	public Prestador getPrestador() {
		return prestador;
	}

	public void setPrestador(Prestador prestador) {
		this.prestador = prestador;
	}
	
}
