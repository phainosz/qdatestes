package entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.ws.rs.FormParam;

@Entity
public class Arquivo implements Serializable {
	private static final long serialVersionUID = -5091911513985284604L;

	@Id
	@SequenceGenerator(allocationSize = 0, name = "NUM_SEQ_ARQUIVO", sequenceName = "NUM_SEQ_ARQUIVO")
	@GeneratedValue(generator = "NUM_SEQ_ARQUIVO", strategy = GenerationType.SEQUENCE)
	private Long id;

	@FormParam("nome")
	private String nome;

	@Lob
	@FormParam("file")
	private byte[] file;

	private String tipo;

	public Arquivo() {
	}

	public Arquivo(String nome, byte[] file) {
		this.nome = nome;
		this.file = file;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
