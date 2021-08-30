package com.startersapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "desafios")
public class Desafio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotNull
	@Column(name = "nome_desafio")
	private String nomeDesafio;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNomeDesafio() {
		return nomeDesafio;
	}

	public void setNomeDesafio(String nomeDesafio) {
		this.nomeDesafio = nomeDesafio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((nomeDesafio == null) ? 0 : nomeDesafio.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Desafio other = (Desafio) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (nomeDesafio == null) {
			if (other.nomeDesafio != null)
				return false;
		} else if (!nomeDesafio.equals(other.nomeDesafio))
			return false;
		return true;
	}
	
	
}
