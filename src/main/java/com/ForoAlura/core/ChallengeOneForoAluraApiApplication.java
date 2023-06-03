package com.ForoAlura.core;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ChallengeOneForoAluraApiApplication {

	//	Se crea este bean para usar la dependencia modelMapper para mapear entidades
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(ChallengeOneForoAluraApiApplication.class, args);
	}

}
