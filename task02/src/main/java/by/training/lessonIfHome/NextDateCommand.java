package by.training.lessonIfHome;

public class NextDateCommand {
    public static Date NextDate(Date date) {
        if (DateValidator.isValid(date)) {
            int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            if (date.isLeap()) {
                daysInMonth[1] = 29;
            }
            if (date.getDay() == daysInMonth[date.getMonth() - 1]) {
                date.setDay(1);
                if (date.getMonth() == 12) {
                    date.setMonth(1);
                    date.setYear(date.getYear() + 1);
                } else {
                    date.setMonth(date.getMonth() + 1);
                }
            } else {
                date.setDay(date.getDay() + 1);
            }
            return date;
        }
        throw new IllegalArgumentException("incorrect date");
    }
}
