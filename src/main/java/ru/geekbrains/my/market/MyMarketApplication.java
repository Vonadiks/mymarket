package ru.geekbrains.my.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;


@SpringBootApplication
@PropertySource("classpath:secret.properties")
public class MyMarketApplication {
	public static void main(String[] args) {
		SpringApplication.run(MyMarketApplication.class, args);
	}
}
