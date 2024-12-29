package com.backend.orders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@EnableR2dbcRepositories
@EnableReactiveMongoRepositories
@EnableMongoAuditing
public class KeolaBackendOrdersApplication {

	public static void main(String[] args) {
		SpringApplication.run(KeolaBackendOrdersApplication.class, args);
	}

}
