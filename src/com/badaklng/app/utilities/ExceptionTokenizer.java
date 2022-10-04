package com.badaklng.app.utilities;

import java.util.StringTokenizer;

public final class ExceptionTokenizer {

    public static String getMessageSegment(String message, int key) {

        StringTokenizer token = new StringTokenizer(message, "\n");
        int i = 1;
        String result = "";

        if (token.countTokens() <= 0 || token.countTokens() < key) {
            result = "";
        } else {
            while (token.hasMoreTokens()) {
                result = token.nextToken();
                if (i == key) {
                    break;
                }
                i++;
            }
        }
        return result;
    }
}
