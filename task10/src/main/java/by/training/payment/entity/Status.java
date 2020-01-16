package by.training.payment.entity;

public class Status {

    private boolean status;
    private String description;

    public Status(boolean status, String description) {
        this.status = status;
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
