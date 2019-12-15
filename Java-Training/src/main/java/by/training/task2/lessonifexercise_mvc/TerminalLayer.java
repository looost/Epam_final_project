package by.training.task2.lessonifexercise_mvc;

import java.util.Scanner;

public class TerminalLayer implements ModelLayer {
    private int[] daysInMonth = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    @Override
    public Date getTerminalDate() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter day: ");
        while (!sc.hasNextInt()) {
            System.out.print("Enter int: ");
            sc.next();
        }
        int entered_day = sc.nextInt();
        System.out.print("Enter month: ");
        while (!sc.hasNextInt()) {
            System.out.print("Enter int: ");
            sc.next();
        }
        int entered_month = sc.nextInt();
        System.out.print("Enter year: ");
        while (!sc.hasNextInt()) {
            System.out.print("Enter int: ");
            sc.next();
        }
        int entered_year = sc.nextInt();
        return new Date(entered_day, entered_month, entered_year);
    }

    @Override
    public boolean isValid(Date date) {
        if (!date.isLeap() && date.getMonth() == 2 && date.getDay() == 29) {
            return false;
        }
        if (date.getYear() <= 0) {
            return false;
        }
        if (date.getDay() <= 0 || date.getDay() > 32) {
            return false;
        }
        if (date.getMonth() <= 0 || date.getMonth() > 12) {
            return false;
        }
        if (daysInMonth[date.getMonth() - 1] < date.getDay()) {
            return false;
        }
        return true;
    }

    @Override
    public Date getResult(Date date) {
        TerminalLayer terminalLayer = new TerminalLayer();
        if (terminalLayer.isValid(date)) {
            if (!date.isLeap()) {
                daysInMonth[1] = 28;
            }
            if (date.getDay() == daysInMonth[date.getMonth() - 1]) {
                date.setDay(1);
                if (date.getMonth() == 12) {
                    date.setMonth(1);
                    date.setYear(date.getYear() + 1);
                } else {
                    date.setMonth(date.getMonth() + 1);
                }
            } else {
                date.setDay(date.getDay() + 1);
            }
            return date;
        }
        throw new IllegalArgumentException("incorrect date");
    }

}
