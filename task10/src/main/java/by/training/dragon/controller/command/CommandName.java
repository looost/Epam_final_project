package by.training.dragon.controller.command;

public enum CommandName {
    ALL("Введите \"all\", что бы получить все сокровища"),
    MOST_VALUABLE("Введите \"most_valuable\", что бы получить наиболее ценное сокровище"),
    TREASURE("Введите \"treasure x\", что бы поучить список сокровищ, чья стоимость выше x"),
    WRONG_REQUEST("Неверная команда");

    private String description;

    CommandName(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
