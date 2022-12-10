package mealPlanner;

import java.util.ArrayList;

public class WeeklyPlan {
	
	private double maxBudget;
	private ArrayList<DailyPlan> dailyPlans;
	private boolean maxBudgetGiven;
	
	public WeeklyPlan() {
		super();
		this.dailyPlans = new ArrayList<DailyPlan>();
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
	
	public void addDailyPlan(DailyPlan dailyPlan) {
		if (!this.dailyPlans.contains(dailyPlan)) {
			dailyPlans.add(dailyPlan);
		}
	}
	
	public double getTotalCost() {
		double totalWeeklyCost = 0;
		for (int i = 0; i < this.dailyPlans.size(); i++) {
			DailyPlan dailyPlan = this.dailyPlans.get(i);
			totalWeeklyCost += dailyPlan.getTotalCost();
		}
		return totalWeeklyCost;
	}

}
