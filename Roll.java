package Sushi;

import java.util.HashMap;

public class Roll implements Sushi{
	
	private Ingredient[] ingredients;
	
	public Roll(Ingredient[] roll_ingredients) {

		//check array for null values
		for (Ingredient currentIngred: roll_ingredients) {
			if (currentIngred == null) {
				throw new RuntimeException("Null ingredient reference");
			}
		}
		//create a hashmap to search for repeated items
		HashMap<String, Ingredient> tempList = new HashMap<String, Ingredient>();
		
		String ingredName; 
		Ingredient oldItem;
		double amount;
		
		for (Ingredient currentIngred: roll_ingredients) {
			ingredName = currentIngred.getName();
			oldItem = tempList.get(ingredName);
			
			if (oldItem == null) {
				tempList.put(ingredName, currentIngred);
			}
			else {
				amount = oldItem.getAmount() + currentIngred.getAmount();
				tempList.remove(ingredName);
				tempList.put(ingredName, createIngredient(ingredName, amount));
			}
		}
	
		//search hashmap to see if there is enough seaweed
		//if not, add the right amount
		oldItem = tempList.get("seaweed");
		
		if (oldItem == null) {
			tempList.put("seaweed", createIngredient("seaweed", .1));
		}
		
		else if (oldItem.getAmount() < .1) {
			tempList.remove("seaweed");
			tempList.put("seaweed", createIngredient("seaweed", .1));
		}		
		
		//put ingredients from hashmap into array that the rest of the class  can use
		this.ingredients = new Ingredient[tempList.size()];
		int i = 0;
		for (String key: tempList.keySet()) {
			ingredients[i++] = tempList.get(key);
		}
	}   //end of constructor
	
	public Ingredient[] getIngredients() {
		Ingredient[] arrayCopy = new Ingredient[ingredients.length];
		arrayCopy = ingredients.clone();
		return arrayCopy;
	}
	
	public double getCost() {
		double totalCost = 0;
		for (Ingredient currentIngred: ingredients) {
			totalCost += currentIngred.getCost();
		}
		return totalCost;
	}
	
	public boolean hasRice() {
		boolean isRice = false;
		for (Ingredient currentIngred: ingredients) {
			if (currentIngred.isRice()) {
				isRice = true;
				break;
			}		
		}
		return isRice;		
	}
	
	public boolean hasShellfish() {
		boolean isShellfish = false;
		for (Ingredient currentIngred: ingredients) {
			if (currentIngred.isShellfish()) {
				isShellfish = true;
				break;
			}
		}
		return isShellfish;
	}
	
	public boolean isVegetarian() {
		boolean vegetarian = true;
		for (Ingredient currentIngred: ingredients) {			
			if (!currentIngred.isVegetarian()) {
				vegetarian = false;
				break;
			}
		}
		return vegetarian;
	}

	//takes the name of a repeated ingredient and creates a new ingredient
	private Ingredient createIngredient(String name, double amount) {
		switch (name) {
			case "avocado":
				return new Avocado(amount);
			case "crab":
				return new Crab(amount);
			case "eel":
				return new Eel(amount);
			case "rice":
				return new Rice(amount);
			case "salmon":
				return new Salmon(amount);
			case "seaweed":
				return new Seaweed(amount);
			case "shrimp":
				return new Shrimp(amount);
			case "tuna":
				return new Tuna(amount);
		}
		//this will not execute...just appeasing the compiler gods
		return null;
	}
	
}  //end of Roll
