package Sushi;

public class PlatePriceException extends Exception {
	public PlatePriceException () {
		super("Illegal plate price");
	}
}