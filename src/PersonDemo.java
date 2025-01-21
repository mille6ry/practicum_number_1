import java.util.Scanner;

public class PersonDemo
{

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        SafeInputObj sio = new SafeInputObj(in);

        Person boy = new Person("000001", "Bill", "Bailey", "DR.", 1942);
        Person girl = new Person("000001", "Saly", "Smith", "DR.", 1952);

        System.out.println(boy.getFullName());
        System.out.println(girl.getFormalName());
        System.out.println(girl.toCSV());

        String id = sio.getNonZeroLenString("Enter your ID: ");

        System.out.println("Your ID: " + id);

    }
}
