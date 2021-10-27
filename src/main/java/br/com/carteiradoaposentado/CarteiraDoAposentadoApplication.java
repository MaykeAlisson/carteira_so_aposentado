package br.com.carteiradoaposentado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class CarteiraDoAposentadoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarteiraDoAposentadoApplication.class, args);
	}

}
