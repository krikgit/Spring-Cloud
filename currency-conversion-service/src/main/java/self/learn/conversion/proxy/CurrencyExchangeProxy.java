package self.learn.conversion.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import self.learn.conversion.model.CurrencyExchange;


//@FeignClient(name = "currency-exchange-service", url = "localhost:8000")
@FeignClient(name = "currency-exchange-service")
public interface CurrencyExchangeProxy {
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to);

}
