package by.training.abstracttextfile.controller;

import by.training.abstracttextfile.entity.Directory;

public interface Command {
    String execute(Directory directory, String request);
}
