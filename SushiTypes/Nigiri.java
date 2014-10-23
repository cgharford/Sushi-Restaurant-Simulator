package Sushi.SushiTypes;

import Sushi.Interfaces.*;
import Sushi.Ingredients.*;
import Sushi.MainSushiProject.*;

public class Nigiri implements Sushi{

	public enum NigiriType {TUNA, SALMON, EEL, CRAB, SHRIMP};
	
		private NigiriType type;
		private IngredientImpl fishIngred, rice;
	
	public Nigiri(NigiriType type) {
	
		this.type = type;
		
		switch (type) {
		case TUNA: 
			fishIngred = new Tuna(.75);
			break;
		case SALMON:
			fishIngred = new Salmon(.75);
			break;
		case EEL:
			fishIngred = new Eel(.75);
			break;
		case CRAB:
			fishIngred = new Crab(.75);
			break;
		case SHRIMP:
			fishIngred = new Shrimp(.75);
			break;
		}
		rice = new Rice(.5);
	}
	
	public Ingredient[] getIngredients() {
		Ingredient[] ingredArray = new Ingredient[2];
		ingredArray[0] = fishIngred;
		ingredArray[1] = rice;
		return ingredArray;
	}
	
	public double getCost() {
		return fishIngred.getCost() + rice.getCost();
	}
	
	public boolean hasRice() {
		return true;
	}
	
	public boolean hasShellfish() {
		return fishIngred.isShellfish();
	}
	
	public boolean isVegetarian() {
		return false;
	}
}
