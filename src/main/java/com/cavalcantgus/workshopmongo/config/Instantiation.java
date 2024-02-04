package com.cavalcantgus.workshopmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.cavalcantgus.workshopmongo.domain.User;
import com.cavalcantgus.workshopmongo.repository.UserRepository;

// Classe para processar métodos de configuração durante a inicialização do aplicativo
@Configuration
public class Instantiation implements CommandLineRunner{
	
	//Injeção de dependência automática de UserRepository
	@Autowired
	private UserRepository userRepository;

	// Método para excluir e popular um banco de dados sempre que a aplicação iniciar
	@Override
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll(); // Deleta os dados anteriores
		
		// Insere três novos usuários na coleção user
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");		
		
		// Salva os dados na coleção
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
	}
}
