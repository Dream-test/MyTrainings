package com.regexmethods;

public class PhoneValidator {

   private final String regexOne = "^\\+{1}[1-9]\\d{0,2}[-\\s.]?(\\(\\d{1,4}\\)[-\\s.]?)?\\d{0,3}([-\\s.]\\d{1,3}){0,4}\\d{1,15}$";
   private final String regexTwo = "^0{2}[1-9]\\d{0,2}[-\\s.]?(\\(\\d{1,4}\\)[-\\s.]?)?\\d{0,3}([-\\s.]\\d{1,3}){0,4}\\d{1,15}$";

    public boolean isValid(String phoneNumber) {
        return phoneNumber.matches(regexOne) || phoneNumber.matches(regexTwo);
    }
}
