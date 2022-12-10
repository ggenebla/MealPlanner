package mealPlanner;

import java.util.HashMap;

public class MonthlyPlanManager {
	
	private HashMap<Integer, MonthlyPlan> monthlyPlans;
	
	public MonthlyPlanManager() {
		super();
		this.monthlyPlans = new HashMap<Integer, MonthlyPlan>();
	}
	
	public void createMonthlyBudget(Integer month, Double budget) {
		MonthlyPlan monthlyPlan = this.monthlyPlans.get(month);
		if (monthlyPlan == null) {
			monthlyPlan = new MonthlyPlan();
			this.monthlyPlans.put(month, monthlyPlan);
		}
		monthlyPlan.setMaxBudget(budget);
	}
	
	public void displayMonthlyDetails(Integer month) {
		MonthlyPlan monthlyPlan = this.monthlyPlans.get(month);
		if (monthlyPlan == null) {
			System.out.println("This month does not have any spending data.");
		}
		else {
			if (monthlyPlan.getMaxBudgetGiven() == true) {
				System.out.println("The budget for this month is: " + formatDecimalPlace(monthlyPlan.getMaxBudget()));
			}
			System.out.println("The total money spent this month is: " + formatDecimalPlace(monthlyPlan.getTotalCost()));
		}
	}
	
	public void addWeeklyPlan(WeeklyPlan weeklyPlan, Integer month) {
		MonthlyPlan monthlyPlan = this.monthlyPlans.get(month);
		if (monthlyPlan == null) {
			monthlyPlan = new MonthlyPlan();
			this.monthlyPlans.put(month, monthlyPlan);
		}
		monthlyPlan.addWeeklyPlan(weeklyPlan);
	}
	
	private String formatDecimalPlace(double cost) {
		return String.format("%.2f", cost);
	}

}
