package Sushi.Interfaces;

public interface Ingredient {
	
	double getAmount();
	double getCost();
	boolean isRice();
	boolean isShellfish();
	boolean isVegetarian();
	String getName();
}
