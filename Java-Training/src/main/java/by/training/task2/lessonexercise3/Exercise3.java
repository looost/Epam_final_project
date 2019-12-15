package by.training.task2.lessonexercise3;

import by.training.task1.validator.Validator;


public class Exercise3 {
    public static void main(String[] args) {
        System.out.print("Enter number: ");
        int number = Validator.enterInt();
        if (number > 10_000 || number <= 0) {
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
                        System.out.println("десять рублей");
                        break;
                    case 1:
                        System.out.println("одинадцать рублей");
                        break;
                    case 2:
                        System.out.println("двенадцать рублей");
                        break;
                    case 3:
                        System.out.println("тринадцать рублей");
                        break;
                    case 4:
                        System.out.println("четырнадцать рублей");
                        break;
                    case 5:
                        System.out.println("пятнадцать рублей");
                        break;
                    case 6:
                        System.out.println("шеснадцать рублей");
                        break;
                    case 7:
                        System.out.println("семьнадцать рублей");
                        break;
                    case 8:
                        System.out.println("восемьнадцать рублей");
                        break;
                    case 9:
                        System.out.println("девятнадцать рублей");
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
                System.out.print("девяносто ");
                break;
        }
        if ((number / 10) % 10 != 1) {
            switch (number % 10) {
                case 1:
                    System.out.print("один рубль");
                    break;
                case 2:
                    System.out.print("два рубля");
                    break;
                case 3:
                    System.out.print("три рубля");
                    break;
                case 4:
                    System.out.print("четыре рубля");
                    break;
                case 5:
                    System.out.print("пять рублей");
                    break;
                case 6:
                    System.out.print("шесть рублей");
                    break;
                case 7:
                    System.out.print("семь рублей");
                    break;
                case 8:
                    System.out.print("восемь рублей");
                    break;
                case 9:
                    System.out.print("девять рублей");
                    break;
            }
        }
    }
}
