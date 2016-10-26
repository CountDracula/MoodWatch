package util;



/// Use this as test class to create "facade"
import java.io.IOException;

import net.IMoodWatch;
import net.MoodWatchFacade;


public class Facade {
	private IMoodWatch facade;


	
	public IMoodWatch getFacade() {
		return facade;
	}



	public void setFacade(IMoodWatch facade) {
		this.facade = facade;
	}



	public Facade() throws IOException {
		facade = MoodWatchFacade.instance();
		
		
	}

}
