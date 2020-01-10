package by.training.allexercise.command;

public enum CommandName {
    EXERCISE4,
    EXERCISE14,
    EXERCISE27,
    EXERCISE37;

    public static boolean contains(String str) {
        for (CommandName c : CommandName.values()) {
            if (c.name().equals(str.toUpperCase())) {
                return true;
            }
        }
        return false;
    }
}

