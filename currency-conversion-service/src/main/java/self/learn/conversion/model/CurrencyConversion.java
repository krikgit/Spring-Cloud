package self.learn.conversion.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CurrencyConversion {
	
	private Long id;
	private String from;
	private String to;
	private BigDecimal quants;
	private BigDecimal conversionMultiple;
	private BigDecimal total;
	private String env;

}
