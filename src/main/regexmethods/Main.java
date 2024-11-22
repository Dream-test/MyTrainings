package regexmethods;

public class Main {

    public static void main(String[] args) {

        String forCount ="Домашнее задание. В тексте, который вы видите на этом изображении, посчитайте количество букв 'е' в каждом слове. Напишите регулярное выражение для проверки телефона в международном формате. С помощью регулярного выражения напишите функцию удаления всех букв и пробелов из текста.";
        char letter = 'е';
        new StringAnalysis().countLetters(forCount, letter);
        System.out.println("-------------------------------------------------------------------------------------------------------");

        String[] phoneNumbers = {"+12345678901", "00122345678901", "+1 (234) 567-8901", "987654321", "+7(920)325-46-56", "+375 29) 754-28-12", "+375 (29) 754-28-12", "+7abc25489212"};
        for (String phone : phoneNumbers) {
            System.out.println(phone + " is valid: " + new PhoneValidator().isValid(phone));
        }
        System.out.println("-------------------------------------------------------------------------------------------------------");

        String forDelete ="2. Under Project Settings, select Libraries and click the New Project Library button | From Maven. 3. In the dialog that opens, specify the necessary library artifact, for example: org.junit.jupiter:junit-jupiter:5.9.1.";
        System.out.println("String for delete: " + forDelete);

        System.out.println("New string: " + new DeleteLetters().deleteLetters(forDelete));
    }
}