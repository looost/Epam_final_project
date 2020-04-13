package by.training.controller.servlet.handler;

import by.training.model.Serial;

public class MultiFilesResponse {

    private boolean haveProblem;
    private Serial serial;

    public MultiFilesResponse(boolean haveProblem, Serial serial) {
        this.haveProblem = haveProblem;
        this.serial = serial;
    }

    public boolean isHaveProblem() {
        return haveProblem;
    }

    public void setHaveProblem(boolean haveProblem) {
        this.haveProblem = haveProblem;
    }

    public Serial getSerial() {
        return serial;
    }

    public void setSerial(Serial serial) {
        this.serial = serial;
    }
}
