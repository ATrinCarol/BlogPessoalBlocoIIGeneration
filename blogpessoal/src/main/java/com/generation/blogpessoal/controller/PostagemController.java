package com.generation.blogpessoal.controller;

import java.util.List; // O "getAll" que fez esse import

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.blogpessoal.model.Postagem;
//"postagemRepository" com p minusculo que ativou esse import
import com.generation.blogpessoal.repository.PostagemRepository; 



@RestController
@RequestMapping ("/postagens")
@CrossOrigin (origins = "*", allowedHeaders = "*")
public class PostagemController {
	
	@Autowired
	private PostagemRepository postagemRepository; 
	
	@GetMapping
	public ResponseEntity<List<Postagem>> getAll (){ //criando o método "getAll"
			return ResponseEntity.ok(postagemRepository.findAll());
	}
}