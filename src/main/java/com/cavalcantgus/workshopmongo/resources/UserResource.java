package com.cavalcantgus.workshopmongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cavalcantgus.workshopmongo.domain.User;


@RestController // Controlador Rest da entidade User
@RequestMapping(value = "/users") // Endpoint da entidade User

public class UserResource {
	
	// Mapeia solicitações HTTP GET retornando todos os usuários
	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		User user = new User("1", "Maria Brown", "maria@gmail.com");
		User user2 = new User("2", "Alex Green", "alex@gmail.com");
		
		List<User> list = new ArrayList<>();
		list.addAll(Arrays.asList(user, user2));
		
		return ResponseEntity.ok().body(list);
	}
	
}
