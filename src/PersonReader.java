import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

public class PersonReader {

    public static void main(String[] args) {
        String record;
        ArrayList<String> lines = new ArrayList<>();
        final int FIELDS_LENGTH = 5; // Expected number of fields per record
        String id, firstName, lastName, title;
        int yob;

        try {
            // Define the file path (ensure this file exists in the working directory)
            Path file = Paths.get("PersonTestData.txt");

            // Check if the file exists
            if (!Files.exists(file)) {
                System.out.println("Error: File 'PersonTestData.txt' not found in the current directory.");
                return;
            }

            // Read the file
            try (BufferedReader reader = Files.newBufferedReader(file)) {
                int lineNumber = 0;
                while ((record = reader.readLine()) != null) { // Read each line
                    lines.add(record); // Add the line to the list
                    lineNumber++;
                    System.out.printf("Line %d: %s\n", lineNumber, record); // Echo each line
                }
            }

            System.out.println("\nFinished reading the file!");

            // Display data in a table format
            System.out.printf("\n%-8s%-20s%-20s%-15s%-5s\n", "ID", "First Name", "Last Name", "Title", "YOB");
            System.out.println("----------------------------------------------------------");

            for (String line : lines) {
                String[] fields = line.split(","); // Split the line into parts

                if (fields.length == FIELDS_LENGTH) {
                    id = fields[0].trim();
                    firstName = fields[1].trim();
                    lastName = fields[2].trim();
                    title = fields[3].trim();
                    yob = Integer.parseInt(fields[4].trim());

                    System.out.printf("%-8s%-20s%-20s%-15s%-5d\n", id, firstName, lastName, title, yob);
                } else {
                    // Warn about improperly formatted records
                    System.out.println("Warning: Found a line with incorrect formatting:");
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error: Could not read the file. Ensure 'PersonTestData.txt' is accessible.");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Error: Found invalid numeric data in the file.");
            e.printStackTrace();
        }
    }
}
