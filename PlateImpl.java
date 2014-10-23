package Sushi;

public class PlateImpl implements Plate{
	
	private Sushi sushi;
	private double price;
	private Color color;
	
	public PlateImpl(double price, Color color) {
		sushi = null;
		this.price = price;
		this.color = color;
	}

	public Sushi getContents() {
		return sushi;
	}
		
	public Sushi removeContents() {
		Sushi currentSushi = sushi;
		sushi = null;
		return currentSushi;     //if plate was empty, this returns null
	}
	
	public void setContents(Sushi s) throws PlatePriceException  {
		if (s == null) {
			throw new IllegalArgumentException("Sushi is null");
		}
		
		if (price <= s.getCost()) {
			throw new PlatePriceException();
		}
		
		sushi = s;
	}
	public boolean hasContents() {
		return (sushi != null);
	}
	
	public double getPrice() {
		return price;
	}
	
	public Plate.Color getColor() {
		return color;
	}
	
	public double getProfit() {
		if(sushi == null) {
			return 0.0;
		}
		else {
			return price - sushi.getCost();
		}
	}
	
}  //end of class
