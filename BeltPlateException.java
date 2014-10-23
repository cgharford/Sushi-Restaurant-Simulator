package Sushi;



public class BeltPlateException extends Exception{
	
	private int position;
	private Plate plate_to_be_set;
	private Belt belt;
	
	public BeltPlateException(int position, Plate plate_to_be_set, Belt belt) {
		super("Illegal belt operation");
		
		this.position = position;
		this.plate_to_be_set = plate_to_be_set;
		this.belt = belt;		
	}

	public int getPosition() {
		return position;
	}

	public Plate getPlateToSet() {
		return plate_to_be_set;
	}
	
	public Belt getBelt() {
		return belt;
	}
	
}