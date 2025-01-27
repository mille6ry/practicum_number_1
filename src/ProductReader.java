import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class ProductReader {

    public static void main(String[] args) {

        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";
        ArrayList<String> lines = new ArrayList<>();

        // Constants for the number of expected fields in a record
        final int FIELDS_LENGTH = 4;

        // Variables to hold extracted fields
        String id, name, description;
        double price;

        try {
            // Set the working directory to the current directory
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);

            // Open file chooser dialog
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                // Read the selected file
                try (InputStream in = new BufferedInputStream(Files.newInputStream(file));
                     BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {

                    int line = 0;
                    while (reader.ready()) {
                        rec = reader.readLine(); // Read one line at a time
                        lines.add(rec); // Add line to the ArrayList
                        line++;
                    }
                }

                System.out.println("\n\nData file read successfully!");

                // Process each line in the file
                System.out.println(String.format("\n%-10s %-15s %-30s %-10s",
                        "ID", "Name", "Description", "Price"));
                System.out.println("==============================================================");

                for (String l : lines) {
                    String[] fields = l.split(",\\s*"); // Split the record into fields

                    if (fields.length == FIELDS_LENGTH) {
                        try {
                            // Extract and trim each field
                            id = fields[0].trim();
                            name = fields[1].trim();
                            description = fields[2].trim();
                            price = Double.parseDouble(fields[3].trim());

                            // Print the formatted output
                            System.out.println(String.format("%-10s %-15s %-30s %-10.2f",
                                    id, name, description, price));
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid price in line: " + l);
                        }
                    } else {
                        // Handle corrupt records
                        System.out.println("Corrupt record found:");
                        System.out.println(l);
                    }
                }

            } else {
                // User closed the file dialog without selecting a file
                System.out.println("No file selected. Please run the program again.");
                System.exit(0);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

