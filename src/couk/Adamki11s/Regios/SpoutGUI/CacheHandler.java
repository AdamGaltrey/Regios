package couk.Adamki11s.Regios.SpoutGUI;

import org.getspout.spoutapi.SpoutManager;

import couk.Adamki11s.Regios.Main.Regios;

public class CacheHandler {
	
	public static void cacheObjects(){
		cache("http://dl.dropbox.com/u/27260323/Regios/GUI/Help%20GUI%20Texture.png");
		cache("http://dl.dropbox.com/u/27260323/Regios/GUI/Editor%20GUI%20Texture.png");
	}
	
	private static void cache(String url){
		SpoutManager.getFileManager().addToPreLoginCache(Regios.regios, url);
	}

}
