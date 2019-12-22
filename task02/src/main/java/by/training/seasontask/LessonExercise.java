package by.training.seasontask;

import java.util.Scanner;

public class LessonExercise {
    private final static String WINTER = "Winter";
    private final static String SUMMER = "Summer";
    private final static String AUTUMN = "Autumn";
    private final static String SPRING = "Spring";

    public static void main(String[] args) {
        System.out.print("Enter month: ");
        Scanner sc = new Scanner(System.in);
        switch (sc.next().toLowerCase()) {
            case "december":
            case "january":
            case "february":
                System.out.println("This is - " + WINTER);
                break;
            case "june":
            case "july":
            case "august":
                System.out.println("This is - " + SUMMER);
                break;
            case "september":
            case "october":
            case "november":
                System.out.println("This is - " + AUTUMN);
                break;
            case "march":
            case "april":
            case "may":
                System.out.println("This is - " + SPRING);
                break;
            default:
                throw new IllegalArgumentException("such a month does not exist");

        }
    }
}
