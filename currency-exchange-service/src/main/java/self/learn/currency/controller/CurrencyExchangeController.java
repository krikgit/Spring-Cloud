package self.learn.currency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import self.learn.currency.dao.CurrencyExchangeRepository;
import self.learn.currency.model.CurrencyExchange;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private CurrencyExchangeRepository repo;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		String port  = env.getProperty("local.server.port");
		//return new CurrencyExchange(1000L,from,to,BigDecimal.valueOf(5),env.getProperty("local.server.port"));
	 CurrencyExchange ce = repo.findByFromAndTo(from, to);
	 ce.setEnv(port);
	 return ce;
	}

}
