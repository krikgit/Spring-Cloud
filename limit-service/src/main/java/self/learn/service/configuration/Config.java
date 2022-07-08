package self.learn.service.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ConfigurationProperties("limit-service")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Configuration
public class Config {
	
	private int minimum;
	private int maximum;
}
