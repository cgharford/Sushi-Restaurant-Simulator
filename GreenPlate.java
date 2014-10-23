package Sushi;

public class GreenPlate extends PlateImpl{

	public GreenPlate(Sushi contents) throws PlatePriceException {
		super (2.0, Color.GREEN);
		if (contents != null) {
			setContents(contents);
		}
	}
}
