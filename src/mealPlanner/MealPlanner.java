package mealPlanner;

import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MealPlanner {
	
	private DailyPlanManager dailyPlanManager;
	private MonthlyPlanManager monthlyPlanManager;
	private WeeklyPlanManager weeklyPlanManager;

	public static void main(String[] args) {
		
		MealPlanner mealPlanner = new MealPlanner();
		
		mealPlanner.displayWelcome();
		mealPlanner.displayMenu();
		System.out.print("\nPlease choose one of the options above (1-6): ");
		
		Scanner scanner = new Scanner(System.in);
		int userChoice;
		
		do {
			
			userChoice = scanner.nextInt();
			mealPlanner.clearConsole();
		
			switch(userChoice) {
			  case 1:
			    mealPlanner.enterMealCost(scanner);
			    break;
			  case 2:
			    mealPlanner.enterDailyBudget(scanner);
			    break;
			  case 3:
				mealPlanner.enterWeeklyBudget(scanner);
				break;
			  case 4:
				mealPlanner.enterMonthlyBudget(scanner);
				break;
			  case 5:
				mealPlanner.getSpendingDetails(scanner);
				break;
			  case 6:
				mealPlanner.exit();
				break;
			  default:
			    mealPlanner.handleInvalidChoice(userChoice, scanner);
			}
			
			mealPlanner.askToRepeat(scanner, mealPlanner);
		
		} while (userChoice > 0 || userChoice < 7);

	}
	
	public MealPlanner() {
		super();
		this.dailyPlanManager = new DailyPlanManager();
		this.monthlyPlanManager = new MonthlyPlanManager();
		this.weeklyPlanManager = new WeeklyPlanManager();
	}
	
	
	private void displayMenu() {
		
		System.out.println("1. Enter meal cost");
		System.out.println("2. Enter budget for specific date");
		System.out.println("3. Enter budget for week");
		System.out.println("4. Enter budget for month");
		System.out.println("5. Get spending details for a specific date, week, or month");
		System.out.println("6. Exit");
		
	}
	
	private void displayWelcome() {
		System.out.println("Welcome to the meal planner application. This application allows you to budget your meals! \n");
		//System.out.println("Please choose one of the options below: \n\n");
	}
	
	private void exit() {
		System.out.println("Thank you for using the Meal Planner application.");
		System.exit(0);
	}
	
	private void handleInvalidChoice(int userChoice, Scanner scanner) {
		while (userChoice < 1 || userChoice > 6) {
			System.out.println("You have entered an invalid choice. Please choose option 1-6.");
			userChoice = scanner.nextInt();
		}
	}
	
	private void askToRepeat(Scanner scanner, MealPlanner mealPlanner) {
		System.out.println("\n\nDo you want to choose another option? (Y/N)");
		String repeatChoice = scanner.next();
		if (repeatChoice.equalsIgnoreCase("Y")) {
			mealPlanner.clearConsole();
			mealPlanner.displayMenu();
		}
		else {
			mealPlanner.exit();
		}
	}
	
	private void enterMealCost(Scanner scanner) {
		boolean repeatChoiceDifferentDay = false;
		do {
			System.out.println("Please enter a date in this format: mm/dd/yyyy");
			Date userDate = formatDate(scanner);
			boolean repeatChoiceSameDay = false;
			do {
				System.out.println("How much did you spend on this meal?");
				double mealCost = scanner.nextDouble();
				this.dailyPlanManager.createMealCost(userDate, mealCost, monthlyPlanManager, weeklyPlanManager);
				
				System.out.println("Do you want to add another meal to this day? (Y/N)");
				String repeatChoice = scanner.next();
				if (repeatChoice.equalsIgnoreCase("Y")) {
					repeatChoiceSameDay = true;
				}
				else {
					repeatChoiceSameDay = false;
				}
				
			} while(repeatChoiceSameDay == true);
			
			System.out.println("Do you want to add a meal to another day? (Y/N)");
			String repeatChoice = scanner.next();
			if (repeatChoice.equalsIgnoreCase("Y")) {
				repeatChoiceDifferentDay = true;
			}
			else {
				repeatChoiceDifferentDay = false;
			}
		} while (repeatChoiceDifferentDay == true);
	}
	
	private void enterDailyBudget(Scanner scanner) {
		System.out.println("Please enter a date in this format: mm/dd/yyyy");
		Date userDate = formatDate(scanner);
		System.out.println("Please enter your budget for this date: ");
		Double dailyBudget = scanner.nextDouble();
		this.dailyPlanManager.createDailyBudget(userDate, dailyBudget);
	}
	
	private void enterWeeklyBudget(Scanner scanner) {
		System.out.println("Please enter a month (1-12) representing January through December.");
		int month = formatMonth(scanner);
		System.out.println("Which week are you setting a budget for? (1-4)");
		int week = formatWeek(scanner);
		System.out.println("Please enter your budget for this week: ");
		Double weeklyBudget = scanner.nextDouble();
		this.weeklyPlanManager.createWeeklyBudget(week, month, weeklyBudget);
	}
	
	private void enterMonthlyBudget(Scanner scanner) {
		System.out.println("Please enter a month (1-12) representing January through December.");
		int month = formatMonth(scanner);
		System.out.println("Please enter your budget for this month: ");
		Double monthlyBudget = scanner.nextDouble();
		this.monthlyPlanManager.createMonthlyBudget(month, monthlyBudget);
	}
	
	private void getSpendingDetails(Scanner scanner) {
		System.out.println("Do you want the details for a specific date, week, or month?");
		System.out.println("1. Specific Date");
		System.out.println("2. Specific Week");
		System.out.println("3. Specific Month");
		
		int userChoice = scanner.nextInt();
		Date userDate = null;
		int month = -1;
		int week = -1;
		
		switch(userChoice) {
		  case 1:
			System.out.println("Please enter a date in this format: mm/dd/yyyy");
			userDate = formatDate(scanner);
			this.dailyPlanManager.displayDailyDetails(userDate);
		    break;
		  case 2:
			System.out.println("Please enter a month (1-12) representing January through December.");
			month = formatMonth(scanner);
			System.out.println("Which week do you want spending details for? (1-4)");
			week = formatWeek(scanner);
			this.weeklyPlanManager.displayWeeklyDetails(week, month);
		    break;
		  case 3:
			System.out.println("Please enter a month (1-12) representing January through December.");
			month = formatMonth(scanner);
			this.monthlyPlanManager.displayMonthlyDetails(month);
			break;
		  default:
			System.out.println("Please enter a date in this format: mm/dd/yyyy");
			userDate = formatDate(scanner);
		}
		
	}
	
	private Date formatDate(Scanner scanner) {
		Date userDate = null;
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		formatter.setLenient(false);
		boolean isValidDate = false;
		do {
			String date = scanner.next();
			try {
				userDate = formatter.parse(date);
				isValidDate = true;
			} catch (ParseException e) {
				System.out.println("You have entered the date in the wrong format. Please enter again.");
			}
		} while (isValidDate == false);
		
		return userDate;
	}
	
	private int formatMonth(Scanner scanner) {
		boolean isValidMonth = false;
		int month = -1;
		do {
			month = scanner.nextInt();
			if (month > 0 && month < 13) {
				isValidMonth = true;
			}
			else {
				System.out.println("Month is invalid. Please enter again.");
			}
			
		} while (isValidMonth == false);
		
		return month;
	}
	
	private int formatWeek(Scanner scanner) {
		boolean isValidWeek = false;
		int week = -1;
		do {
			week = scanner.nextInt();
			if (week > 0 && week < 5) {
				isValidWeek = true;
			}
			else {
				System.out.println("There are only 4 weeks in a month! Please enter a a valid week. (1-4)");
			}
		} while(isValidWeek == false);
		
		return week;
	}
	
	public void clearConsole() {
		System.out.println("\n\n\n");
	}
	
	

}
