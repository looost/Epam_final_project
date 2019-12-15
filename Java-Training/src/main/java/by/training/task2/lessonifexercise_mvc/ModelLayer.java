package by.training.task2.lessonifexercise_mvc;


public interface ModelLayer {
    Date getTerminalDate();

    Date getResult(Date date);

    boolean isValid(Date date);
}
