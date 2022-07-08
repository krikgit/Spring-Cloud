package self.learn.currency.circuit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


@RestController
public class CircuitBreakers {
	private Logger log = LoggerFactory.getLogger(CircuitBreaker.class);
	
	@GetMapping("/hi")
	//@Retry(name = "hi-api", fallbackMethod = "hardCodedMethod")//retry logic
	//@CircuitBreaker(name = "default", fallbackMethod = "hardCodedMethod")//circuit breaking logic
	//@RateLimiter(name = "default")//alowed calls logic
	@Bulkhead(name = "default")
	public String getMsg() {
		log.info("Hitting url");
		/*ResponseEntity<String> entity = new RestTemplate()
				.getForEntity("http://localhost:8080/ok", String.class);
		return entity.getBody();*/
		return "OK!!";
		
	}
	
	public String hardCodedMethod(Exception e) {
		return "Fallback msg: - "+"\n"+e.getMessage();
	}

}
