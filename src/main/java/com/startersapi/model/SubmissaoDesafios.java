package com.startersapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "submissao_desafios")
public class SubmissaoDesafios {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotNull
	@JoinColumn(name = "codigo_starter")
	@ManyToOne
	private Starter starter;
	
	@NotNull
	@JoinColumn(name = "codigo_desafio")
	@ManyToOne
	private Desafio desafio;
	
	@NotNull
	private String endereco_repositorio;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Starter getStarter() {
		return starter;
	}

	public void setStarter(Starter starter) {
		this.starter = starter;
	}

	public Desafio getDesafio() {
		return desafio;
	}

	public void setDesafio(Desafio desafio) {
		this.desafio = desafio;
	}

	public String getEndereco_repositorio() {
		return endereco_repositorio;
	}

	public void setEndereco_repositorio(String endereco_repositorio) {
		this.endereco_repositorio = endereco_repositorio;
	}
	
	
	
}
