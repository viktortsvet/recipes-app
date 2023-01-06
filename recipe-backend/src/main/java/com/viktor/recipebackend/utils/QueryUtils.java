package com.viktor.recipebackend.utils;

import java.util.Arrays;
import java.util.stream.Collectors;

public class QueryUtils {
    public static String stringDataToStringForQuery(String[] data) {
        return Arrays.stream(data).collect(Collectors.joining("','", "'", "'"));
    }
}