package com.cavalcantgus.workshopmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cavalcantgus.workshopmongo.domain.User;
import com.cavalcantgus.workshopmongo.services.UserService;


@RestController // Controlador Rest da entidade User
@RequestMapping(value = "/users") // Endpoint da entidade User

public class UserResource {
	
	@Autowired
	private UserService userService;
	
	// Mapeia solicitações HTTP GET retornando todos os usuários
	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		List<User> list = userService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
}
