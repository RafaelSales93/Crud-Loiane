package com.rafael.crud_spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.rafael.crud_spring.model.Curso;
import com.rafael.crud_spring.repository.CusroRepository;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(CusroRepository cusroRepository) {
		return args -> {
			cusroRepository.deleteAll();

			Curso c = new Curso();
			c.setNome("Angular com Spring");
			c.setCategoria("front-end");

			cusroRepository.save(c);
		};

	}

}
