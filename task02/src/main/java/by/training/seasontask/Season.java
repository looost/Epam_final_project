package by.training.seasontask;

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
