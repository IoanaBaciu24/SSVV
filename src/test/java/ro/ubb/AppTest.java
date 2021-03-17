package ro.ubb;

import Domain.Student;
import Exceptions.ValidatorException;
import Repository.TxtFileRepository.StudentFileRepo;
import Repository.XMLFileRepository.StudentXMLRepo;
import Service.TxtFileService.StudentService;
import Validator.StudentValidator;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    StudentFileRepo repo = new StudentFileRepo("src/main/java/TestStd.txt", new StudentValidator());
    StudentService service = new StudentService(repo);
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

    @Test
    public void testDoubleAdd() throws ValidatorException {
        Student s = new Student("153", "cezar cheddar", 10, "cezar@senat.it", "stabby");
        repo.save(s);
        assertSame(repo.save(s), s);
    }

    @Test
    public void testNull()
    {
        try {
            repo.save(null);
            assertFalse(true);
        }
        catch (ValidatorException | IllegalArgumentException e) {
            assertTrue(true);
        }
        catch (NullPointerException e)
        {
            assertFalse(true);
        }

    }

    @Test
    public void testIdEmpty()
    {
//        Student s = new Student("", "cezar cheddar", 10, "cezar@senat.it", "stabby");
        try {
            service.add(new String[]{"", "cezar cheddar", "10", "cezar@senat.it", "stabby"});
            assertFalse(true);
        }
        catch (ValidatorException e) {
            assertTrue(true);
        }
    }


    @Test
    public void testIdNull()
    {
//        Student s = new Student(null, "cezar cheddar", 10, "cezar@senat.it", "stabby");
        try {
            service.add(new String[]{null, "cezar cheddar", "10", "cezar@senat.it", "stabby"});
            assertFalse(true);
        }
        catch (ValidatorException e) {
            assertTrue(true);
        }
        catch (NullPointerException e)
        {
            assertFalse(true);
        }
    }

    @Test
    public void testNameEmpty()
    {
//        Student s = new Student("153", "", 10, "cezar@senat.it", "stabby");
        try {
            service.add(new String[]{"153", "", "10", "cezar@senat.it", "stabby"});

            assertFalse(true);
        }
        catch (ValidatorException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testNameNull()
    {
//        Student s = new Student("153", null, 10, "cezar@senat.it", "stabby");
        try {
            service.add(new String[]{"153", null, "10", "cezar@senat.it", "stabby"});
            assertFalse(true);
        }
        catch (ValidatorException e) {
            assertTrue(true);
        }
        catch (NullPointerException e)
        {
            assertFalse(true);
        }
    }


    @Test
    public void testEmailEmpty()
    {
//        Student s = new Student("153", "cezar cheddar", 10, "", "stabby");
        try {
            service.add(new String[]{"153", "cezar cheddar", "10", "", "stabby"});
            assertFalse(true);
        }
        catch (ValidatorException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testEmailNull()
    {
//        Student s = new Student("153", "cezar cheddar", 10, null, "stabby");
        try {
            service.add(new String[]{"153", "cezar cheddar", "10", null, "stabby"});
            assertFalse(true);
        }
        catch (ValidatorException e) {
            assertTrue(true);
        }
        catch (NullPointerException e)
        {
            assertFalse(true);
        }
    }

    @Test
    public void testIndrumatorEmpty()
    {
//        Student s = new Student("153", "cezar cheddar", 10, "cezar@senat.it", "");
        try {
            service.add(new String[]{"153", "cezar cheddar", "10", "cezar@senat.it", ""});
            assertFalse(true);
        }
        catch (ValidatorException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testIndrumatorNull()
    {
//        Student s = new Student("153", "cezar cheddar", 10, "cezar@senat.it", null);
        try {
            service.add(new String[]{"153", "cezar cheddar", "10", "cezar@senat.it", null});
            assertFalse(true);
        }
        catch (ValidatorException e) {
            assertTrue(true);
        }
        catch (NullPointerException e)
        {
            assertFalse(true);
        }
    }


    @Test
    public void testGrupaBV1()
    {
        Student s = new Student("153", "cezar cheddar", 0, "cezar@senat.it", "stabby");
        try {
            service.add(new String[]{"153", "cezar cheddar", "0", "cezar@senat.it", "stabby"});
            assertTrue(true);
        }
        catch (ValidatorException e) {
            assertFalse(true);
        }
    }

    @Test
    public void testGrupaBV3()
    {
//        Student s = new Student("153", "cezar cheddar", 1, "cezar@senat.it", "stabby");
        try {
            service.add(new String[]{"153", "cezar cheddar", "10", "cezar@senat.it", "stabby"});
            assertTrue(true);
        }
        catch (ValidatorException e) {
            assertFalse(true);
        }
    }

    @Test
    public void testGrupaBV2()
    {
//        Student s = new Student("153", "cezar cheddar", -1, "cezar@senat.it", "stabby");
        try {
            service.add(new String[]{"153", "cezar cheddar", "-1", "cezar@senat.it", "stabby"});
            assertFalse(true);
        }
        catch (ValidatorException e) {
            assertTrue(true);
        }
    }
}
