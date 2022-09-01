package com.dataart.intern.logista.repository;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RepositoryUtil {

    private final static String WILDCARD = "%";

    public static String wrap(String input) {
        return WILDCARD + input + WILDCARD;
    }
}
