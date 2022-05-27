package com.generation.blogpessoal.repository;

import org.springframework.data.jpa.repository.JpaRepository; //"JpaRepository" 
import org.springframework.stereotype.Repository; //"Repository" anotação

import com.generation.blogpessoal.model.Postagem; //"Postagem, Long" Tá pegando o "id" ? da classe Postagem ?

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> {
	

}
