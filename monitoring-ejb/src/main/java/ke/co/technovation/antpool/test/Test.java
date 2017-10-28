package ke.co.technovation.antpool.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import ke.co.technovation.antpool.api.APIMethods;
import ke.co.technovation.antpool.crypto.BTC;
import ke.co.technovation.antpool.fiat.FiatExchangeRate;
import ke.co.technovation.antpool.sinature.Signature;

public class Test {

	
	public static void main(String[] args) throws Exception{
		
		String results = "";
		try{
			
			String url_base = "https://antpool.com/api/".concat( APIMethods.ACCOUNT_INFO.getPathParam() );
			
			HttpClient client = HttpClientBuilder.create().build();
			HttpPost post = new HttpPost(url_base);
			
			
			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			urlParameters.add(new BasicNameValuePair("key", "d78fa971d5e442e092a456c35def8499"));
			urlParameters.add(new BasicNameValuePair("nonce", "nonce"));
			urlParameters.add(new BasicNameValuePair("signature", Signature.apiSignature("waithaka2", "d78fa971d5e442e092a456c35def8499", "nonce", "0cc97896ec6544e48e169dd50a95c979")));
			urlParameters.add(new BasicNameValuePair("coin", "BTC"));
	
			post.setEntity(new UrlEncodedFormEntity(urlParameters));
	
			HttpResponse response = client.execute(post);
			System.out.println("Response Code : "
			                + response.getStatusLine().getStatusCode());
	
			BufferedReader rd = new BufferedReader(
			        new InputStreamReader(response.getEntity().getContent()));
	
			StringBuilder result = new StringBuilder();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			
			results = result.toString();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		JSONObject jsob = new JSONObject(results);
		if(jsob.getInt("code")==0){
			JSONObject data = jsob.getJSONObject("data");
			BigDecimal earn24Hours = data.getBigDecimal("earn24Hours");
			BigDecimal earnTotal = data.getBigDecimal("earnTotal");
			BigDecimal paidOut = data.getBigDecimal("paidOut");
			BigDecimal balance = data.getBigDecimal("balance");
			BigDecimal btc_selling_price = BTC.getPrice();
			BigDecimal balance_usd = balance.multiply( btc_selling_price);
			BigDecimal balance_kes = FiatExchangeRate.getExchangeFiat(balance_usd,"USD","KES");
			BigDecimal earnTotal_usd = earnTotal.multiply( btc_selling_price);
			BigDecimal earnTotal_kes = FiatExchangeRate.getExchangeFiat(balance_usd,"USD","KES");
			System.out.println(" Balance BTC.  --> "+balance);
			System.out.println(" Balance Kes.  --> "+balance_kes);
			System.out.println(" Balance USD.  --> "+balance_usd);
			System.out.println(" Total So Far BTC.  --> "+earnTotal);
			System.out.println(" Total So Far Kes.  --> "+earnTotal_kes);
			System.out.println(" Total So Far USD.  --> "+earnTotal_usd);
		}else{
			System.out.println(results);
		}
		
		
	}
}
