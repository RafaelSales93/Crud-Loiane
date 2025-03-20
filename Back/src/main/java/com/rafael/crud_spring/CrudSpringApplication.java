package com.rafael.crud_spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.rafael.crud_spring.enums.Categoria;
import com.rafael.crud_spring.model.Curso;
import com.rafael.crud_spring.model.Lesson;
import com.rafael.crud_spring.repository.CursoRepository;

@SpringBootApplication
public class CrudSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(CrudSpringApplication.class, args);
    }

	@Bean
	CommandLineRunner initDatabase(CursoRepository cursoRepository) {
		return args -> {
			cursoRepository.deleteAll();

			Curso c = new Curso();
			c.setNome("Angular com Spring");
			c.setCategoria(Categoria.FRONT_END);

			Lesson l = new Lesson();
			l.setNome("Introdução ao Angular");
			l.setYoutubeUrl("watch?v=9SGDpanrc8U");
			l.setCurso(c);
			c.getLessons().add(l);


			Lesson l2 = new Lesson();
			l2.setNome("Introdução ao Java");
			l2.setYoutubeUrl("watch?v=9SGDTeste");
			l2.setCurso(c);
			c.getLessons().add(l2);
			
			cursoRepository.save(c);
		};

	}

}
