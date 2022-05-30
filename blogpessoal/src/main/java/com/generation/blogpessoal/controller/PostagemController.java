package com.generation.blogpessoal.controller;

import java.util.List; // O "getAll" que fez esse import

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@GetMapping ("/{id}")
	public ResponseEntity <Postagem> getById(@PathVariable Long id){
		return postagemRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse (ResponseEntity.notFound().build());
	}
	
	
	@GetMapping ("/titulo/{titulo}") //criação de método pela interface. Buscar pelo título.
	public ResponseEntity <List<Postagem>> getByTitulo (@PathVariable String titulo){
		return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
		
		 //select * from tb_postagens WHERE titulo LIKE "%titulo%";
	}
	
	@PostMapping
	public ResponseEntity <Postagem> postPostagem(@Valid @RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.CREATED).body(postagemRepository.save(postagem));
	}
	
	@PutMapping
	public ResponseEntity <Postagem> putPostagem (@Valid @RequestBody Postagem postagem){
		return postagemRepository.findById(postagem.getId())
				.map(resposta -> ResponseEntity.ok().body(postagemRepository.save(postagem)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping ("/{id}")
	public ResponseEntity<?> deletePostagem(@PathVariable Long id){
		
		return postagemRepository.findById(id)
				.map(resposta -> {
					postagemRepository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.notFound().build());
	}
	
	/*VÁRIAS FORMAS DE ALCANÇAR O MESMO OBJETIVO:
	*
	* @PutMapping
	* public ResponseEntity <Postagem> putPostagem(@Valid @RequestBody Postagem postagem){
	*	return ResponseEntity.status(HttpStatus.OK).body(postagemRepository.save(postagem));
	*
	*
	* @DeleteMapping ("/{id}")
	* public void deletePostagem (@PathVariable Long id) {
	*	postagemRepository.deleteById(id);
	*}
	*
	* @PutMapping
	* public ResponseEntity <Postagem> putPostagem (@Valid @RequestBody Postagem postagem){
	*	if(postagem.getId() == null)
	*		return ResponseEntity.badRequest().build();
	*	return postagemRepository.findById(postagem.getId())
	*			.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(postagemRepository.save(postagem)))
	*			.orElse(ResponseEntity.badRequest().build());
	* }
	*
	* @DeleteMapping ("/{id}")
	* @ResponseStatus(HttpStatus.NO_CONTENT)
	* public void deletePostagem(@PathVariable Long id) {
	*	Optional <Postagem> resposta = postagemRepository.findById(id);
	*	if (resposta.isPresent())
	*		ResponseEntity.ok(resposta);
	*	else 
	*		ResponseEntity.notFound().build();
	* }
	*/
	
	
	
}
