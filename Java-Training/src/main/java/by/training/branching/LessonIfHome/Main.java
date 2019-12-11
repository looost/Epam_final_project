package by.training.branching.LessonIfHome;


public class Main {
    public static void main(String[] args) {
        Date date = new DateCreator().create(ReadDate.red());
        System.out.println("Нынешний день - " + date.getDay() + "." + date.getMonth() + "." + date.getYear());
        NextDateCommand.NextDate(date);
        System.out.println("Следующий день - " + date.getDay() + "." + date.getMonth() + "." + date.getYear());
    }
}
