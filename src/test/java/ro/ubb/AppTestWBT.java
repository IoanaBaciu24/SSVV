package ro.ubb;

import Domain.HasId;
import Domain.TemaLab;
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
import java.util.stream.StreamSupport;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.*;

public class AppTestWBT {

    TemaLabFileRepo temaLabFileRepo = new TemaLabFileRepo("src/main/java/TestTemaLab.txt", new TemaLabValidator());
    TemaLabService temaLabService = new TemaLabService(temaLabFileRepo);
    public AppTestWBT() throws IOException {
    }

    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }



    @Test
    public void testAddDuplicatedAssignment() throws ValidatorException {
        TemaLab dummy = new TemaLab(1, "le description", 2, 2);
        HasId save = temaLabFileRepo.save(dummy);
        assertNull(save);
        save = temaLabFileRepo.save(dummy);
        assertNotNull(save);
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
    public void testAddSP1()
    {
        String[] dummy = new String[]{"1", "le description", "2", "0"};
        try {
            temaLabService.add(dummy);
            fail();
        } catch (ValidatorException e) {
            assertFalse(false);
        }
    }

    @Test
    public void testAddSP2()
    {
        String[] dummy = new String[]{"1", "le description", "2", "15"};
        try {
            temaLabService.add(dummy);
            fail();
        } catch (ValidatorException e) {
            assertFalse(false);
        }
    }

    @Test
    public void testAddTL1()
    {
        String[] dummy = new String[]{"1", "le description", "0", "2"};
        try {
            temaLabService.add(dummy);
            fail();
        } catch (ValidatorException e) {
            assertFalse(false);
        }
    }

    @Test
    public void testAddTL2()
    {
        String[] dummy = new String[]{"1", "le description", "15", "2"};
        try {
            temaLabService.add(dummy);
            fail();
        } catch (ValidatorException e) {
            assertFalse(false);
        }
    }

    @Test
    public void testAddD1()
    {
        String[] dummy = new String[]{"1", "", "2", "2"};
        try {
            temaLabService.add(dummy);
            fail();
        } catch (ValidatorException e) {
            assertFalse(false);
        }
    }
    @Test
    public void testAddD2()
    {
        String[] dummy = new String[]{"1", null, "2", "2"};
        try {
            temaLabService.add(dummy);
            fail();
        } catch (ValidatorException e) {
            assertFalse(false);
        }
    }

    @Test
    public void testAddNull()
    {
        try
        {
            temaLabService.add(null);
            fail();
        } catch (NullPointerException e) {
            fail();
        }
        catch (ValidatorException e)
        {
            assertFalse(false);
        }
    }


    @Test
    public void testAddNR1()
    {
        String[] dummy = new String[]{null, "le description", "2", "2"};
        try {
            temaLabService.add(dummy);
            fail();
        } catch (ValidatorException e) {
            assertFalse(false);
        }
        catch (NumberFormatException e)
        {
            fail();
        }
    }

    @Test
    public void testAddNR2()
    {
        String[] dummy = new String[]{"-1", "le description", "2", "2"};
        try {
            temaLabService.add(dummy);
            fail();
        } catch (ValidatorException e) {
            assertFalse(false);
        }
    }

    //some blackbox tests
    @Test
    public void testAddGrade()  {
        try {
            StudentFileRepo repo = new StudentFileRepo("src/main/java/TestStd.txt", new StudentValidator());
            StudentService service = new StudentService(repo);
            TemaLabFileRepo temaLabFileRepo = new TemaLabFileRepo("src/main/java/TestTemaLab.txt", new TemaLabValidator());
            TemaLabService temaLabService = new TemaLabService(temaLabFileRepo);
            NotaFileRepo notaRepo = new NotaFileRepo("src/main/java/TestNota.txt", new NotaValidator());
            NotaService notaService = new NotaService(notaRepo);
            String[] student = new String[]{"153", "cezar cheddar", "10", "cezar@senat.it", "stabby"};
            String[] tema = new String[]{"1", "le description", "2", "2"};
            String[] grade = new String[]{"1", "153", "1", "10", "2018-09-16T08:00:00"};

            service.add(student);
            temaLabService.add(tema);
            notaService.add(grade);

            assertTrue(StreamSupport.stream(notaService.getAll().spliterator(), false).count() == 1);
            assertFalse(false);
        }
        catch(IOException e)
        {

        }
        catch(ValidatorException e)
        {
            fail();
        }
    }



}
