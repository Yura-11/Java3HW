package archive;



import person.Person;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

    public class PersonArchive {
        private Map<String, BufferedWriter> files;

        public PersonArchive(Map<String, BufferedWriter> files) {
            this.files = files;
        }

        public void savePerson(Person person) {
            String filename = Settings.DATA_DIRECTORY + person.getLastName() + ".txt";
            String resultString = "";

            try {
                BufferedWriter writer = files.computeIfAbsent(filename, key -> {
                    try {
                        return new BufferedWriter(new FileWriter(key, true));
                    } catch (IOException e) {
                        throw new IllegalArgumentException("Ошибка при создании файла для фамилии " + key);
                    }
                });

                String newLine = String.format("%s %s %s %s %s %s",
                        person.getLastName(), person.getFirstName(), person.getMiddleName(),
                        person.getDateOfBirth(), person.getPhoneNumber(), person.getGender().toString().toLowerCase());

                resultString = newLine;

                writer.write(newLine);
                writer.newLine();
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("\u001B[32m"+ "Данные сохранены: "+ resultString + "\u001B[0m");
        }

        public void close() {
            for (BufferedWriter writer : files.values()) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

