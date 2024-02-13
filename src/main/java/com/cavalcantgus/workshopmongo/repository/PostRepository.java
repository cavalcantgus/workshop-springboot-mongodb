package com.cavalcantgus.workshopmongo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.cavalcantgus.workshopmongo.domain.Post;

// Interface responsável pelo acesso a dados da entidade User 
@Repository
public interface PostRepository extends MongoRepository<Post, String>{
	
	// Busca de título com o uso de uma expressão regular
	@Query("{ title: { $regex: ?0, $options: 'i' } }")
	List<Post> findByTitle(String text);
	
	// Faz uma consulta no banco de dados por um post contendo uma palavra chave
	List<Post> findByTitleContaining(String text);
	
	/*
	 * Consulta personalizada no banco de dados para encontrar um post por duas lógicas:
	 * 1° Encontrar posts em um intervalo de tempo definido por uma data mínima e máxima
	 * 2° Encontrar posts que contenham em seu título, subtítulo, ou comentários, o valor do parâmetro 'text' da requisição
	 */
	
	@Query("{ $and: [ { 'date': {$gte: ?1} }, { 'date': {$lte: ?2} }, { $or: [ { 'title': { $regex: ?0, $options: 'i'} }, { 'subtitle': { $regex: ?0, $options: 'i'} }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
	List<Post> fullSearch(String text, Date minDate, Date maxDate);
}
