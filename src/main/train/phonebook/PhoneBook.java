package train.phonebook;

import java.util.Scanner;
import java.util.Set;

import static train.phonebook.Person.findPerson;
import static train.phonebook.Person.initBD;

public class PhoneBook {
    public static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        initBD();

        for (;;) {

            System.out.print("Для поиcка по имени введите 1, по номеру 2, email 3, для выхода из справочника 'стоп': ");
            String searchType = in.nextLine();
            Set<String> stopWords = Set.of("стоп", "stop");
            if (stopWords.contains(searchType.toLowerCase())) {
                System.out.println("Справочник закрыт, работа завершена");
                break;
            } else if (!searchType.matches("[1-3]")) {
                System.out.println("Тип поиска не опознан, должен быть 1, 2 или 3");
            } else {
                System.out.print("Введите поисковое значение: ");
                String searchString = in.nextLine();

                findPerson(searchType, searchString);
            }
        }

    }
}

class Person {
    private final String name;
    private final String phone;
    private final String email;
    private final static Person[] persons = new Person[10];

    public Person(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }


    String getName() {
        return name;
    }

    String getPhone() {
        return phone;
    }

    String getEmail() {
        return email;
    }

    public static void initBD() {
        persons[0] = new Person("Юля", "89210000000", "Julia@yandex.com");
        persons[1] = new Person("Сергей", "777777", "borya@yandex.com");
        persons[2] = new Person("Друган", "23566777", "univer@yandex.com");
        persons[3] = new Person("EvilBoss", "456546546", "boss@yandex.com");
        persons[4] = new Person("Anna", "+79216661666", "mylove@yandex.com");
    }

    public String toString() {
        return "\n\nName: " + this.getName() + "\nPhone number: " + this.getPhone() + "\nEmail: " +
                this.getEmail();
    }

    // поиск человека
    public static void findPerson(String searchType, String searchString) {

        switch (searchType) {
            case "1":
                //по имени
                System.out.println(findByName(searchString, persons));
                break;
            case "2":
                //по телефону
                System.out.println(findByPhoneNumber(searchString, persons));
                break;
            case "3":
                // по почте
                System.out.println(findByMail(searchString, persons));
                break;
            default:
                break;
        }
    }

    public static Person findByName(String name, Person[] persons) {
        if (!(name.matches("[А-ЯЁа-яё\\sa-zA-Z]*")) || name.isEmpty()) {
            System.out.println("Имя должно состоять из букв");
            return null;
        }
        String nameToFind = name
                .replaceAll("\\s", "")
                .toLowerCase();
        for (Person person : persons) {
            if (person == null) continue;
            if (person.getName().toLowerCase().matches(nameToFind)) {
                return person;
            }
        }
        System.out.println("нет такого человека");
        return null;
    }

    public static Person findByPhoneNumber(String phone, Person[] persons) {
        String phoneNumber = phone
                .replaceAll("[()\\s.+,]", "")
                .trim();
        if (phoneNumber.isEmpty()) {
            System.out.println("Не корректный номер телефона");
            return null;
        }

        for (Person person : persons) {
            if (person == null) continue;
            if (person.getPhone().replaceAll("[()\\s.+,]", "").matches(phoneNumber)) {
                return person;
            }
        }
        System.out.println("нет такого человека");
        return null;
    }

    public static Person findByMail(String email, Person[] persons) {
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            System.out.println("Не корректный e-mail");
            return null;
        }
        for (Person person : persons) {
            if (person == null) continue;
            if (person.getEmail().equalsIgnoreCase(email)) {
                return person;
            }
        }
        System.out.println("нет такого человека");
        return null;
    }

}

/*
 ДЗ
Дописать методы поиска для телефона и почты
Сделать так же выход
Продумать обработку исключений для поиска по пустым значениям

*/