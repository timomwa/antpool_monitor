package ke.co.technovation.antpool.sinature;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

public class Signature {
	
	public static String apiSignature(String userId, String key, String nonce, String secret) throws Exception {
	    String data = userId+key+nonce;
	    Mac hmacSha256 = Mac.getInstance("HmacSHA256");
	    SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
	    hmacSha256.init(secretKey);
	    return Hex.encodeHexString(hmacSha256.doFinal(data.getBytes())).toUpperCase();
	}
	
	
	public static boolean checkApiSignature(String userId, String key, String nonce, String secret, String signature) throws Exception {
	    String data = userId+key+nonce;
	    Mac hmacSha256 = Mac.getInstance("HmacSHA256");
	    SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
	    hmacSha256.init(secretKey);
	    String signatrueTmp = Hex.encodeHexString(hmacSha256.doFinal(data.getBytes())).toUpperCase();
	    if(signature.equals(signatrueTmp)) {
	        return true;
	    }
	    return false;
	}

}
