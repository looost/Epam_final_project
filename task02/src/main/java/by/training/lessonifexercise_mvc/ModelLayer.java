package by.training.lessonifexercise_mvc;


public interface ModelLayer {
    Date getTerminalDate();

    Date getResult(Date date);

    boolean isValid(Date date);
}
