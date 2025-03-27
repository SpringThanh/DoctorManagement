/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import validation.DoctorValidator;
/**
 *
 * @author Acer
 */
public class DoctorManager {
    private List<Doctor> doctorList;
    private int nextId;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    public DoctorManager() {
        this.doctorList = new ArrayList<>();
        this.nextId = 1;
    }

    public void initializeSampleDoctors() {
        try {
            addDoctor("Nguyễn Văn An", "10/02/1980", "Tim mạch", 1, "nguyenvanan@gmail.com", "(111)-222-3333");
            addDoctor("Trần Thị Bích", "15/08/1985", "Nhi khoa", 0, "tranthibich@gmail.com", "(222)-333-4444");
            addDoctor("Lê Văn Cần", "05/12/1977", "Chấn thương chỉnh hình", 2, "levancan@gmail.com", "(333)-444-5555");
            addDoctor("Phạm Thị Dung", "22/04/1990", "Da liễu", 3, "phamthidung@gmail.com", "(444)-555-6666");
            addDoctor("Hoàng Văn Bảo", "08/09/1982", "Tiêu hóa", 1, "hoangvanbao@gmail.com", "(555)-666-7777");
            addDoctor("Đỗ Thị Phương", "30/11/1979", "Tai mũi họng", 1, "dothiphuong@gmail.com", "(666)-777-8888");
            addDoctor("Phan Văn Giang", "14/07/1983", "Nội khoa", 2, "phanvangiang@gmail.com", "(777)-888-9999");
            addDoctor("Vũ Thị Hòa", "27/03/1988", "Sản phụ khoa", 0, "vuthihoa@gmail.com", "(888)-999-0000");
            addDoctor("Bùi Văn Nam", "19/06/1975", "Phẫu thuật", 3, "buivannam@gmail.com", "(999)-000-1111");
            addDoctor("Trịnh Thị Vân", "04/01/1986", "Hồi sức", 1, "trinhthivan@gmail.com", "(000)-111-2222");
        } catch (Exception e) {
            System.out.println("Error initializing sample doctors: " + e.getMessage());
        }
    }

    public void addDoctor(String name, String dob, String specialization, int availability, String email, String mobile) {
        if (!DoctorValidator.isValidName(name)
                || !DoctorValidator.isValidDateOfBirth(dob)
                || !DoctorValidator.isValidSpecialization(specialization)
                || !DoctorValidator.isValidAvailability(availability)
                || !DoctorValidator.isValidEmail(email)
                || !DoctorValidator.isValidMobile(mobile)) {
            System.out.println("Invalid doctor data. Please check your input.");
            return;
        }
        try {
            Date dateOfBirth = DATE_FORMAT.parse(dob);
            Doctor newDoctor = new Doctor(nextId++, name, dateOfBirth, specialization, availability, email, mobile);
            doctorList.add(newDoctor);
            System.out.println("Doctor added with valid availability");
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use dd/MM/yyyy.");
        }
    }

    public void editDoctor(int id, String name, String dob, String specialization, int availability, String email, String mobile) {
        Doctor doctorToEdit = null;
        for (Doctor d : doctorList) {
            if (d.getId() == id) {
                doctorToEdit = d;
                break;
            }
        }
        if (doctorToEdit == null) {
            System.out.println("Doctor not found.");
            return;
        }

        if (!name.trim().isEmpty() && DoctorValidator.isValidName(name)) {
            doctorToEdit.setName(name);
        }
        if (!dob.trim().isEmpty() && DoctorValidator.isValidDateOfBirth(dob)) {
            try {
                Date newDOB = DATE_FORMAT.parse(dob);
                doctorToEdit.setDateOfBirth(newDOB);
            } catch (ParseException e) {
                System.out.println("Invalid date format.");
            }
        }
        if (!specialization.trim().isEmpty() && DoctorValidator.isValidSpecialization(specialization)) {
            doctorToEdit.setSpecialization(specialization);
        }
        if (availability != -1 && DoctorValidator.isValidAvailability(availability)) {
            doctorToEdit.setAvailability(availability);
        }
        if (!email.trim().isEmpty() && DoctorValidator.isValidEmail(email)) {
            doctorToEdit.setEmail(email);
        }
        if (!mobile.trim().isEmpty() && DoctorValidator.isValidMobile(mobile)) {
            doctorToEdit.setMobile(mobile);
        }
        System.out.println("Doctor updated successfully!");
    }

    public void deleteDoctor(int id) {
        boolean removed = doctorList.removeIf(doc -> doc.getId() == id);
        if (removed) {
            System.out.println("Doctor deleted successfully!");
        } else {
            System.out.println("Doctor not found.");
        }
    }

    public Doctor searchDoctorById(int id) {
        for (Doctor d : doctorList) {
            if (d.getId() == id) {
                return d;
            }
        }
        return null;
    }

    public List<Doctor> searchDoctorByName(String name) {
        List<Doctor> results = new ArrayList<>();
        for (Doctor d : doctorList) {
            if (d.getName().toLowerCase().contains(name.toLowerCase())) {
                results.add(d);
            }
        }
        return results;
    }

    public void sortDoctorsByDOB() {
        doctorList.sort(Comparator.comparing(Doctor::getDateOfBirth));
    }

    public void displayDoctors() {
        if (doctorList.isEmpty()) {
            System.out.println("No doctors available.");
        } else {
            doctorList.forEach(System.out::println);
        }
    }
}


