package com.cristian.ordemservico.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.cristian.ordemservico.service.DBService;


@Configuration
@Profile("test")
public class TesteConfig {
	
	@Autowired
	private DBService dbService;
	
	@Bean
	public void instanciaDB() {
		this.dbService.instanciaDB();
		
	}

}
