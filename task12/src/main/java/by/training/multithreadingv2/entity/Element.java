package by.training.multithreadingv2.entity;

public class Element {

    private int value;
    private Status status;

    public Element(int value) {
        this.value = value;
        this.status = Status.OPEN;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
