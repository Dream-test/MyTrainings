package com.regexmethods;

public class PhoneValidator {

   private final String regexOne = "^\\+?[1-9]\\d{0,2}[-\\s.]?(\\(?\\d{1,4}\\)?[-\\s.]?)*\\d{1,14}$";
   private final String regexTwo = "^0{2}[1-9]\\d{0,2}[-\\s.]?(\\(?\\d{1,4}\\)?[-\\s.]?)*\\d{1,14}$";

    public boolean isValid(String phoneNumber) {
        return phoneNumber.matches(regexOne) || phoneNumber.matches(regexTwo);
    }
}
