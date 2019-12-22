package by.training.lessonifexercise_mvc;

public class ViewTerminal implements ViewLayer {
    @Override
    public void showEnteredDay(Date date) {
        System.out.println("Entered day - " + date.getDay() + "." + date.getMonth() + "." + date.getYear());
    }

    @Override
    public void showNextDay(Date date) {
        System.out.println("Next day - " + date.getDay() + "." + date.getMonth() + "." + date.getYear());
    }
}
