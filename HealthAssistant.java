import java.util.*;

public class HealthAssistant {

    // Food database with nutrients per serving (in grams)
    static Map<String, double[]> foodDatabase = new HashMap<>() {{
        put("rice", new double[]{130, 2.5, 28, 0.3}); // {calories, protein, carbs, fats}
        put("chicken", new double[]{239, 27, 0, 14});
        put("salad", new double[]{15, 0.5, 3.6, 0.2});
        put("fish", new double[]{206, 22, 0, 12});
        put("milk", new double[]{42, 3.4, 5, 1});
    }};

    // Function to calculate BMI
    public static double calculateBMI(double weightKg, double heightCm) {
        double heightMeters = heightCm / 100;
        return weightKg / (heightMeters * heightMeters);
    }

    // Function to calculate BMR
    public static double calculateBMR(double weightKg, double heightCm, int age, int gender) {
        if (gender == 1) { // Male
            return 88.362 + (13.397 * weightKg) + (4.799 * heightCm) - (5.677 * age);
        } else { // Female
            return 447.593 + (9.247 * weightKg) + (3.098 * heightCm) - (4.330 * age);
        }
    }

    // Function to calculate total calorie target based on goal and timeline
    public static double calculateTargetCalories(double maintenanceCalories, int goal, int timeline) {
        double calorieAdjustment = 0;

        if (goal == 1) { // Weight Loss
            calorieAdjustment = -500; // Default reduction
            if (timeline == 1) calorieAdjustment -= 200; // Faster transformation
        } else if (goal == 2) { // Weight Gain
            calorieAdjustment = 500; // Default increase
            if (timeline == 1) calorieAdjustment += 200; // Faster transformation
        } else if (goal == 3) { // Muscle Building
            calorieAdjustment = 300; // Default increase
        }

        return maintenanceCalories + calorieAdjustment;
    }

    // Function to provide workout and diet recommendations
    public static void suggestPlan(double targetCalories, int goal) {
        System.out.println("\n### Recommended Plan ###");
        if (goal == 1) { // Weight Loss
            System.out.printf("- Daily Calorie Target: %.2f kcal\n", targetCalories);
            System.out.println("- Workout: Cardio 5x/week, strength training 2x/week.");
            System.out.println("- Diet: High-protein, low-carb meals with healthy fats.");
        } else if (goal == 2) { // Weight Gain
            System.out.printf("- Daily Calorie Target: %.2f kcal\n", targetCalories);
            System.out.println("- Workout: Strength training 5x/week, minimal cardio.");
            System.out.println("- Diet: High-calorie meals with balanced macronutrients.");
        } else if (goal == 3) { // Muscle Building
            System.out.printf("- Daily Calorie Target: %.2f kcal\n", targetCalories);
            System.out.println("- Workout: Progressive overload resistance training 6x/week.");
            System.out.println("- Diet: High-protein meals with moderate carbs and fats.");
        }
    }

    // Function to log food and calculate remaining nutrients
    public static void logFoodAndCalculate(String food, double quantity, Map<String, Double> remainingNutrients) {
        if (foodDatabase.containsKey(food.toLowerCase())) {
            double[] nutrients = foodDatabase.get(food.toLowerCase());
            double consumedCalories = nutrients[0] * quantity;
            double consumedProtein = nutrients[1] * quantity;
            double consumedCarbs = nutrients[2] * quantity;
            double consumedFats = nutrients[3] * quantity;

            remainingNutrients.put("calories", remainingNutrients.get("calories") - consumedCalories);
            remainingNutrients.put("protein", remainingNutrients.get("protein") - consumedProtein);
            remainingNutrients.put("carbs", remainingNutrients.get("carbs") - consumedCarbs);
            remainingNutrients.put("fats", remainingNutrients.get("fats") - consumedFats);

            System.out.printf("Logged: %.1f servings of %s.\n", quantity, food);
        } else {
            System.out.println("Food not found in the database.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input user data
        System.out.print("Enter your weight (kg): ");
        double weightKg = scanner.nextDouble();

        System.out.print("Enter your height (cm): ");
        double heightCm = scanner.nextDouble();

        System.out.print("Enter your age: ");
        int age = scanner.nextInt();

        System.out.print("Enter your gender (1 for Male, 2 for Female): ");
        int gender = scanner.nextInt();

        System.out.println("Select your activity level:\n1. Sedentary\n2. Light\n3. Moderate\n4. Active\n5. Very Active");
        int activityLevel = scanner.nextInt();

        System.out.println("Select your health goal:\n1. Weight Loss\n2. Weight Gain\n3. Muscle Building");
        int goal = scanner.nextInt();

        System.out.println("Select transformation timeline:\n1. 3 months\n2. 6 months\n3. 1 year");
        int timeline = scanner.nextInt();

        System.out.print("Do you have dietary restrictions? (1 for Yes, 2 for No): ");
        int dietaryRestriction = scanner.nextInt();

        System.out.print("Do you have food allergies? (1 for Yes, 2 for No): ");
        int foodAllergies = scanner.nextInt();

        // Calculate BMI, BMR, and calorie needs
        double bmi = calculateBMI(weightKg, heightCm);
        double bmr = calculateBMR(weightKg, heightCm, age, gender);
        double maintenanceCalories = bmr * (1 + 0.2 * (activityLevel - 1)); // Adjust for activity level
        double targetCalories = calculateTargetCalories(maintenanceCalories, goal, timeline);

        // Display results and suggestions
        System.out.printf("\nYour BMI: %.2f\n", bmi);
        System.out.printf("Your maintenance calories: %.2f kcal/day\n", maintenanceCalories);
        suggestPlan(targetCalories, goal);

        // Nutrient tracking
        Map<String, Double> remainingNutrients = new HashMap<>();
        remainingNutrients.put("calories", targetCalories);
        remainingNutrients.put("protein", 100.0); // Example target
        remainingNutrients.put("carbs", 250.0); // Example target
        remainingNutrients.put("fats", 70.0); // Example target

        System.out.println("\nLog your meals:");
        while (true) {
            System.out.print("Enter food (or 'done' to finish): ");
            String food = scanner.next();
            if (food.equalsIgnoreCase("done")) break;

            System.out.print("Enter quantity (servings): ");
            double quantity = scanner.nextDouble();

            logFoodAndCalculate(food, quantity, remainingNutrients);
        }

        // Display remaining nutrients
        System.out.println("\nRemaining Nutrients:");
        remainingNutrients.forEach((key, value) -> System.out.printf("- %s: %.2f\n", key, value));
        scanner.close();
    }
}