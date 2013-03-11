package ca.dreamteam.recipebook.Models;

public class Ingredient {
	public String name;
	public Float amount;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public float getAmount() {
		return this.amount;
	}
	
	public void setAmount(float amount) {
		this.amount = amount;
	}
}
