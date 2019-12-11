package by.training.branching.LessonIfHome;


public class DateValidator {
    public static boolean isValid(Date date) {
        int[] dayPerMonth = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if (!date.isLeap() && date.getMonth() == 2 && date.getDay() == 29) {
            return false;
        }

        if (date.getYear() < 0) {
            return false;
        }
        if (date.getDay() <= 0 || date.getDay() > 32) {
            return false;
        }
        if (date.getMonth() <= 0 || date.getMonth() > 12) {
            return false;
        }
        if (dayPerMonth[date.getMonth() - 1] < date.getDay()) {
            return false;
        }
        return true;
    }
}
