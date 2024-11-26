import java.util.Scanner;

public class HealthCalculator {

    // Function to calculate BMI
    public static double calculateBMI(double weightKg, double heightCm) {
        return weightKg / (heightCm * heightCm / 10000); // height in cm, converting to meters
    }

    // Function to calculate BMR (Basal Metabolic Rate)
    public static double calculateBMR(double weightKg, double heightCm, int age, String gender) {
        if (gender.equalsIgnoreCase("male")) {
            return 88.362 + (13.397 * weightKg) + (4.799 * heightCm) - (5.677 * age);
        } else { // Female case
            return 447.593 + (9.247 * weightKg) + (3.098 * heightCm) - (4.330 * age);
        }
    }

    // Function to calculate maintenance calories based on activity level
    public static double calculateMaintenanceCalories(double bmr, int activityChoice) {
        switch (activityChoice) {
            case 1: return bmr * 1.2; // Sedentary
            case 2: return bmr * 1.375; // Light
            case 3: return bmr * 1.55; // Moderate
            case 4: return bmr * 1.725; // Active
            case 5: return bmr * 1.9; // Very Active
            default: // Invalid input
                System.out.println("Invalid activity level. Assuming sedentary as default.");
                return bmr * 1.2;
        }
    }

    // Function to suggest health programs, exercises, and diet plans
    public static void suggestProgram(double maintenanceCalories, int goalChoice) {
        System.out.println("\nBased on your goal, here are some recommendations:");

        switch (goalChoice) {
            case 1: // Weight loss
                double weightLossCalories = maintenanceCalories - 500;
                System.out.println("- Target daily calorie intake: " + String.format("%.2f", weightLossCalories) + " kcal.");
                System.out.println("- Suggested exercises:");
                System.out.println("  1. Cardio (e.g., running, cycling, swimming) for 30-60 minutes, 5 days a week.");
                System.out.println("  2. Strength training 2-3 days a week to maintain muscle mass.");
                System.out.println("  3. Yoga or stretching for relaxation and flexibility.");
                System.out.println("- Suggested Diet Plan:");
                System.out.println("  Breakfast: Oatmeal with almond milk and fresh fruits.");
                System.out.println("  Lunch: Grilled chicken salad with olive oil dressing and a side of quinoa.");
                System.out.println("  Snack: A handful of nuts or a low-calorie protein bar.");
                System.out.println("  Dinner: Baked fish with steamed vegetables and a small sweet potato.");
                break;
            case 2: // Weight gain
                double weightGainCalories = maintenanceCalories + 500;
                System.out.println("- Target daily calorie intake: " + String.format("%.2f", weightGainCalories) + " kcal.");
                System.out.println("- Suggested exercises:");
                System.out.println("  1. Strength training (e.g., weightlifting) 4-5 days a week.");
                System.out.println("  2. Avoid excessive cardio to conserve calories.");
                System.out.println("  3. Focus on compound lifts like squats, deadlifts, and bench presses.");
                System.out.println("- Suggested Diet Plan:");
                System.out.println("  Breakfast: Eggs, toast with avocado, and a banana.");
                System.out.println("  Lunch: Grilled chicken or beef with rice and a side of vegetables.");
                System.out.println("  Snack: Peanut butter sandwich or a protein shake with milk.");
                System.out.println("  Dinner: Salmon with pasta and a side of spinach.");
                break;
            case 3: // Muscle building
                double muscleBuildingCalories = maintenanceCalories + 300;
                System.out.println("- Target daily calorie intake: " + String.format("%.2f", muscleBuildingCalories) + " kcal.");
                System.out.println("- Suggested exercises:");
                System.out.println("  1. Resistance training (e.g., weightlifting) 5-6 days a week.");
                System.out.println("  2. Focus on progressive overload (gradually increase weights).");
                System.out.println("  3. Include adequate protein in your diet (1.6-2.2 g/kg of body weight).");
                System.out.println("- Suggested Diet Plan:");
                System.out.println("  Breakfast: Protein-rich smoothie (milk, protein powder, oats, and berries).");
                System.out.println("  Lunch: Grilled chicken, brown rice, and broccoli.");
                System.out.println("  Snack: Greek yogurt with nuts and honey.");
                System.out.println("  Dinner: Steak or tofu stir-fry with quinoa and vegetables.");
                break;
            default:
                System.out.println("Invalid goal. Please select a valid option.");
                break;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Welcome Message
        System.out.println("Welcome to Wickedally Healthscreen!");
        System.out.print("Please enter your name: ");
        String name = scanner.nextLine();

        // Input for BMI and BMR calculation
        System.out.print("Hello " + name + "! Please enter your weight (in kg): ");
        double weightKg = scanner.nextDouble();

        // Input for height
        System.out.println("Enter your height:");
        System.out.println("1. Feet and Inches");
        System.out.println("2. Meters");
        System.out.println("3. Centimeters");
        System.out.print("Choose height unit (1/2/3): ");
        int heightUnitChoice = scanner.nextInt();

        double heightCm = 0;

        if (heightUnitChoice == 1) {
            // Feet and Inches
            System.out.print("Enter feet: ");
            int feet = scanner.nextInt();
            System.out.print("Enter inches: ");
            int inches = scanner.nextInt();
            heightCm = (feet * 30.48) + (inches * 2.54);
        } else if (heightUnitChoice == 2) {
            // Meters
            System.out.print("Enter height in meters: ");
            double heightMeters = scanner.nextDouble();
            heightCm = heightMeters * 100;
        } else if (heightUnitChoice == 3) {
            // Centimeters
            System.out.print("Enter height in centimeters: ");
            heightCm = scanner.nextDouble();
        } else {
            System.out.println("Invalid input for height unit.");
            return;
        }

        System.out.print("Enter your age: ");
        int age = scanner.nextInt();

        // Gender selection
        System.out.println("Select your gender:");
        System.out.println("1. Male");
        System.out.println("2. Female");
        int genderChoice = scanner.nextInt();
        String gender = (genderChoice == 1) ? "male" : "female";

        // Calculate BMI and display result
        double bmi = calculateBMI(weightKg, heightCm);

        // Calculate BMR
        double bmr = calculateBMR(weightKg, heightCm, age, gender);

        // Activity level selection
        System.out.println("\nChoose your activity level:");
        System.out.println("1. Sedentary (little or no exercise)");
        System.out.println("2. Light (light exercise/sports 1-3 days a week)");
        System.out.println("3. Moderate (moderate exercise/sports 3-5 days a week)");
        System.out.println("4. Active (hard exercise/sports 6-7 days a week)");
        System.out.println("5. Very Active (very hard exercise/sports & a physical job)");
        int activityChoice = scanner.nextInt();

        double maintenanceCalories = calculateMaintenanceCalories(bmr, activityChoice);

        // Goal selection
        System.out.println("\nSelect your goal:");
        System.out.println("1. Weight Loss");
        System.out.println("2. Weight Gain");
        System.out.println("3. Muscle Building");
        int goalChoice = scanner.nextInt();

        // Output Results
        System.out.println("\n========== Health Report ==========");
        System.out.println("Name: " + name);
        System.out.printf("BMI: %.2f\n", bmi);
        System.out.printf("BMR: %.2f kcal/day\n", bmr);
        System.out.printf("Maintenance Calories: %.2f kcal/day\n", maintenanceCalories);

        // Provide suggestions based on the goal
        suggestProgram(maintenanceCalories, goalChoice);

        scanner.close();
    }
}
