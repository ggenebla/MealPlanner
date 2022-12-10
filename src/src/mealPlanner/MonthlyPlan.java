package mealPlanner;

import java.util.ArrayList;

public class MonthlyPlan {

	private double maxBudget;
	private ArrayList<WeeklyPlan> weeklyPlans;
	private boolean maxBudgetGiven;
	
	public MonthlyPlan() {
		super();
		this.weeklyPlans = new ArrayList<WeeklyPlan>();
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
	
	public void addWeeklyPlan(WeeklyPlan weeklyPlan) {
		if (!this.weeklyPlans.contains(weeklyPlan)) {
			this.weeklyPlans.add(weeklyPlan);
		}
	}
	
	public double getTotalCost() {
		double totalMonthlyCost = 0;
		for (int i = 0; i < this.weeklyPlans.size(); i++) {
			WeeklyPlan weeklyPlan = this.weeklyPlans.get(i);
			totalMonthlyCost += weeklyPlan.getTotalCost();
		}
		return totalMonthlyCost;
	}
	
	
	
}
