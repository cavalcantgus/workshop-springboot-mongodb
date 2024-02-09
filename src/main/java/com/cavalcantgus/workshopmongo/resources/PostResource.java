package com.cavalcantgus.workshopmongo.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cavalcantgus.workshopmongo.domain.Post;
import com.cavalcantgus.workshopmongo.services.PostService;


@RestController // Controlador Rest da entidade Post
@RequestMapping(value = "/posts") // Endpoint da entidade Post

public class PostResource {
	
	// Injeção de dependência automática de um PostService
	@Autowired
	private PostService postService;
	
	// Mapeia solicitações HTTP GET retornando como resposta o corpo de um post
	@GetMapping(value = "/{id}")
	public ResponseEntity<Post> findByid(@PathVariable String id){
		Post obj = postService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
}
