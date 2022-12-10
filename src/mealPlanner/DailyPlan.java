package mealPlanner;

import java.util.ArrayList;

public class DailyPlan {
	
	private double maxBudget;
	private ArrayList<Meal> meals;
	private boolean maxBudgetGiven;
	
	public DailyPlan() {
		super();
		this.meals = new ArrayList<Meal>();
		this.maxBudgetGiven = false;
	}

	
	public double getMaxBudget() {
		return maxBudget;
	}
	
	public void setMaxBudget(double maxBudget) {
		this.maxBudget = maxBudget;
		this.maxBudgetGiven = true;
	}
	
	public boolean getMaxBudgetGiven() {
		return this.maxBudgetGiven;
	}
	
	public void addMeal(Meal meal) {
		this.meals.add(meal);
	}
	
	public double getTotalCost() {
		double totalDailyCost = 0;
		for (int i = 0; i < this.meals.size(); i++) {
			Meal meal = this.meals.get(i);
			totalDailyCost += meal.getActualCost();
		}
		return totalDailyCost;
	}
	
	public double getAverageMealCost() {
		double averageMealCost = (getTotalCost() / this.meals.size());
		return averageMealCost;
	}
	
	

}
