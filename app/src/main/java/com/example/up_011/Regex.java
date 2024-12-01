package com.example.up_011;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static boolean RegexCheck(String email){
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,3}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
