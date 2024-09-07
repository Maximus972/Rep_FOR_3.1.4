package ru.kata.spring.boot_security.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.kata.spring.boot_security.demo.service.UserService;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "ru.kata.spring.boot_security.demo")
public class SpringBootSecurityDemoApplication {
//	private final UserService userService;

//	public SpringBootSecurityDemoApplication(UserService userService) {
//		this.userService = userService;
//	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner run() {
//		return args -> {
//			// Вызов метода для шифрования существующих паролей
//			userService.encryptExistingPasswords();
//		};
//	}
}
