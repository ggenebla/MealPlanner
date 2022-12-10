package mealPlanner;

import java.util.HashMap;

/** 
* Creates a hashmap holding weekly spending and budgeting details
*/
public class WeeklyPlanManager {
	
	private HashMap<String, WeeklyPlan> weeklyPlans;
	
	public WeeklyPlanManager() {
		super();
		this.weeklyPlans = new HashMap<String, WeeklyPlan>();
	}
	
	public void createWeeklyBudget(Integer week, Integer month, Double budget) {
		String key = month + "-" + week;
		WeeklyPlan weeklyPlan = this.weeklyPlans.get(key);
		if (weeklyPlan == null) {
			weeklyPlan = new WeeklyPlan();
			this.weeklyPlans.put(key, weeklyPlan);
		}
		weeklyPlan.setMaxBudget(budget);
	}
	
	public void displayWeeklyDetails(Integer week, Integer month) {
		String key = month + "-" + week;
		WeeklyPlan weeklyPlan = this.weeklyPlans.get(key);
		if (weeklyPlan == null) {
			System.out.println("This week does not have any spending data.");
		}
		else {
			if (weeklyPlan.getMaxBudgetGiven() == true) {
				System.out.println("The budget for this week is: " + formatDecimalPlace(weeklyPlan.getMaxBudget()));
			}
			System.out.println("The total money spent this week is: " + formatDecimalPlace(weeklyPlan.getTotalCost()));
		}
	}
	
	public void addDailyPlan(DailyPlan dailyPlan, Integer week, Integer month) {
		String key = month + "-" + week;
		WeeklyPlan weeklyPlan = this.weeklyPlans.get(key);
		if (weeklyPlan == null) {
			weeklyPlan = new WeeklyPlan();
			this.weeklyPlans.put(key, weeklyPlan);
		}
		weeklyPlan.addDailyPlan(dailyPlan);
	}
	
	public WeeklyPlan returnWeeklyPlan(Integer week, Integer month) {
		String key = month + "-" + week;
		WeeklyPlan weeklyPlan = this.weeklyPlans.get(key);
		if (weeklyPlan == null) {
			weeklyPlan = new WeeklyPlan();
			this.weeklyPlans.put(key, weeklyPlan);
		}
		
		return weeklyPlan;
	}
	
	private String formatDecimalPlace(double cost) {
		return String.format("%.2f", cost);
	}


}
