package com.cavalcantgus.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.cavalcantgus.workshopmongo.domain.Post;
import com.cavalcantgus.workshopmongo.domain.User;
import com.cavalcantgus.workshopmongo.repository.PostRepository;
import com.cavalcantgus.workshopmongo.repository.UserRepository;

// Classe para processar métodos de configuração durante a inicialização do aplicativo
@Configuration
public class Instantiation implements CommandLineRunner{
	
	//Injeção de dependência automática 
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	// Método para excluir dados antigos e popular um banco de dados sempre que a aplicação iniciar
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll(); // Deleta os dados anteriores de User
		postRepository.deleteAll(); // Deleta os dados anteriores de Post
		
		// Insere três novos usuários na coleção user
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");		
		
		// Insere três novos posts na coleção post
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!!!", maria);
		Post post2 = new Post(null, sdf.parse("2/03/2018"), "Bom dia!", "Acordei feliz hoje!", maria);

		// Salva os dados na coleção
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		postRepository.saveAll(Arrays.asList(post1, post2));
		
	}
}
