package com.example.nopaper.helpers;

public class StringHelper {

    // Set Regular Expression Pattern for Email:
    public static boolean regexEmailValidationPattern(String email) {
        // Set Pattern:
        String regex = "([a-z]+(?:[._][a-z0-9]+)*)@([a-z]+(?:[.-][a-z0-9]+)*[.][a-zA-Z]{2,})";

        if (email.matches(regex)) {
            return true;
        }
        return false;
    }
}
