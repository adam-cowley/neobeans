package co.adamcowley.neobeans;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NeobeansApplication {

	public static void main(String[] args) {
		SpringApplication.run(NeobeansApplication.class, args);
	}

}
