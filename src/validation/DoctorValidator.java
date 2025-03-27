/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;
/**
 *
 * @author Acer
 */
public class DoctorValidator {

    private static final String DATE_PATTERN = "dd/MM/yyyy";
    private static final SimpleDateFormat dateFormatter;
    private static final Pattern emailRegex;
    private static final Pattern phoneRegex;

    static {
        dateFormatter = new SimpleDateFormat(DATE_PATTERN);
        dateFormatter.setLenient(false);
        emailRegex = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
        phoneRegex = Pattern.compile("^\\(\\d{3}\\)-\\d{3}-\\d{4}$");
    }

    public static boolean isValidName(String name) {
        if (name == null) {
            return false;
        }
        return name.length() <= 50;
    }

    public static boolean isValidDateOfBirth(String dob) {
        if (dob == null) {
            return false;
        }
        try {
            dateFormatter.parse(dob);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static boolean isValidSpecialization(String specialization) {
        if (specialization == null) {
            return false;
        }
        return specialization.length() <= 255;
    }

    public static boolean isValidAvailability(int availability) {
        return availability >= 0 && availability <= 3;
    }

    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        return emailRegex.matcher(email).matches();
    }

    public static boolean isValidMobile(String mobile) {
        if (mobile == null) {
            return false;
        }
        return phoneRegex.matcher(mobile).matches();
    }
}

