/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Acer
 */
public class DoctorManagerTest {

    public DoctorManagerTest() {
    }

    @SuppressWarnings("unchecked")
    private List<Doctor> getDoctorList(DoctorManager manager) throws Exception {
        Field field = DoctorManager.class.getDeclaredField("doctorList");
        field.setAccessible(true);
        return (List<Doctor>) field.get(manager);
    }



    @Test
    public void testAddDoctor() throws Exception {
        DoctorManager manager = new DoctorManager();
        manager.addDoctor("Test Doctor", "01/01/1990", "Test Specialization", 2, "testdoctor@gmail.com", "(123)-456-7890");
        List<Doctor> list = getDoctorList(manager);

        assertEquals("The list should contain 1 doctor after adding", 1, list.size());
        Doctor d = list.get(0);
        assertEquals("Doctor name mismatch", "Test Doctor", d.getName());
    }

    @Test
    public void testEditDoctor() throws Exception {
        DoctorManager manager = new DoctorManager();
        manager.addDoctor("Old Name", "01/01/1990", "Old Specialization", 2, "old@gmail.com", "(123)-456-7890");

        manager.editDoctor(1, "New Name", "02/02/1992", "New Specialization", 3, "new@gmail.com", "(321)-654-0987");
        Doctor d = manager.searchDoctorById(1);
        assertNotNull("The edited doctor should not be null", d);
        assertEquals("Doctor name was not updated", "New Name", d.getName());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date expectedDOB = sdf.parse("02/02/1992");
        assertEquals("Date of birth was not updated correctly", expectedDOB, d.getDateOfBirth());

        assertEquals("Specialization was not updated", "New Specialization", d.getSpecialization());
        assertEquals("Availability was not updated", 3, d.getAvailability());
        assertEquals("Email was not updated", "new@gmail.com", d.getEmail());
        assertEquals("Mobile was not updated", "(321)-654-0987", d.getMobile());
    }

    @Test
    public void testDeleteDoctor() throws Exception {
        DoctorManager manager = new DoctorManager();
        manager.addDoctor("Doctor To Delete", "01/01/1990", "Test", 2, "delete@gmail.com", "(123)-456-7890");

        assertNotNull("Doctor with ID = 1 should exist", manager.searchDoctorById(1));

        manager.deleteDoctor(1);
        assertNull("Doctor with ID = 1 should be null after deletion", manager.searchDoctorById(1));
    }

    @Test
    public void testSearchDoctorById() throws Exception {
        DoctorManager manager = new DoctorManager();
        manager.addDoctor("Doctor One", "01/01/1990", "Test", 2, "one@gmail.com", "(123)-456-7890");
        Doctor d = manager.searchDoctorById(1);
        assertNotNull("Searching by ID should return a doctor", d);
        assertEquals("Doctor name does not match when searching by ID", "Doctor One", d.getName());
    }

    @Test
    public void testSearchDoctorByName() throws Exception {
        DoctorManager manager = new DoctorManager();
        manager.addDoctor("Alpha", "01/01/1990", "Test", 2, "alpha@gmail.com", "(123)-456-7890");
        manager.addDoctor("Beta", "02/02/1992", "Test", 2, "beta@gmail.com", "(234)-567-8901");
        manager.addDoctor("Alfred", "03/03/1993", "Test", 2, "alfred@gmail.com", "(345)-678-9012");

        List<Doctor> results = manager.searchDoctorByName("Al");
        assertEquals("The number of search results by name does not match", 2, results.size());
    }

    @Test
    public void testSortDoctorsByDOB() throws Exception {
        DoctorManager manager = new DoctorManager();
        manager.addDoctor("Doctor A", "10/10/1985", "Spec", 1, "a@gmail.com", "(111)-111-1111");
        manager.addDoctor("Doctor B", "05/05/1990", "Spec", 1, "b@gmail.com", "(222)-222-2222");
        manager.addDoctor("Doctor C", "20/03/1980", "Spec", 1, "c@gmail.com", "(333)-333-3333");

        manager.sortDoctorsByDOB();
        List<Doctor> list = getDoctorList(manager);

        assertEquals("Doctor C", list.get(0).getName());
        assertEquals("Doctor A", list.get(1).getName());
        assertEquals("Doctor B", list.get(2).getName());
    }

}