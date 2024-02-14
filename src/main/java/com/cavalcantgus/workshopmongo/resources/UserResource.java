package com.cavalcantgus.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cavalcantgus.workshopmongo.domain.Post;
import com.cavalcantgus.workshopmongo.domain.User;
import com.cavalcantgus.workshopmongo.dto.UserDTO;
import com.cavalcantgus.workshopmongo.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController // Controlador Rest da entidade User
@RequestMapping(value = "/users") // Endpoint da entidade User
@Tag(name = "Usuários", description = "Controlador de usuários")
public class UserResource {

	@Autowired
	private UserService userService;

	// Mapeia solicitações HTTP GET retornando como respostas todos os usuários
	@Operation(summary = "Recupera todos os usuários")
	@GetMapping
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Usuários encontrados", content = {
					@Content(schema = @Schema(implementation = User.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", description = "Nenhum usuário encontrado.", content = {
					@Content(schema = @Schema()) }) })
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> list = userService.findAll();
		List<UserDTO> listDTO = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	// Mapeia solicitações HTTP GET retornando como resposta o corpo de um usuário
	@Operation(summary = "Recupera um usuário com base no ID")
	@GetMapping(value = "/{id}")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Usuário encontrado", content = {
					@Content(schema = @Schema(implementation = User.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", description = "Nenhum usuário com esse ID encontrado", content = {
					@Content(schema = @Schema()) }) })
	public ResponseEntity<UserDTO> findByid(@PathVariable String id) {
		User obj = userService.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj));
	}

	// Mapeia solicitações HTTP POST para inserir um novo objeto user
	@Operation(summary = "Insere um novo usuário no banco de dados")
	@PostMapping
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Usuário criado com sucesso", content = {
					@Content(schema = @Schema(implementation = User.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "Requisição inválida", content = {
					@Content(schema = @Schema()) }) })
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto) {
		User obj = userService.fromDTO(objDto); // Converte um objeto UserDTO em User
		obj = userService.insert(obj); // Insere um novo objeto User
		// Cria uma URI de resposta para a requisição atual adicionando o caminho para o
		// ID do objeto recém-criado
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	// Mapeia solicitações HTTP DELETE para deletar um objeto user
	@Operation(summary = "Deleta um usuário com base em seu ID")
	@DeleteMapping(value = "/{id}")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Usuário removido com sucesso"),
			@ApiResponse(responseCode = "404", description = "Nenhum usuário com esse ID encontrado", content = {
					@Content(schema = @Schema()) }) })
	public ResponseEntity<Void> delete(@PathVariable String id) {
		userService.delete(id);
		return ResponseEntity.noContent().build();
	}

	// Mapeia solicitações HTTP PUT para atualizar um objeto user
	@Operation(summary = "Atualiza um usuário com base em seu ID")
	@PutMapping(value = "/{id}")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso", content = {
					@Content(schema = @Schema(implementation = User.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", description = "Nenhum usuário com esse ID encontrado", content = {
					@Content(schema = @Schema()) }) })
	public ResponseEntity<Void> update(@RequestBody UserDTO objDto, @PathVariable String id) {
		User obj = userService.fromDTO(objDto); // Converte um objeto UserDTO em User
		obj.setId(id);
		obj = userService.update(obj); // Atualiza os dados do usuário
		return ResponseEntity.noContent().build();
	}

	// Mapeia solicitações HTTP GET para a busca de posts dos usuários
	@Operation(summary = "Recupera posts do usuário com base no ID")
	@GetMapping(value = "/{id}/posts")
	public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
		User obj = userService.findById(id);
		return ResponseEntity.ok().body(obj.getPosts());
	}
}
