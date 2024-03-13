package org.teej21.crud_console_app;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.teej21.crud_console_app.dao.StudentDAO;

@SpringBootApplication
public class CrudConsoleAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudConsoleAppApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return args -> {
			new CrudApplication(studentDAO).run();
		};
	}
}
