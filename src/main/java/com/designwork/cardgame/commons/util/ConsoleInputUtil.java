package com.designwork.cardgame.commons.util;

import java.util.Scanner;

public class ConsoleInputUtil {
    public ConsoleInputUtil() {
    }

    public static int requestNumericInput() {
        Integer input = null;
        Scanner sc;
        try {
            sc = new Scanner(System.in);
            input = sc.nextInt();
        } catch (Exception e) {
            System.out.println("please enter a number");
        }
        return input;
    }

    public String requestTextInput() {
        String input = null;
        Scanner sc;
        try {
            sc = new Scanner(System.in);
            input = sc.nextLine();
        } catch (Exception e) {
            System.out.println("please enter text");
        }
        return input;
    }
}
