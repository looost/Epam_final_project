package by.training.branching.LessonIfTraining.controller;

import by.training.branching.LessonIfTraining.Data;

import java.util.List;

public class DataCreator {
    public Data create(List<Integer> list) {
        return new Data(list.get(0), list.get(1), list.get(2));
    }
}
