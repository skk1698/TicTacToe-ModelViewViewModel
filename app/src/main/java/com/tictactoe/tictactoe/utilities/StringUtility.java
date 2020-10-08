package com.tictactoe.tictactoe.utilities;


public class StringUtility {

    public static String stringFromNumbers(int... numbers) {
        StringBuilder stringValueFromNumbers = new StringBuilder();
        for (int number : numbers)
            stringValueFromNumbers.append(number);
        return stringValueFromNumbers.toString();
    }

    public static boolean isNullOrEmpty(String value) {
        return value == null || value.length() == 0;
    }
}
