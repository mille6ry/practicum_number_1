import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ProductReader {
    public static void main(String[] args) {
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath(), "ProductTestData.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(file.toFile()))) {
            System.out.println(String.format("%-10s %-15s %-30s %-10s",
                    "ID", "Name", "Description", "Price"));
            System.out.println("==============================================================");

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",\\s*"); // Split on ", " or ","
                if (parts.length != 4) { // Validate the record format
                    System.out.println("Skipping malformed line: " + line);
                    continue;
                }

                String id = parts[0].trim();
                String name = parts[1].trim();
                String description = parts[2].trim();
                double price;

                try {
                    price = Double.parseDouble(parts[3].trim());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid price in line: " + line);
                    continue;
                }

                System.out.println(String.format("%-10s %-15s %-30s %-10.2f",
                        id, name, description, price));
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}

