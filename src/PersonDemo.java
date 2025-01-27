import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PersonDemo {
    private Person person;

    @BeforeEach
    public void setUp() {
        person = new Person("12345", "John", "Doe", "Mr.", 1985);
    }

    @Test
    public void testConstructorValid() {
        assertDoesNotThrow(() -> new Person("67890", "Jane", "Smith", "Dr.", 1975));
    }

    @Test
    public void testConstructorInvalidYOB() {
        assertThrows(IllegalArgumentException.class, () -> new Person("12345", "John", "Doe", "Mr.", 1930));
        assertThrows(IllegalArgumentException.class, () -> new Person("12345", "John", "Doe", "Mr.", 2020));
    }

    @Test
    public void testFullName() {
        assertEquals("John Doe", person.getFullName());
    }

    @Test
    public void testFormalName() {
        assertEquals("Mr. John Doe", person.getFormalName());
    }

    @Test
    public void testGetAge() {
        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        assertEquals(currentYear - 1985, person.getAge());
    }

    @Test
    public void testGetAgeSpecificYear() {
        assertEquals(20, person.getAge(2005));
    }

    @Test
    public void testToCSV() {
        assertEquals("12345,John,Doe,Mr.,1985", person.toCSV());
    }

    @Test
    public void testToJSON() {
        assertEquals("{\"ID\":\"12345\",\"firstName\":\"John\",\"lastName\":\"Doe\",\"title\":\"Mr.\",\"YOB\":1985}",
                person.toJSON());
    }

    @Test
    public void testToXML() {
        assertEquals("<Person><ID>12345</ID><FirstName>John</FirstName><LastName>Doe</LastName><Title>Mr.</Title><YOB>1985</YOB></Person>",
                person.toXML());
    }
}
