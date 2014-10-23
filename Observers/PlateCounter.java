package Sushi.Observers;

import java.util.Observable;
import java.util.Observer;
import Sushi.MainSushiProject.*;

public class PlateCounter implements Observer {
	
	private int redCount;
	private int greenCount;
	private int blueCount;
	private int goldCount;
	
	//When we return from constructor, we assume that we're added as an observer to the belt
	public PlateCounter() {
		
		redCount = 0;
		greenCount = 0;
		blueCount = 0;
		goldCount = 0;
	}
	
	public void update(Observable belt, Object plate) {
		
		PlateEvent.EventType type = ((PlateEvent)plate).getType();
		Sushi.Interfaces.Plate.Color color = ((PlateEvent)plate).getPlate().getColor();
		
		if (type == PlateEvent.EventType.PLATE_REMOVED) {
			//update each color
			switch (color) {
				case RED: 
					redCount--;
					break;
				case GREEN:
					greenCount--;
					break;
				case BLUE:
					blueCount--;
					break;
				case GOLD:
					goldCount--;
					break;
			} //end of switch
		} //end of if clause
		
		else if (type == PlateEvent.EventType.PLATE_PLACED){
			//update each color
			switch (color) {
				case RED: 
					redCount++;
					break;
				case GREEN:
					greenCount++;
					break;
				case BLUE:
					blueCount++;
					break;
				case GOLD:
					goldCount++;
					break;
			} // end of switch
		}  //end of else if clause
	}
	
	public int getRedPlateCount() {
		return redCount;
	}	
	public int getGreenPlateCount() {
		return greenCount;		
	}
	public int getBluePlateCount() {
		return blueCount;
	}
	public int getGoldPlateCount() {
		return goldCount;
	}

}
