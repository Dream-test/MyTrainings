package regexmethods;

public class StringAnalysis {


    public void countLetters(String forCount, char letter) {
        String[] forCountWords = forCount
                .replaceAll("[,.']", "")
                .trim()
                .split(" ");
        System.out.println(forCount);
        countLettersInWords(forCountWords, letter);
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
