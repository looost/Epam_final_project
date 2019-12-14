package by.training.task2.lessonIfHome;

public class NextDateCommand {
    public static Date NextDate(Date date) {
        if (DateValidator.isValid(date)) {
            int[] dayPerMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            if (date.isLeap()) {
                dayPerMonth[1] = 29;
            }
            if (date.getDay() == dayPerMonth[date.getMonth() - 1]) {
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
