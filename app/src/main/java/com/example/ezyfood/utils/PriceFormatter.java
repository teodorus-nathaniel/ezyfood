package com.example.ezyfood.utils;

public class PriceFormatter {
    public static String format(int price) {
        String s = "Rp. ";
        String stringValue = "";
        String value = (price + "");

        int iter = 0;
        for (int i=value.length() - 1; i>=0; i--) {
            char character = value.charAt(i);
            if(iter != 0 && iter % 3 == 0) {
                stringValue = "." + stringValue;
            }
            stringValue = character + stringValue;
            iter++;
        }

        return s + stringValue;
    }
}
