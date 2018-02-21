package co.adamcowley.neobeans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.neo4j.ogm.session.SessionFactory;

@SpringBootApplication
public class NeobeansApplication {

	public static void main(String[] args) {
		SpringApplication.run(NeobeansApplication.class, args);
	}

}
