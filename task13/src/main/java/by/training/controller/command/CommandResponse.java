package by.training.controller.command;

import by.training.entity.Serial;

import java.util.Set;

public class CommandResponse {
    private final String status;
    private final Set<Serial> value;

    public CommandResponse(String status, Set<Serial> value) {
        this.status = status;
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public Set<Serial> getValue() {
        return value;
    }
}
