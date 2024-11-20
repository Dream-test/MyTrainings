package com.regexmethods;

public class StringAnalysis {
    private String forCount;
    private String newEditString;

    public void countLetters(String forCount, char letter) {
        this.forCount = forCount;
        System.out.println(forCount);
        editForCount();
        countLettersInWords(splitStringToWords(), letter);
    }

    private void editForCount() {
        newEditString = forCount.replaceAll("[,.']", "").trim();
    }

    private String[] splitStringToWords() {
        return newEditString.split(" ");
    }

    private void countLettersInWords(String[] forCountWords, char letter) {
        int allCountLetter = 0;

        for (String forCountWord : forCountWords) {
            int count = 0;
            for (int j = 0; j < forCountWord.length(); j++) {
                if (forCountWord.charAt(j) == letter) count++;
            }
            allCountLetter = allCountLetter + count;
            System.out.println(count + "   " + "\"" + letter + "\"" + "  в слове:  \"" + forCountWord + "\"");
        }
        System.out.println("Всего  " + allCountLetter + " буквы " + "\"" + letter + "\"");
    }
}
