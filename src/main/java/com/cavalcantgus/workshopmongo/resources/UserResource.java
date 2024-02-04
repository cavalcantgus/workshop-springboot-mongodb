package com.cavalcantgus.workshopmongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cavalcantgus.workshopmongo.domain.User;
import com.cavalcantgus.workshopmongo.dto.UserDTO;
import com.cavalcantgus.workshopmongo.services.UserService;


@RestController // Controlador Rest da entidade User
@RequestMapping(value = "/users") // Endpoint da entidade User

public class UserResource {
	
	@Autowired
	private UserService userService;
	
	// Mapeia solicitações HTTP GET retornando todos os usuários
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll(){
		List<User> list = userService.findAll();
		List<UserDTO> listDTO = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
}
