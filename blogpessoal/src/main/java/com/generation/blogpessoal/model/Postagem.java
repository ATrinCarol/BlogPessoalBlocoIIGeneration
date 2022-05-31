package com.generation.blogpessoal.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity //Entidade. Necessário para criação de uma tabela no DB.
@Table (name = "tb_postagens") //Criando a tabela tb_postagens no MySQL
public class Postagem {
	
	@Id //Definindo esse atributo como a Primary Key da tabela postagens do DB blogpessoal
	@GeneratedValue (strategy = GenerationType.IDENTITY) //Definindo a Primary Key com AUTO_INCREMENT
	private Long id;
	
	//Essa anotação "NotBlanck" só funciona com String
	@NotBlank (message = "O atributo 'titulo' é <b>obrigatório</b> e não pode"
			+ " utilizar o espaço em branco.< /br>< /br>")
	@Size (min=5, max=100, message = "O atributo <b>'titulo'</b> deve conter no minímo 5 e no máximo"
			+ " 100 caracteres.< /br>< /br>")
	private String titulo;
	
	//Essa anotação "NotNull" funciona com qualquer tipo de atributo
	@NotNull (message = "O atributo 'texto' é <b>obrigatório</b>.< /br>< /br>")
	@Size (min=10, max=1000, message = "O atributo <b>'texto'</b> deve conter no minímo 10 e no máximo"
			+ "1000 caracteres.")
	private String texto;
	
	@UpdateTimestamp //
	private LocalDateTime data;
	
	@ManyToOne
	@JsonIgnoreProperties ("postagem")
	private Tema tema;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}
	
	

}
