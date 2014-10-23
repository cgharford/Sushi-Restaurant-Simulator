package Sushi;

import java.util.*;

public class Belt extends Observable implements Iterable<Plate> {
		
	private int size;
	private int count;					//counts the number of occupied positions in array
	private Plate[] beltArray;
	private Customer[] customers;
		
	public Belt(int size) {
		if (size < 1) {
			throw new IllegalArgumentException();
		}
			
		this.size = size;
		count = 0;
		beltArray = new Plate[size];
		customers = new Customer[size];
	}
	
	public int getSize(){
		return size;
	}
	
	//Returns the plate at specified position
	public Plate getPlateAtPosition(int position) {
		
		//Converts all possible positions to correct array index.
		//This calculation works for negative numbers as well as positive.
		position = ((position % size)+ size)% size;
		
		return beltArray[position];              //returns null if nothing there
	}
	
	//Sets plate at specified position
	public void setPlateAtPosition(Plate plate, int position) throws BeltPlateException {
		if (plate == null) {
			throw new IllegalArgumentException();
		}	
		
		//Converts all possible positions to correct array index.
		//This calculation works for negative numbers as well as positive.
		position = ((position % size)+ size)% size;
				
		if  (beltArray[position] != null) {
			throw new BeltPlateException(position, beltArray[position], this); 
		}
		
		beltArray[position] = plate;	
		count++;
	}
	
	//Sets specified position to null
	public void clearPlateAtPosition(int position) {
		//Converts all possible positions to correct array index.
		//This calculation works for negative numbers as well as positive.
		position = ((position % size)+ size)% size;
				
		Plate removedPlate = beltArray[position];
		
		if (removedPlate != null) {
			beltArray[position] = null;	
			//Create Plate event class to notify the observers
			PlateEvent event = new PlateEvent(PlateEvent.EventType.PLATE_REMOVED, removedPlate, position); 	
			notifyHelper(event);
		}
	}
	
	//Removes plate at specified position and returns it
	public Plate removePlateAtPosition(int position) {
		Plate plate;
		
		position = ((position % size)+ size)% size;
		
		//Get plate contents and check for errors.
		plate = getPlateAtPosition(position);
		if (beltArray[position] == null) {
			throw new NoSuchElementException();
		}	
		
		clearPlateAtPosition(position);
		return plate;
	}
	
	//Checks current position to see if plate can be placed there
	//If not, keep checking next highest place for an empty position
	//If belt is full, throw an exception
	public int setPlateNearestToPosition(Plate plate, int position) throws BeltFullException {	
		for (int i = 0; i < size; i++) {
			if (getPlateAtPosition(position) == null) {
				position = ((position % size)+ size)% size; 	//Adjust position for out of bound array index
				beltArray[position] = plate;
				
				//Create Plate event class to notify the observers
				PlateEvent event = new PlateEvent(PlateEvent.EventType.PLATE_PLACED, plate, position); 		
				notifyHelper(event);
				
				count++;
				return position;							    //Expected exit
			}
			position++;
		}
		
		throw new BeltFullException(this);					    //Executes only if belt is full
	}
	
	//Checks count variable that has been incremented every time a plate is placed on the belt
	public boolean isEmpty() {
		return (count == 0);
	}
		
	//Returns BeltIterator object from position 0
	public Iterator<Plate> iterator() {
		return iteratorFromPosition(0);
	}
	
	//Returns BeltIter object from specified position
	public Iterator<Plate> iteratorFromPosition(int position) {
		BeltIterator iteratorObject = new BeltIterator(this, position);
		return iteratorObject;
	}
	
	//Returns BeltIter object from specified position and specified maximum price
	public Iterator<Plate> iteratorFromPosition(int position, double max_price) {
		BeltIterator iteratorObject = new BeltIterator(this, position, max_price);
		return iteratorObject;
	}
	
	//Returns BeltIter object from specified position and specified color
	public Iterator<Plate> iteratorFromPosition(int position, Plate.Color color_filter) {
		BeltIterator iteratorObject = new BeltIterator(this, position, color_filter);
		return iteratorObject;
	}
	
	//Shifts all of the plate objects up one on the belt
	public void rotate() {

		Plate temp = beltArray[size - 1];
		for (int i = (size - 2); i >= 0; i--) {
			beltArray[i + 1] = beltArray[i];
		}
		
		beltArray[0] = temp;
	}
	
	//Optional helper method that marks state change for parent class and notifies observers
	private void notifyHelper(PlateEvent event) {
		setChanged();
		notifyObservers(event);
	}
	
	public void registerCustomerAtPosition(Customer c, int position) {
		//Immediately correct position for use within method
		position = ((position % size)+ size)% size; 
		if (customers[position] != null) {
			throw new RuntimeException();
		}
		for (Customer trial: customers) {
			if (trial == c) {
				throw new RuntimeException();
			} //end of if
		} //end of for
		customers[position] = c;
	}
	
	public Customer unregisterCustomerAtPosition(int position) {
		//Immediately correct position for use within method
		position = ((position % size)+ size)% size; 
		Customer removedCustomer = customers[position];
		customers[position] = null;
		return removedCustomer;
	}
}