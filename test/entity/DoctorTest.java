/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Acer
 */
public class DoctorTest {

    private Doctor doctor;
    private Date sampleDate;
    private SimpleDateFormat sdf;

    @Before
    public void setUp() throws ParseException {
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        sampleDate = sdf.parse("01/01/1990");
        doctor = new Doctor(1, "John Doe", sampleDate, "Cardiology", 1, "johndoe@example.com", "(123)-456-7890");
    }

    @Test
    public void testGetId() {
        assertEquals("ID should be 1", 1, doctor.getId());
    }

    @Test
    public void testGetName() {
        assertEquals("Name should be 'John Doe'", "John Doe", doctor.getName());
    }

    @Test
    public void testGetDateOfBirth() {
        assertEquals("Date of Birth should match the sample date", sampleDate, doctor.getDateOfBirth());
    }

    @Test
    public void testGetSpecialization() {
        assertEquals("Specialization should be 'Cardiology'", "Cardiology", doctor.getSpecialization());
    }

    @Test
    public void testGetAvailability() {
        assertEquals("Availability should be 1", 1, doctor.getAvailability());
    }

    @Test
    public void testGetEmail() {
        assertEquals("Email should be 'johndoe@example.com'", "johndoe@example.com", doctor.getEmail());
    }

    @Test
    public void testGetMobile() {
        assertEquals("Mobile should be '(123)-456-7890'", "(123)-456-7890", doctor.getMobile());
    }

    @Test
    public void testSetName() {
        doctor.setName("Jane Smith");
        assertEquals("Name should be updated to 'Jane Smith'", "Jane Smith", doctor.getName());
    }

    @Test
    public void testSetDateOfBirth() throws ParseException {
        Date newDate = sdf.parse("02/02/1992");
        doctor.setDateOfBirth(newDate);
        assertEquals("Date of Birth should be updated", newDate, doctor.getDateOfBirth());
    }

    @Test
    public void testSetSpecialization() {
        doctor.setSpecialization("Neurology");
        assertEquals("Specialization should be updated to 'Neurology'", "Neurology", doctor.getSpecialization());
    }

    @Test
    public void testSetAvailability() {
        doctor.setAvailability(2);
        assertEquals("Availability should be updated to 2", 2, doctor.getAvailability());
    }

    @Test
    public void testSetEmail() {
        doctor.setEmail("janesmith@example.com");
        assertEquals("Email should be updated to 'janesmith@example.com'", "janesmith@example.com", doctor.getEmail());
    }

    @Test
    public void testSetMobile() {
        doctor.setMobile("(321)-654-0987");
        assertEquals("Mobile should be updated to '(321)-654-0987'", "(321)-654-0987", doctor.getMobile());
    }

    @Test
    public void testToString() {
        String expected = String.format("Doctor [ID=%d, Name=%s, DOB=%s, Specialization=%s, Availability=%d, Email=%s, Mobile=%s]",
                doctor.getId(), doctor.getName(), doctor.getDateOfBirth(), doctor.getSpecialization(),
                doctor.getAvailability(), doctor.getEmail(), doctor.getMobile());
        assertEquals("toString output should match", expected, doctor.toString());
    }
}
