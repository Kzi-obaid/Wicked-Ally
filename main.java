import java.util.Scanner;

public class HealthCalculator {

    // Function to calculate BMI
    public static double calculateBMI(double weightKg, double heightFeet) {
        double heightMeters = heightFeet * 0.3048; // convert feet to meters
        return weightKg / (heightMeters * heightMeters);
    }
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
