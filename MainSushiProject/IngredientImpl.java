package Sushi.MainSushiProject;

import Sushi.Interfaces.*;

public class IngredientImpl implements Ingredient {

	
	protected double amount; 
	protected String name;
	protected double pricePerOunce;
	protected boolean rice;
	protected boolean shellfish; 
	protected boolean vegetarian; 
	
	public IngredientImpl (double amount, 
							String name, 
							double pricePerOunce, 
							boolean rice, 
							boolean shellfish, 
							boolean vegetarian) {
		
		if (amount <= 0) {
			throw new RuntimeException("Illegal ingredient");
		}
		
		this.amount = amount;
		this.name = name; 
		this.pricePerOunce = pricePerOunce;
		this.rice = rice; 
		this.shellfish = shellfish;
		this.vegetarian = vegetarian; 
	}
	
	public double getAmount() {
		return amount;
	}
	
	public double getCost(){
		return (amount * pricePerOunce); 
	}

	public boolean isRice() {
		return rice;
	}
	
	public boolean isShellfish() {
		return shellfish;
	}
	
	public boolean isVegetarian() {
		return vegetarian;
	}
	
	public String getName() {
		return name;
	}
	
}
	
	
	
