package self.learn.currency.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CurrencyExchange {
	@Id
	private Long id;
	
	@Column(name = "currency_from")
	private String from;
	
	@Column(name = "currency_to")
	private String to;
	private BigDecimal conversionMultiple;
	
	private String env;

}
