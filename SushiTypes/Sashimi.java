package Sushi.SushiTypes;

import Sushi.Interfaces.*;
import Sushi.Ingredients.*;
import Sushi.MainSushiProject.*;

public class Sashimi implements Sushi{

	public enum SashimiType {TUNA, SALMON, EEL, CRAB, SHRIMP};
	
		private SashimiType type;
		private IngredientImpl ingred;
		
	public Sashimi(SashimiType type) {
		
		this.type = type;
		
		switch (type) {
		case TUNA: 
			ingred = new Tuna(.75);
			break;
		case SALMON:
			ingred = new Salmon(.75);
			break;
		case EEL:
			ingred = new Eel(.75);
			break;
		case CRAB:
			ingred = new Crab(.75);
			break;
		case SHRIMP:
			ingred = new Shrimp(.75);
			break;
		}
	}
	
	public Ingredient[] getIngredients() {
		Ingredient[] ingredArray = new Ingredient[1];
		ingredArray[0] = ingred;
		return ingredArray;
	}
	
	public double getCost() {
		return ingred.getCost();
	}
	
	public boolean hasRice() {
		return ingred.isRice();
	}
	
	public boolean hasShellfish() {
		return ingred.isShellfish();
	}
	
	public boolean isVegetarian() {
		return ingred.isVegetarian();
	}
}
