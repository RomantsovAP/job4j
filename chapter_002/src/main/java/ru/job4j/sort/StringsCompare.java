package ru.job4j.sort;

import java.util.Comparator;

public class StringsCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int result = 0;
        for (int i = 0; i < Math.min(left.length(), right.length()); i++) {
            result = (result == 0) ? Character.compare(left.charAt(i), right.charAt(i)) : result;
            if (result != 0) {
                break;
            }
        }
        result = (result == 0) ? Integer.compare(left.length(), right.length()) : result;
        return result;
    }
}
