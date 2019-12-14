package by.training.task2.lessonIfHome;


import java.util.List;

public class DateCreator {
    public Date create(List<Integer> list) {
        return new Date(list.get(0), list.get(1), list.get(2));
    }
}
