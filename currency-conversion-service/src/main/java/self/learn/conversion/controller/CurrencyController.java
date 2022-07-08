package self.learn.conversion.controller;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import self.learn.conversion.model.CurrencyConversion;
import self.learn.conversion.model.CurrencyExchange;
import self.learn.conversion.proxy.CurrencyExchangeProxy;

@RestController
public class CurrencyController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private CurrencyExchangeProxy proxy;

	@GetMapping("/currency-conversion/from/{from}/to/{to}/quants/{quants}")
	public CurrencyConversion calculateCurrency(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quants) {
		// TODO Auto-generated constructor stub
		String port = env.getProperty("local.server.port");
		
		HashMap<String, String> uriVars = new HashMap<>();
		uriVars.put("from", from);
		uriVars.put("to", to);
		
		ResponseEntity<CurrencyConversion> entity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
				CurrencyConversion.class, uriVars);
		CurrencyConversion cc = entity.getBody();
		return new CurrencyConversion(cc.getId(), from, to, quants,cc.getConversionMultiple(), quants.multiply(cc.getConversionMultiple()),port );
	}
	
	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quants/{quants}")
	public CurrencyConversion calculateCurrencyByFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quants) {
		// TODO Auto-generated constructor stub
		String port = env.getProperty("local.server.port");
		
		/*HashMap<String, String> uriVars = new HashMap<>();
		 *uriVars.put("from", from);
		 *uriVars.put("to", to);
		
		
		 * ResponseEntity<CurrencyConversion> entity = new RestTemplate().getForEntity(
		 * "http://localhost:8000/currency-exchange/from/{from}/to/{to}",
		 * CurrencyConversion.class, uriVars);
		 */
		
		CurrencyExchange ce = proxy.retrieveExchangeValue(from, to);
		
		CurrencyConversion cc = CurrencyConversion.builder().id(ce.getId())
				.from(from).to(to).quants(quants)
				.conversionMultiple(ce.getConversionMultiple())
				.total(quants.multiply(ce.getConversionMultiple()))
				.env(ce.getEnv()).build();
		
		return cc;
	}

}
