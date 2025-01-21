import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductGenerator {
    public static void main(String[] args) {
        boolean doneInput = false;

        String ID;
        String Name;
        String description;
        String rec;
        double price;

        ArrayList<String> products = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        do {
            ID = SafeInput.getNonZeroLenString(in, "Enter the Product ID [e.g., 000001]:");
            Name = SafeInput.getNonZeroLenString(in, "Enter the Product Name:");
            description = SafeInput.getNonZeroLenString(in, "Enter the Product Description:");
            price = SafeInput.getDouble(in, "Enter the Product Price:");

            rec = ID + ", " + Name + ", " + description + ", " + price;

            System.out.println("Record: " + rec);
            products.add(rec);

            doneInput = SafeInput.getYNConfirm(in, "Are you done entering products?");
        } while (!doneInput);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath(), "ProductTestData.txt");

        try {
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
            for (String product : products) {
                writer.write(product, 0, product.length());
                writer.newLine();
            }
            writer.close();
            System.out.println("Products saved to ProductTestData.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


