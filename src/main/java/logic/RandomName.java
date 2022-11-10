package logic;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.Random;

public class RandomName {

    public static String getName() {
        String firstName = "";
        String lastName = "";
        String firstNamePath = "src/main/java/logic/names/first-names.txt";
        String lastNamePath = "src/main/java/logic/names/last-names.txt";

        File firstFile = new File(firstNamePath);
        File lastFile = new File(lastNamePath);

        try {
            Random r = new Random();

            RandomAccessFile firstNameFile = new RandomAccessFile(firstFile, "r");
            long randomIndex = r.nextLong(firstNameFile.length());
            firstNameFile.seek(randomIndex);
            firstNameFile.readLine();
            firstName = firstNameFile.readLine();
            firstNameFile.close();

            RandomAccessFile lastNameFile = new RandomAccessFile(lastFile, "r");
            randomIndex = r.nextLong(lastNameFile.length());
            lastNameFile.seek(randomIndex);
            lastNameFile.readLine();
            lastName = lastNameFile.readLine();
            lastNameFile.close();
        }
        catch(Exception e) {
            System.out.println("Something went wrong making a name");
        }

        return String.format("%s %s", firstName, lastName);
    }
    
}
