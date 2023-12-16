package com.example.universitymanagementsystem.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    private Validation(){}

    public static boolean isEmailValid(String email) {
        final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isPhoneNumberValid(String phoneNumber) {
        final String PHONE_NUMBER_REGEX = "^5[0-9]{8}$";
        Pattern pattern = Pattern.compile(PHONE_NUMBER_REGEX);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
}
