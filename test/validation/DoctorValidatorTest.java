/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Acer
 */
public class DoctorValidatorTest {

    public DoctorValidatorTest() {
    }

    @Test
    public void testIsValidName() {
        assertTrue("Valid name should return true", DoctorValidator.isValidName("John Doe"));

        assertFalse("Null name should return false", DoctorValidator.isValidName(null));

        String longName = new String(new char[51]).replace('\0', 'a');
        assertFalse("Name longer than 50 characters should return false", DoctorValidator.isValidName(longName));

        String validName = new String(new char[50]).replace('\0', 'b');
        assertTrue("Name with exactly 50 characters should be valid", DoctorValidator.isValidName(validName));
    }

    @Test
    public void testIsValidDateOfBirth() {
        assertTrue("Valid date should return true", DoctorValidator.isValidDateOfBirth("01/01/2000"));

        assertFalse("Date in wrong format should return false", DoctorValidator.isValidDateOfBirth("2000-01-01"));

        assertFalse("Non-existent date should return false", DoctorValidator.isValidDateOfBirth("31/02/2000"));

        assertFalse("Null date should return false", DoctorValidator.isValidDateOfBirth(null));
    }

    @Test
    public void testIsValidSpecialization() {
        assertTrue("Valid specialization should return true", DoctorValidator.isValidSpecialization("Cardiology"));

        assertFalse("Null specialization should return false", DoctorValidator.isValidSpecialization(null));

        String longSpecialization = new String(new char[256]).replace('\0', 'a');
        assertFalse("Specialization longer than 255 characters should return false", DoctorValidator.isValidSpecialization(longSpecialization));

        String validSpecialization = new String(new char[255]).replace('\0', 'b');
        assertTrue("Specialization with exactly 255 characters should be valid", DoctorValidator.isValidSpecialization(validSpecialization));
    }

    @Test
    public void testIsValidAvailability() {
        assertTrue("Availability of 0 should be valid", DoctorValidator.isValidAvailability(0));
        assertTrue("Availability of 1 should be valid", DoctorValidator.isValidAvailability(1));
        assertTrue("Availability of 2 should be valid", DoctorValidator.isValidAvailability(2));
        assertTrue("Availability of 3 should be valid", DoctorValidator.isValidAvailability(3));

        assertFalse("Negative availability should be invalid", DoctorValidator.isValidAvailability(-1));
        assertFalse("Availability greater than 3 should be invalid", DoctorValidator.isValidAvailability(4));
    }

    @Test
    public void testIsValidEmail() {
        assertTrue("Valid email should return true", DoctorValidator.isValidEmail("john.doe@example.com"));

        assertFalse("Invalid email format should return false", DoctorValidator.isValidEmail("john.doeexample.com"));

        assertFalse("Email with invalid TLD should return false", DoctorValidator.isValidEmail("john@example.c"));

        assertFalse("Null email should return false", DoctorValidator.isValidEmail(null));
    }

    @Test
    public void testIsValidMobile() {
        assertTrue("Valid mobile number should return true", DoctorValidator.isValidMobile("(123)-456-7890"));

        assertFalse("Mobile without proper parentheses should return false", DoctorValidator.isValidMobile("123-456-7890"));

        assertFalse("Mobile with wrong format should return false", DoctorValidator.isValidMobile("(123)456-7890"));

        assertFalse("Null mobile should return false", DoctorValidator.isValidMobile(null));
    }
}
