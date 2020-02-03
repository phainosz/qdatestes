package model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Pessoa implements Serializable {

	private static final long serialVersionUID = -3928572189567437507L;

	@Id
	@GeneratedValue(generator = "NUM_SEQ", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "NUM_SEQ", sequenceName = "NUM_SEQ_PESSOA", allocationSize = 0)
	private Long id;
	
	private String nome;
	private String cpf;
	private String sobrenome;
	private Integer idade;
	private LocalDate dataNascimento;

	public Pessoa() {
	}

	public Pessoa(String nome, String cpf, String sobrenome, Integer idade, LocalDate dataNascimento) {
		this.nome = nome;
		this.cpf = cpf;
		this.sobrenome = sobrenome;
		this.idade = idade;
		this.dataNascimento = dataNascimento;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@Override
	public String toString() {
		return this.nome;
	}

}
