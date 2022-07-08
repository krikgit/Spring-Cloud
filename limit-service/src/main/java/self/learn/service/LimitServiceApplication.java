package self.learn.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"self.learn.service"})
public class LimitServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LimitServiceApplication.class, args);
	}

}
