package by.training.lessonIfTraining;


public class DateValidator {
    public boolean isValid(Data data) {
        int[] dayPerMonth = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int notLeapYear = data.isLeap() ? 0 : 1;
        if (data.getYear() < 0) {
            return false;
        }
        if (data.getMonth() < 0 || data.getMonth() > 13) {
            return false;
        }

        if (data.getDay() < 0 || (data.getMonth() == 2 && data.getDay() > dayPerMonth[data.getMonth() - 1] - notLeapYear)
                || data.getMonth() != 2 && data.getDay() > dayPerMonth[data.getMonth() - 1]) {
            return false;
        }
        return true;
    }
}
