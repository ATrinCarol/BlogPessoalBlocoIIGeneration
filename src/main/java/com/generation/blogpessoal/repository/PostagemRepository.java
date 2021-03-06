package com.generation.blogpessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository; //"JpaRepository" 
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository; //"Repository" anotação

import com.generation.blogpessoal.model.Postagem; //"Postagem, Long" Tá pegando o "id" ? da classe Postagem ?

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> {
	
	public List <Postagem> findAllByTituloContainingIgnoreCase(@Param("titulo") String Titulo);
	
//	public abstract <Postagem> void deletePostagem204 (@Param ("id") Long Id);
	
}
