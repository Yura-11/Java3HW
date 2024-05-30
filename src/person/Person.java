package person;

import java.time.DateTimeException;
import java.time.LocalDate;

public class Person {

    String lastName;
    String firstName;
    String middleName;
    LocalDate dateOfBirth;
    String phoneNumber;
    Gender gender;

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Gender getGender() {
        return gender;
    }

    public Person(String lastName, String firstName, String middleName, LocalDate dateOfBirth, String phoneNumber, Gender gender) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public static Person parseString(String input) {

        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Пустая строка. Ожидается строка с данными");
        }

        String[] parts = input.split(" ");

        for (int i = 0; i < parts.length; i++) {
            if (parts[i].isEmpty()){
                throw new IllegalArgumentException("Один из аргументов пустой. Индекс пустого аргумента: " + i);
            }

        }

        if (parts.length != 6) {
            throw new IllegalArgumentException("Неверное количество данных. Ожидается 6 элементов, получено " + parts.length);
        }

        // Фамилия, имя, отчество
        String lastName = parts[0];
        String firstName = parts[1];
        String middleName = parts[2];

        // Дата рождения
        LocalDate dateOfBirth;
        try {
            String[] dateParts = parts[3].split("\\.");
            int year = Integer.parseInt(dateParts[2]);
            int month = Integer.parseInt(dateParts[1]);
            int day = Integer.parseInt(dateParts[0]);
            dateOfBirth = LocalDate.of(year, month, day);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException | DateTimeException e){
            throw new IllegalArgumentException("Некорректный формат даты рождения: " + parts[3]);
        }

        String phoneNumber = parts[4];

        Gender gender;
        try {
            gender = parts[5].equalsIgnoreCase("f") ? Gender.FEMALE : parts[5].equalsIgnoreCase("m") ? Gender.MALE : null;
            if (gender == null) {
                throw new IllegalArgumentException("Не корректный аргумент: есть только два пола, мужской(m) и женский(f).");
            }
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            throw new IllegalArgumentException("Ошибка в формате строки: " + e.getMessage());
        }

        return new Person(lastName, firstName, middleName, dateOfBirth, phoneNumber, gender);
    }

    @Override
    public String toString() {
        return "Person [" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", gender=" + gender +
                ']';
    }
}