package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;

@Entity
public class Arquivo implements Serializable {
	private static final long serialVersionUID = -5091911513985284604L;

	@Id
	@SequenceGenerator(allocationSize = 0, name = "NUM_SEQ_ARQUIVO", sequenceName = "NUM_SEQ_ARQUIVO")
	@GeneratedValue(generator = "NUM_SEQ_ARQUIVO", strategy = GenerationType.SEQUENCE)
	private Long id;

	private String nome;

	@Lob
	private byte[] conteudo;

	private String tipo;

	public Arquivo() {
	}

	public Arquivo(String nome, byte[] file) {
		this.nome = nome;
		this.conteudo = file;
	}

	public Arquivo(String nome, byte[] conteudo, String tipo) {
		this.nome = nome;
		this.conteudo = conteudo;
		this.tipo = tipo;
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

	public byte[] getConteudo() {
		return conteudo;
	}

	public void setConteudo(byte[] file) {
		this.conteudo = file;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
