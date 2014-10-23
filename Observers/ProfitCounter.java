package Sushi.Observers;

import java.util.Observable;
import java.util.Observer;
import Sushi.MainSushiProject.*;

public class ProfitCounter implements Observer {
	
	private double totalProfit;
	private int plateCount; 
	
	//When we return from constructor, we assume that we're added as an observer to the belt
	public ProfitCounter() {
		
		totalProfit = 0;
		plateCount = 0;
	}
	
	public void update(Observable belt, Object plate) {
		
		PlateEvent.EventType type = ((PlateEvent)plate).getType();
		
		switch (type) {
			case PLATE_REMOVED:
				totalProfit -= ((PlateEvent)plate).getPlate().getProfit(); 
				plateCount--;
				break;
			case PLATE_PLACED:
				totalProfit += ((PlateEvent)plate).getPlate().getProfit(); 
				plateCount++;
				break;
		}
	}
	
	public double getTotalBeltProfit() {
		return totalProfit;
	}
	public double getAverageBeltProfit() {
		return totalProfit / plateCount;
	}
}
