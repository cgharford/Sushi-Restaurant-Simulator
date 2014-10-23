package Sushi;

public class RedPlate extends PlateImpl{

	public RedPlate(Sushi contents) throws PlatePriceException {
		super (1.0, Color.RED);
		if (contents != null) {
			setContents(contents);
		}
	}
}
