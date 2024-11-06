import java.util.Scanner;

public class HealthCalculator {

    // Function to calculate BMI
    public static double calculateBMI(double weightKg, double heightFeet) {
        double heightMeters = heightFeet * 0.3048; // convert feet to meters
        return weightKg / (heightMeters * heightMeters);
    }
