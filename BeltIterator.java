package Sushi;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class BeltIterator implements Iterator<Plate> { 
	
	private enum filterType {NONE, PRICE, COLOR};

	private Belt belt;
	private int currentPosition;
	private int lastReturned;
	private filterType filter;
	private double max_price;
	private Plate.Color color_filter;

	public BeltIterator(Belt belt, int start_position) {
	
		this.belt = belt;
		currentPosition = start_position;
		lastReturned = -1;
		filter = filterType.NONE;
	}
	
	//Constructor will only iterate over plates that have a price less than or equal to the specified max price
	public BeltIterator(Belt belt, int start_position, double max_price) {
		this.belt = belt;
		currentPosition = start_position;
		lastReturned = -1;		
		filter = filterType.PRICE;
		this.max_price = max_price;
	}
	
	//Constructor will only iterate over plates that have a color that matches the specified color value.
	public BeltIterator(Belt belt, int start_position, Plate.Color color_filter) {
		this.belt = belt;
		currentPosition = start_position;
		lastReturned = -1;		
		filter = filterType.COLOR;
		this.color_filter = color_filter;
	}

	//First checks enum value to see what type of plates need to be iterated over in belt
	//Checks isEmpty() method to see if there are any plate objects to be iterated
	public boolean hasNext() {
		Plate plate;		
		
		switch (filter) {
			case NONE: 
				return (!belt.isEmpty());
				
			case PRICE: 
				for (int i = 0; i < belt.getSize(); i++) {
					plate = belt.getPlateAtPosition(i);
					if ((plate == null) || (plate.getPrice() > max_price)) {
						continue;
					}
					return true;
				}
				return false;
			
			case COLOR: 
				for (int i = 0; i < belt.getSize(); i++) {
					plate = belt.getPlateAtPosition(i);
					if ((plate == null) || (plate.getColor() != color_filter)) {
						continue;
					}
					return true;
				}
				return false;
		}
		return false; 							//This will not execute
	}
	
	//First checks enum value to see what type of plates need to be iterated over in belt
	//Retrieves and returns next plate object from belt
	public Plate next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		
		switch (filter) {
			case NONE:  
				while (true) {
					Plate plate = belt.getPlateAtPosition(currentPosition++);
					if (plate != null) {
						lastReturned = currentPosition - 1;
						return plate;
					}
				}
				
			case PRICE:
				while (true) {
					Plate plate = belt.getPlateAtPosition(currentPosition++);
					if ((plate != null) && (plate.getPrice() <= max_price)) {
						lastReturned = currentPosition - 1;
						return plate;
					}
				}
				
			case COLOR: 
				while (true) {
					Plate plate = belt.getPlateAtPosition(currentPosition++);
					if ((plate != null) && (plate.getColor() == color_filter)) {
						lastReturned = currentPosition - 1;
						return plate;
					}
				}
		}
		
		return null;						//This will not execute
	}
	
	//Removes the last element that has been iterated
	//Last element recorded as a value in a variable
	
	//If the next method has not yet been called, or the remove method has already
	//been called after the last call to the next method, throws an exception
	public void remove() {
			if (lastReturned < 0) {
			throw new IllegalStateException();
		}
		
		belt.removePlateAtPosition(lastReturned);
		
		lastReturned = -1;					//Prevent consecutive remove()s with no intermediate next()
	}
}
