package com.backend.orders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@EnableR2dbcRepositories
@EnableReactiveMongoRepositories
public class KeolaBackendOrdersApplication {

	public static void main(String[] args) {
		SpringApplication.run(KeolaBackendOrdersApplication.class, args);
	}

}
