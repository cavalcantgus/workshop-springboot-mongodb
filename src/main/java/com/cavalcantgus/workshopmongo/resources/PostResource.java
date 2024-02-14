package com.cavalcantgus.workshopmongo.resources;

import java.net.URI;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cavalcantgus.workshopmongo.domain.Post;
import com.cavalcantgus.workshopmongo.domain.User;
import com.cavalcantgus.workshopmongo.resources.util.URL;
import com.cavalcantgus.workshopmongo.services.PostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController // Controlador Rest da entidade Post
@RequestMapping(value = "/posts") // Endpoint da entidade Post
@Tag(name = "Posts", description = "Controlador de posts")
public class PostResource {

	// Injeção de dependência automática de um PostService
	@Autowired
	private PostService postService;

	// Mapeia solicitações HTTP GET retornando como resposta o corpo de um post
	@Operation(summary = "Recupera um post com base no ID")
	@GetMapping(value = "/{id}")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Post encontrado", content = {
					@Content(schema = @Schema(implementation = User.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", description = "Nenhum post com esse ID encontrado", content = {
					@Content(schema = @Schema()) }) })
	public ResponseEntity<Post> findById(@PathVariable String id) {
		Post obj = postService.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	// Mapeia solicitações HTTP GET buscando um post a partir de um título chave
	@Operation(summary = "Recupera um post com base numa chave de texto aleatória")
	@GetMapping(value = "/titlesearch")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Post encontrado", content = {
					@Content(schema = @Schema(implementation = User.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", description = "Nenhum post encontrado", content = {
					@Content(schema = @Schema()) }) })
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
		text = URL.decodeParam(text);
		List<Post> list = postService.findByTitle(text);
		return ResponseEntity.ok().body(list);
	}

	// Mapeia solicitações HTTP GET buscando um post a partir de vários critérios
	@Operation(summary = "Recupera um post com base numa consulta personalizada")
	@GetMapping(value = "/fullsearch")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Posts encontrados", content = {
					@Content(schema = @Schema(implementation = User.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", description = "Nenhum post encontrado", content = {
					@Content(schema = @Schema()) }) })
	public ResponseEntity<List<Post>> fullSearch(@RequestParam(value = "text", defaultValue = "") String text,
			@RequestParam(value = "minDate", defaultValue = "") String minDate,
			@RequestParam(value = "maxDate", defaultValue = "") String maxDate) {

		text = URL.decodeParam(text);
		Date min = URL.convertDate(minDate, new Date(0L));
		Date max = URL.convertDate(maxDate, new Date());

		List<Post> list = postService.fullSearch(text, min, max);
		return ResponseEntity.ok().body(list);
	}
	
	// Mapeia solicitações HTTP POST para inserir um novo objeto post
	@Operation(summary = "Insere um novo post no banco de dados")
	@PostMapping
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Post criado com sucesso", content = {
					@Content(schema = @Schema(implementation = User.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "Requisição inválida", content = {
					@Content(schema = @Schema()) }) })
	public ResponseEntity<Void> insert(@RequestBody Post obj) {
		postService.insert(obj); // Converte um objeto UserDTO em User
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	// Mapeia solicitações HTTP DELETE para deletar um objeto post
	@Operation(summary = "Deleta um post com base em seu ID")
	@DeleteMapping(value = "/{id}")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Post removido com sucesso"),
			@ApiResponse(responseCode = "404", description = "Nenhum post com esse ID encontrado", content = {
					@Content(schema = @Schema()) }) })
	public ResponseEntity<Void> delete(@PathVariable String id) {
		postService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
