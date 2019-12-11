package by.training.branching.LessonIfTraining;

public class Data {
    private int day;
    private int month;
    private int year;

    public Data(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public boolean isLeap() {  //не високосный год если кратен 400, иначе
        if (year % 400 == 0) {
            return false;
        }
        return year % 4 == 0;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }
}
