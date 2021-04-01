package ro.ubb;

import Domain.HasId;
import Domain.TemaLab;
import Exceptions.ValidatorException;
import Repository.TxtFileRepository.TemaLabFileRepo;
import Service.TxtFileService.TemaLabService;
import Validator.TemaLabValidator;
import org.junit.Test;

import java.io.IOException;

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

//    @Test
//    public void testAddNR1()
//    {
//        String[] dummy = new String[]{null, "le description", "2", "2"};
//        try {
//            temaLabService.add(dummy);
//            fail();
//        } catch (ValidatorException e) {
//            assertFalse(false);
//        }
//    }
//
//    @Test
//    public void testAddNR2()
//    {
//        String[] dummy = new String[]{"", "le description", "2", "2"};
//        try {
//            temaLabService.add(dummy);
//            fail();
//        } catch (ValidatorException e) {
//            assertFalse(false);
//        }
//    }
}
