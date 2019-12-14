package by.training.task2.lessonexercise3;

import by.training.task1.validator.Validator;


public class Exercise3 {
    public static void main(String[] args) {
        System.out.print("Enter number: ");
        int number = Validator.enterInt();
        if (number > 10_000) {
            throw new IllegalArgumentException();
        }

        switch ((number / 1000) % 10) {
            case 1:
                System.out.print("Одна тысяча ");
                break;
            case 2:
                System.out.print("Две тысячи ");
                break;
            case 3:
                System.out.print("Три тысячи ");
                break;
            case 4:
                System.out.print("Четыре тысячи ");
                break;
            case 5:
                System.out.print("Пять тысяч ");
                break;
            case 6:
                System.out.print("Шесть тысяч ");
                break;
            case 7:
                System.out.print("Семь тысяч ");
                break;
            case 8:
                System.out.print("Восемь тысяч ");
                break;
            case 9:
                System.out.print("Девять тысяч ");
                break;
        }

        switch ((number / 100) % 10) {
            case 1:
                System.out.print("сто ");
                break;
            case 2:
                System.out.print("двесте ");
                break;
            case 3:
                System.out.print("триста ");
                break;
            case 4:
                System.out.print("четыреста ");
                break;
            case 5:
                System.out.print("пятьсот ");
                break;
            case 6:
                System.out.print("шестьсот ");
                break;
            case 7:
                System.out.print("семьсот ");
                break;
            case 8:
                System.out.print("восемьсот ");
                break;
            case 9:
                System.out.print("девятьсот ");
                break;
        }
        switch ((number / 10) % 10) {
            case 1:
                switch (number % 10) {
                    case 0:
                        System.out.println("десять");
                        break;
                    case 1:
                        System.out.println("одинадцать");
                        break;
                    case 2:
                        System.out.println("двенадцать");
                        break;
                    case 3:
                        System.out.println("тринадцать");
                        break;
                    case 4:
                        System.out.println("четырнадцать");
                        break;
                    case 5:
                        System.out.println("пятнадцать");
                        break;
                    case 6:
                        System.out.println("шеснадцать");
                        break;
                    case 7:
                        System.out.println("семьнадцать");
                        break;
                    case 8:
                        System.out.println("восемьнадцать");
                        break;
                    case 9:
                        System.out.println("девятнадцать");
                        break;
                }
                break;
            case 2:
                System.out.print("двадцать ");
                break;
            case 3:
                System.out.print("тридцать ");
                break;
            case 4:
                System.out.print("сорок ");
                break;
            case 5:
                System.out.print("пятьдесят ");
                break;
            case 6:
                System.out.print("шестьдесят ");
                break;
            case 7:
                System.out.print("семьдесят ");
                break;
            case 8:
                System.out.print("восемьдесят ");
                break;
            case 9:
                System.out.print("девятьдесят ");
                break;
        }
        if ((number / 10) % 10 != 1) {
            switch (number % 10) {
                case 2:
                    System.out.print("два ");
                    break;
                case 3:
                    System.out.print("три ");
                    break;
                case 4:
                    System.out.print("четыре ");
                    break;
                case 5:
                    System.out.print("пять ");
                    break;
                case 6:
                    System.out.print("шесть ");
                    break;
                case 7:
                    System.out.print("семь ");
                    break;
                case 8:
                    System.out.print("восемь ");
                    break;
                case 9:
                    System.out.print("девять ");
                    break;
            }
        }
    }
}
