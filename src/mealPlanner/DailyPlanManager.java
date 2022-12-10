package mealPlanner;

import java.util.HashMap;
import java.util.Calendar;
import java.util.Date;

public class DailyPlanManager {

	private HashMap<Date, DailyPlan> dailyPlans;
	
	public DailyPlanManager() {
		super();
		this.dailyPlans = new HashMap<Date, DailyPlan>();
	}
	
	public void createDailyBudget(Date date, Double budget) {
		DailyPlan dailyPlan = this.dailyPlans.get(date);
		//checking to see if that date exists in hashmap
		if (dailyPlan == null) {
			//the date does not exist yet in the hashmap
			dailyPlan = new DailyPlan();
			this.dailyPlans.put(date, dailyPlan);
		}
		dailyPlan.setMaxBudget(budget);
	}
	
	public void createMealCost(Date date, Double mealCost, MonthlyPlanManager monthlyPlanManager, WeeklyPlanManager weeklyPlanManager) {
		DailyPlan dailyPlan = this.dailyPlans.get(date);
		//checking to see if that date exists in hashmap
		if (dailyPlan == null) {
			//the date does not exist yet in the hashmap
			dailyPlan = new DailyPlan();
			this.dailyPlans.put(date, dailyPlan);
		}
		
		Meal newMeal = new Meal();
		newMeal.setActualCost(mealCost);
		dailyPlan.addMeal(newMeal);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int month = calendar.get(Calendar.MONTH) + 1;
		int year = calendar.get(Calendar.YEAR);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.set(year, month - 1, day);
		int week = calendar.get(Calendar.WEEK_OF_MONTH);
		
		weeklyPlanManager.addDailyPlan(dailyPlan, week, month);
		WeeklyPlan weeklyPlan = weeklyPlanManager.returnWeeklyPlan(week, month);
		monthlyPlanManager.addWeeklyPlan(weeklyPlan, month);
	}
	
	public void displayDailyDetails(Date date) {
		DailyPlan dailyPlan = this.dailyPlans.get(date);
		if (dailyPlan == null) {
			System.out.println("This date does not have any spending data.");
		}
		else {
			if (dailyPlan.getMaxBudgetGiven() == true) {
				System.out.println("The budget for this day is: " + formatDecimalPlace(dailyPlan.getMaxBudget()));
			}
			System.out.println("The total money spent this day is: " + formatDecimalPlace(dailyPlan.getTotalCost()));
			System.out.println("The average meal cost for this day is: " + formatDecimalPlace(dailyPlan.getAverageMealCost()));
		}
	}
	
	private String formatDecimalPlace(double cost) {
		return String.format("%.2f", cost);
	}
	
}
