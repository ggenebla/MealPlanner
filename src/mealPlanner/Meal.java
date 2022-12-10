package mealPlanner;

public class Meal {
	
	private double maxBudget;
	private double actualCost;
	private boolean maxBudgetGiven;
	
	

	public Meal() {
		super();
		this.maxBudgetGiven = false;
	}

	public double getMaxBudget() {
		return maxBudget;
	}

	public void setMaxBudget(double maxBudget) {
		this.maxBudget = maxBudget;
		this.maxBudgetGiven = true;
	}

	public double getActualCost() {
		return actualCost;
	}

	public void setActualCost(double actualCost) {
		this.actualCost = actualCost;
	}
	
	public boolean getMaxBudgetGiven() {
		return this.maxBudgetGiven;
	}
	
	public boolean isBudgetExceeded() {
		if (this.getMaxBudgetGiven() == true) {
			return actualCost > maxBudget;
		}
		return false;
	}

}
