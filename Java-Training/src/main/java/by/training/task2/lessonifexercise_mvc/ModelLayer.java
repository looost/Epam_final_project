package by.training.task2.lessonifexercise_mvc;

import java.util.ArrayList;

public interface ModelLayer {
    Date getTerminalDate();

    Date getResult(Date date);

    boolean isValid(Date date);
}
