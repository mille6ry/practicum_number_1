import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import static java.nio.file.StandardOpenOption.CREATE;

public class PersonGenerator {
    public static void main(String[] args)
    {
        boolean doneInput = false;


        String ID = "";
        String firstName = "";
        String lastName = "";
        String title = "";
        String rec = "";
        int YOB = 0;


        ArrayList <String> people = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        do {
            ID=SafeInput.getNonZeroLenString(in, "Enter your ID [000001");
            firstName=SafeInput.getNonZeroLenString(in, "Enter your first name");
            lastName=SafeInput.getNonZeroLenString(in, "Enter your last name");
            title=SafeInput.getNonZeroLenString(in, "Enter your title");
            YOB = SafeInput.getRangedInt(in, "Enter your YOB",  1000, 9999);

            rec = ID + ", " + firstName + ", " + lastName + ", " + title + ", " + YOB;

            System.out.println(rec);

            people.add(rec);


            doneInput = SafeInput.getYNConfirm(in,"Are you done");
        }while (!doneInput);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\PersonTestData.txt.txt");

        try
        {
            OutputStream out=
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
                    BufferedWriter writer =
                            new BufferedWriter(new OutputStreamWriter(out));
            for(String person : people)
            {
                writer.write(person, 0,person.length());
                writer.newLine();
            }
            writer.close();
            System.out.println("Done file Written");

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}

