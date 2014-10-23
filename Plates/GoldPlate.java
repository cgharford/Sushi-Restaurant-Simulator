package Sushi.Plates;

import Sushi.Interfaces.*;
import Sushi.Exceptions.*;
import Sushi.MainSushiProject.*;

public class GoldPlate extends PlateImpl {
	
	public GoldPlate(Sushi contents, double price) throws PlatePriceException {		
		super (price, Color.GOLD); 
		
		if (contents != null) {
			setContents(contents);
		}
		
		if (price < 5.0) {
			throw new IllegalArgumentException();
		}
	}
}
