package self.learn.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import self.learn.service.configuration.Config;
import self.learn.service.model.Limits;

@RestController
@RequestMapping("/api")
public class LimitController {
	
	@Autowired
	private Config configuration;
	
	@GetMapping("/limit")
	public Limits retrieveLimit() {
		return new Limits(configuration.getMinimum(),configuration.getMaximum());
		//return new Limits(1,1000);
	}
}
