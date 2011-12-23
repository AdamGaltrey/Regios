package couk.Adamki11s.Regios.Data;

public enum MODE {

	Whitelist,
	Blacklist;
	
	public static MODE toMode(String s){
		if(s.equalsIgnoreCase("WHITELIST")){
			return Whitelist;
		} else if(s.equalsIgnoreCase("BLACKLIST")){
			return Blacklist;
		} else {
			return Whitelist; //Default
		}
	}
	
}
