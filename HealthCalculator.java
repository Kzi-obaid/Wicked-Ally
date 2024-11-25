import java.util.Scanner;

public class HealthCalculator {


    // Function to calculate BMI
    public static double calculateBMI(double weightKg, double heightFeet) {
        double heightMeters = heightFeet * 0.3048; // convert feet to meters
        return weightKg / (heightMeters * heightMeters);
    }

    // Function to calculate BMR (Basal Metabolic Rate)
    public static double calculateBMR(double weightKg, double heightCm, int age, String gender) {
        if (gender.equalsIgnoreCase("male")) {
            return 88.362 + (13.397 * weightKg) + (4.799 * heightCm) - (5.677 * age);
        } else if (gender.equalsIgnoreCase("female")) {
            return 447.593 + (9.247 * weightKg) + (3.098 * heightCm) - (4.330 * age);
        } else {
            System.out.println("Invalid gender input. Assuming male as default.");
            return 88.362 + (13.397 * weightKg) + (4.799 * heightCm) - (5.677 * age);
        }
    }

    // Function to calculate maintenance calories based on activity level
    public static double calculateMaintenanceCalories(double bmr, String activityLevel) {
        switch (activityLevel.toLowerCase()) {
            case "sedentary":
                return bmr * 1.2;
            case "light":
                return bmr * 1.375;
            case "moderate":
                return bmr * 1.55;
            case "active":
                return bmr * 1.725;
            case "very active":
                return bmr * 1.9;
            default:
                System.out.println("Invalid activity level. Assuming sedentary as default.");
                return bmr * 1.2;
        }
    }

    // Function to suggest health programs, exercises, and diet plans
    public static void suggestProgram(double maintenanceCalories, String goal) {
        System.out.println("\nBased on your goal, here are some recommendations:");

        if (goal.equalsIgnoreCase("weight loss")) {
            double targetCalories = maintenanceCalories - 500;
            System.out.println("- Target daily calorie intake: " + targetCalories + " kcal.");
            System.out.println("- Suggested exercises:");
            System.out.println("  1. Cardio (e.g., running, cycling, swimming) for 30-60 minutes, 5 days a week.");
            System.out.println("  2. Strength training 2-3 days a week to maintain muscle mass.");
            System.out.println("  3. Yoga or stretching for relaxation and flexibility.");
            System.out.println("- Suggested Diet Plan:");
            System.out.println("  Breakfast: Oatmeal with almond milk and fresh fruits.");
            System.out.println("  Lunch: Grilled chicken salad with olive oil dressing and a side of quinoa.");
            System.out.println("  Snack: A handful of nuts or a low-calorie protein bar.");
            System.out.println("  Dinner: Baked fish with steamed vegetables and a small sweet potato.");
        } else if (goal.equalsIgnoreCase("weight gain")) {
            double targetCalories = maintenanceCalories + 500;
            System.out.println("- Target daily calorie intake: " + targetCalories + " kcal.");
            System.out.println("- Suggested exercises:");
            System.out.println("  1. Strength training (e.g., weightlifting) 4-5 days a week.");
            System.out.println("  2. Avoid excessive cardio to conserve calories.");
            System.out.println("  3. Focus on compound lifts like squats, deadlifts, and bench presses.");
            System.out.println("- Suggested Diet Plan:");
            System.out.println("  Breakfast: Eggs, toast with avocado, and a banana.");
            System.out.println("  Lunch: Grilled chicken or beef with rice and a side of vegetables.");
            System.out.println("  Snack: Peanut butter sandwich or a protein shake with milk.");
            System.out.println("  Dinner: Salmon with pasta and a side of spinach.");
        } else if (goal.equalsIgnoreCase("muscle building")) {
            double targetCalories = maintenanceCalories + 300;
            System.out.println("- Target daily calorie intake: " + targetCalories + " kcal.");
            System.out.println("- Suggested exercises:");
            System.out.println("  1. Resistance training (e.g., weightlifting) 5-6 days a week.");
            System.out.println("  2. Focus on progressive overload (gradually increase weights).");
            System.out.println("  3. Include adequate protein in your diet (1.6-2.2 g/kg of body weight).");
            System.out.println("- Suggested Diet Plan:");
            System.out.println("  Breakfast: Protein-rich smoothie (milk, protein powder, oats, and berries).");
            System.out.println("  Lunch: Grilled chicken, brown rice, and broccoli.");
            System.out.println("  Snack: Greek yogurt with nuts and honey.");
            System.out.println("  Dinner: Steak or tofu stir-fry with quinoa and vegetables.");
        } else {
            System.out.println("Invalid goal. Please select 'weight loss', 'weight gain', or 'muscle building'.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input for BMI and BMR calculation
        System.out.print("Enter your weight (in kg): ");
        double weightKg = scanner.nextDouble();

        System.out.print("Enter your height (in feet): ");
        double heightFeet = scanner.nextDouble();

        System.out.print("Enter your age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter your gender (male/female): ");
        String gender = scanner.nextLine();

        // Convert height to cm for BMR calculation
        double heightCm = heightFeet * 30.48;

        // Calculate BMI and display result
        double bmi = calculateBMI(weightKg, heightFeet);
        System.out.printf("Your BMI is: %.2f\n", bmi);

        // Calculate BMR and ask for activity level to determine maintenance calories
        double bmr = calculateBMR(weightKg, heightCm, age, gender);

        System.out.println("\nChoose your activity level:");
        System.out.println("1. Sedentary (little or no exercise)");
        System.out.println("2. Light (light exercise/sports 1-3 days a week)");
        System.out.println("3. Moderate (moderate exercise/sports 3-5 days a week)");
        System.out.println("4. Active (hard exercise/sports 6-7 days a week)");
        System.out.println("5. Very Active (very hard exercise/sports & a physical job)");
        System.out.print("Enter your choice (e.g., 'sedentary', 'light', 'moderate', 'active', 'very active'): ");
        String activityLevel = scanner.nextLine();

        double maintenanceCalories = calculateMaintenanceCalories(bmr, activityLevel);
        System.out.printf("Your maintenance calories are: %.2f kcal per day\n", maintenanceCalories);

        // Ask for goal and provide suggestions
        System.out.print("\nEnter your goal (e.g., 'weight loss', 'weight gain', 'muscle building'): ");
        String goal = scanner.nextLine();

        suggestProgram(maintenanceCalories, goal);
        scanner.close();
    }
}


