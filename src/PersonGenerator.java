import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Scanner;
import static java.nio.file.StandardOpenOption.*;

public class PersonGenerator {
    public static void main(String[] args) {
        boolean doneInput = false;
        String ID = "";
        String firstName = "";
        String lastName = "";
        String title = "";
        int YOB = 0;
        ArrayList<String> people = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        do {
            ID = SafeInput.getNonZeroLenString(in, "Enter your ID [000001]: ");
            firstName = SafeInput.getNonZeroLenString(in, "Enter your first name: ");
            lastName = SafeInput.getNonZeroLenString(in, "Enter your last name: ");
            title = SafeInput.getNonZeroLenString(in, "Enter your title: ");
            YOB = SafeInput.getRangedInt(in, "Enter your YOB", 1000, 9999);

            String rec = ID + ", " + firstName + ", " + lastName + ", " + title + ", " + YOB;
            System.out.println(rec);
            people.add(rec);

            doneInput = SafeInput.getYNConfirm(in, "Are you done? (Y/N): ");
        } while (!doneInput);

        Path file = Paths.get("PersonTestData.txt");

        try (BufferedWriter writer = Files.newBufferedWriter(file, CREATE, APPEND)) {
            for (String person : people) {
                writer.write(person);
                writer.newLine();
            }
            System.out.println("Data successfully written to PersonTestData.txt.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
