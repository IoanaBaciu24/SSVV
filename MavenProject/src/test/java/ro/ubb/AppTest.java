package ro.ubb;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import Domain.Student;
import Exceptions.ValidatorException;
import Repository.TxtFileRepository.StudentFileRepo;
import Repository.XMLFileRepository.StudentXMLRepo;
import Validator.StudentValidator;
import org.junit.Test;

import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    StudentFileRepo repo = new StudentFileRepo("src/main/java/TestStd.txt", new StudentValidator());

    public AppTest() throws IOException {
    }

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testAddStudent() throws ValidatorException {
        Student s = new Student("153", "cezar cheddar", 10, "cezar@senat.it", "stabby");
        assertNull(repo.save(s));
    }
}
