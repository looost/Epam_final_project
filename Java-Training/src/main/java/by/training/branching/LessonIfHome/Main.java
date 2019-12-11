package by.training.branching.LessonIfHome;


public class Main {
    public static void main(String[] args) {
        Date date = new DateCreator().create(ReadDate.red());
        System.out.println("Нынешняя дата - " + date.getDay() + "." + date.getMonth() + "." + date.getYear());
        NextDateCommand.NextDate(date);
        System.out.println("Следующая дата - " + date.getDay() + "." + date.getMonth() + "." + date.getYear());
    }
}
