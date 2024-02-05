package com.cavalcantgus.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cavalcantgus.workshopmongo.domain.User;
import com.cavalcantgus.workshopmongo.dto.UserDTO;
import com.cavalcantgus.workshopmongo.services.UserService;


@RestController // Controlador Rest da entidade User
@RequestMapping(value = "/users") // Endpoint da entidade User

public class UserResource {
	
	@Autowired
	private UserService userService;
	
	// Mapeia solicitações HTTP GET retornando como respostas todos os usuários
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll(){
		List<User> list = userService.findAll();
		List<UserDTO> listDTO = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	// Mapeia solicitações HTTP GET retornando como resposta o corpo de um usuário
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findByid(@PathVariable String id){
		User obj = userService.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj));
	}
	
	// Mapeia solicitações HTTP POST 
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto){
		User obj = userService.fromDTO(objDto); // Converte um objeto UserDTO em User
		obj = userService.insert(obj); // Insere um novo objeto User
		
		// Cria uma URI de resposta para a requisição atual adicionando o caminho para o ID do objeto recém-criado
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build(); 
	}
}
