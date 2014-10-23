package Sushi.Plates;

import Sushi.Interfaces.*;
import Sushi.Exceptions.*;
import Sushi.MainSushiProject.*;

public class BluePlate extends PlateImpl{

	public BluePlate(Sushi contents) throws PlatePriceException {
		super (4.0, Color.BLUE);
		if (contents != null) {
			setContents(contents);
		}
	}
}
