package io.github.zerothreadsonfire.playgroundbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBootApplication encapsulates @Configuration, @EnableAutoConfiguration & @ComponentScan annotations with their
 * default attributes. Its made configuring spring easier with it's auto-configuration feature.
 */
@SpringBootApplication
public class PlaygroundBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlaygroundBackendApplication.class, args);
	}

}
