package by.training.task2.seasontask;

public enum Season {
    WINTER("Winter"),
    SPRING("Spring"),
    SUMMER("Summer"),
    AUTUMN("Autumn");

    private String seasonText;

    private Season(String seasonText) {
        this.seasonText = seasonText;
    }
}
