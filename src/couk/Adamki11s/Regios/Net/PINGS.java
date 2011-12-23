package couk.Adamki11s.Regios.Net;

public enum PINGS {
	
	ON_ENABLE("http://bit.ly/prRQ4z"),
	ON_CREATE("http://bit.ly/qLA6si"),
	ON_DELETE("http://bit.ly/mQgviK");
	
	private String url;
	
	PINGS(String url){
		this.url = url;
	}
	
	public String getURL(){
		return this.url;
	}

}
