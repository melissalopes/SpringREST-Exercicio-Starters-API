package com.startersapi.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Table(name = "nota_desafio")
@Entity
public class NotaDesafio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotNull
	@JoinColumn(name = "nota_qualidade_codigo")
	@Enumerated(EnumType.STRING)
	private NotaQualidadeCodigo notaQualidadeCodigo;
	
	@NotNull
	@JoinColumn(name = "nota_qtd_entregada")
	@Enumerated(EnumType.STRING)
	private NotaQtdEntregada notaQtdEntregada;
	
	@NotNull
	@JoinColumn(name = "codigo_submissao")
	@OneToOne
	private SubmissaoDesafios submissaoDesafios;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public NotaQualidadeCodigo getNotaQualidadeCodigo() {
		return notaQualidadeCodigo;
	}

	public void setNotaQualidadeCodigo(NotaQualidadeCodigo notaQualidadeCodigo) {
		this.notaQualidadeCodigo = notaQualidadeCodigo;
	}

	public NotaQtdEntregada getNotaQtdEntregada() {
		return notaQtdEntregada;
	}

	public void setNotaQtdEntregada(NotaQtdEntregada notaQtdEntregada) {
		this.notaQtdEntregada = notaQtdEntregada;
	}

	public SubmissaoDesafios getSubmissaoDesafios() {
		return submissaoDesafios;
	}

	public void setSubmissaoDesafios(SubmissaoDesafios submissaoDesafios) {
		this.submissaoDesafios = submissaoDesafios;
	}
		
}
