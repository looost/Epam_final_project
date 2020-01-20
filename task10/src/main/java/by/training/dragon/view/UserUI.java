package by.training.dragon.view;

import java.util.Scanner;

public class UserUI {
    private final Scanner scanner = new Scanner(System.in);

    public String enterString() {
        return scanner.nextLine();
    }
}
