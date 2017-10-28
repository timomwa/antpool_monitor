package ke.co.technovation.antpool.fiat;

import java.math.BigDecimal;

public class FiatExchangeRate {

	public static BigDecimal getExchangeFiat(BigDecimal balance_usd, String sourceFiat, String destFiat) {
		
		if(sourceFiat.trim().equalsIgnoreCase("USD") && destFiat.trim().equalsIgnoreCase("KES"))
			return BigDecimal.valueOf(103).multiply(balance_usd);
		return BigDecimal.ONE.negate();
	}

}
