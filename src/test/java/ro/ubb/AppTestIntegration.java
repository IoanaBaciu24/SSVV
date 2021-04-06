package ro.ubb;

import Domain.Student;
import Exceptions.ValidatorException;
import Repository.TxtFileRepository.NotaFileRepo;
import Repository.TxtFileRepository.StudentFileRepo;
import Repository.TxtFileRepository.TemaLabFileRepo;
import Service.TxtFileService.NotaService;
import Service.TxtFileService.StudentService;
import Service.TxtFileService.TemaLabService;
import Validator.NotaValidator;
import Validator.StudentValidator;
import Validator.TemaLabValidator;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class AppTestIntegration {

    StudentFileRepo repo = new StudentFileRepo("src/main/java/TestStd.txt", new StudentValidator());
    StudentService service = new StudentService(repo);
    TemaLabFileRepo temaLabFileRepo = new TemaLabFileRepo("src/main/java/TestTemaLab.txt", new TemaLabValidator());
    TemaLabService temaLabService = new TemaLabService(temaLabFileRepo);
    NotaFileRepo notaRepo = new NotaFileRepo("src/main/java/TestNota.txt", new NotaValidator());
    NotaService notaService =  new NotaService(notaRepo);


    public AppTestIntegration() throws IOException {
    }

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
    public void testAddToService()
    {
        String[] dummy = new String[]{"1", "le description", "2", "2"};
        try{
            temaLabService.add(dummy);
            assertFalse(false);
        }
        catch (Exception e)
        {
            assertFalse(true);
        }
    }

    @Test
    public void testAddGrade()
    {
        String[] s = new String[]{"1", "153", "1", "10", "2018-09-16T08:00:00"};
        try
        {
            notaService.add(s);
            assertFalse(false);
        } catch (ValidatorException e) {
            fail();
        }
    }

    @Test
    public void testIntegrateAll()
    {
        String[] student = new String[]{"153", "cezar cheddar", "10", "cezar@senat.it", "stabby"};
        String[] tema = new String[]{"1", "le description", "2", "2"};
        String[] grade = new String[]{"1", "153", "1", "10", "2018-09-16T08:00:00"};

        try {
            service.add(student);
            temaLabService.add(tema);
            notaService.add(grade);
            assertFalse(false);
        } catch (ValidatorException e) {
            fail();
        }


    }

}
