package self.learn.currency.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import self.learn.currency.model.CurrencyExchange;

@Repository
public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {
	CurrencyExchange findByFromAndTo(String x,String y);
}
