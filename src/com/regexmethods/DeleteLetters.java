package com.regexmethods;

public class DeleteLetters {
    private final String regex = "[A-Za-z ]";


    public String deleteLetters(String forDelete) {
        return forDelete.replaceAll(regex, "");
    }
}
