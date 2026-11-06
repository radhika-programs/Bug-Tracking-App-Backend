package com.bugtracking;
import java.util.Locale;
import java.text.NumberFormat;

public class LocaleCurrencyExample {
    public static void main(String[] args) {
        double price = 12345.67;

        NumberFormat usFormat = NumberFormat.getCurrencyInstance(Locale.US);
        NumberFormat indiaFormat = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
        NumberFormat franceFormat = NumberFormat.getCurrencyInstance(Locale.FRANCE);

        System.out.println("US: " + usFormat.format(price));
        System.out.println("India: " + indiaFormat.format(price));
        System.out.println("France: " + franceFormat.format(price));
    }
}