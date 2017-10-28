package ke.co.technovation.antpool.api;


public enum APIMethods {
	POOL_STATS("poolStats.htm", "POST"),
	ACCOUNT_INFO("account.htm", "POST"),
	HASHRATE("hashrate.htm", "POST"),
	WORKER_STATS("workers.htm", "POST"),
	PAYMENT_HISTORY("paymentHistory.htm", "POST");
	
	private String pathParam;
	private String httpMethod;
	
	private APIMethods(String pathParam, String httpMethod){
		this.pathParam = pathParam;
		this.httpMethod = httpMethod;
	}

	public String getPathParam() {
		return pathParam;
	}

	public void setPathParam(String pathParam) {
		this.pathParam = pathParam;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}
	
	
}
