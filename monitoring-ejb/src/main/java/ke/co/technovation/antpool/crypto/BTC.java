package ke.co.technovation.antpool.crypto;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

public class BTC {

	public static BigDecimal getPrice() {
		
		BigDecimal usdEq = BigDecimal.ONE.negate();
		
		try{
			
			String url_base = "https://blockchain.info/ticker";
	
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet post = new HttpGet(url_base);
	
			HttpResponse response = client.execute(post);
			System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
	
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	
			StringBuilder result = new StringBuilder();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			String results = result.toString();
			System.out.println(results);
			JSONObject jsob = new JSONObject(results).getJSONObject("USD");
			
			usdEq = jsob.getBigDecimal("sell");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return usdEq;
	}

}
