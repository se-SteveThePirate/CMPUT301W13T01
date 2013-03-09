package ca.dreamteam.recipebook.Models;

public class Ingredient {
	public String name;
	public Integer amount;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getAmount() {
		return this.amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
}
