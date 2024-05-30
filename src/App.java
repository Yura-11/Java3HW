import archive.PersonArchive;
import person.Person;

import java.io.BufferedWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        start();
    }

    public static void start(){

        Map<String, BufferedWriter> files = new HashMap<>();
        PersonArchive archive = new PersonArchive(files);
        String input1 = "Иванов Иван Иванович 01.01.1990 1234567890 m";
        String input2 = "Петров Петр Петрович 02.02.1991 0987654321 m";
        String input3 = "Иванова Мария Ивановна 03.03.1992 1357924680 f";
        String input4 = "Иванов Игорь Петрович 04.04.1993 465153156 m";
        String input5 = "Иванов Игорь Петрович 04.04.1993 7777 m";

        archive.savePerson(Person.parseString(input1));
        archive.savePerson(Person.parseString(input2));
        archive.savePerson(Person.parseString(input3));
        archive.savePerson(Person.parseString(input4));
        archive.savePerson(Person.parseString(input5));

        String input = getInput();
        archive.savePerson(Person.parseString(input));
        archive.close();
    }

    public static String getInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\u001B[33m"+ "Памятка, порядок данных: фамилия, имя, отчество, дата рождения dd.mm.yyyy, номер телефона, пол (m/f)" + "\u001B[0m");
        System.out.print("Введите ваши данные: ");
        String input = scanner.nextLine();

        scanner.close();
        return input;
    }
}