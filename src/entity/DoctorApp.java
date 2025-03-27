/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.List;
import java.util.Scanner;
/**
 *
 * @author Acer
 */
public class DoctorApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DoctorManager manager = new DoctorManager();
        manager.initializeSampleDoctors();

        boolean exit = false;
        while (!exit) {
            System.out.println("\n===== Doctor Management System =====");
            System.out.println("1. Add Doctor");
            System.out.println("2. Edit Doctor Information");
            System.out.println("3. Delete Doctor");
            System.out.println("4. Search Doctor by ID");
            System.out.println("5. Search Doctor by Name");
            System.out.println("6. Sort Doctors by Date of Birth");
            System.out.println("7. Display All Doctors");
            System.out.println("8. Exit");
            System.out.print("Select an option: ");

            int option;
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                continue;
            }

            switch (option) {
                case 1:
                    handleAddDoctor(manager, scanner);
                    break;
                case 2:
                    handleEditDoctor(manager, scanner);
                    break;
                case 3:
                    handleDeleteDoctor(manager, scanner);
                    break;
                case 4:
                    handleSearchById(manager, scanner);
                    break;
                case 5:
                    handleSearchByName(manager, scanner);
                    break;
                case 6:
                    manager.sortDoctorsByDOB();
                    System.out.println("Doctors sorted by Date of Birth.");
                    break;
                case 7:
                    manager.displayDoctors();
                    break;
                case 8:
                    System.out.println("Exiting program...");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
        scanner.close();
    }

    private static void handleAddDoctor(DoctorManager manager, Scanner scanner) {
        System.out.print("Enter doctor's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter DOB (dd/MM/yyyy): ");
        String dob = scanner.nextLine();
        System.out.print("Enter specialization: ");
        String specialization = scanner.nextLine();
        System.out.print("Enter availability (0-3): ");
        int availability;
        try {
            availability = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid availability input.");
            return;
        }
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter mobile ((000)-000-0000): ");
        String mobile = scanner.nextLine();

        manager.addDoctor(name, dob, specialization, availability, email, mobile);
    }

    private static void handleEditDoctor(DoctorManager manager, Scanner scanner) {
        System.out.print("Enter Doctor ID to edit: ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID input.");
            return;
        }

        System.out.print("Enter new name (or press Enter to skip): ");
        String name = scanner.nextLine();
        System.out.print("Enter new DOB (dd/MM/yyyy) (or press Enter to skip): ");
        String dob = scanner.nextLine();
        System.out.print("Enter new specialization (or press Enter to skip): ");
        String specialization = scanner.nextLine();
        System.out.print("Enter new availability (0-3) (-1 to skip): ");
        int availability;
        try {
            availability = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            availability = -1;
        }
        System.out.print("Enter new email (or press Enter to skip): ");
        String email = scanner.nextLine();
        System.out.print("Enter new mobile ((000)-000-0000) (or press Enter to skip): ");
        String mobile = scanner.nextLine();

        manager.editDoctor(id, name, dob, specialization, availability, email, mobile);
    }

    private static void handleDeleteDoctor(DoctorManager manager, Scanner scanner) {
        System.out.print("Enter Doctor ID to delete: ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID input.");
            return;
        }
        manager.deleteDoctor(id);
    }

    private static void handleSearchById(DoctorManager manager, Scanner scanner) {
        System.out.print("Enter Doctor ID to search: ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID input.");
            return;
        }
        Doctor doctor = manager.searchDoctorById(id);
        if (doctor != null) {
            System.out.println(doctor);
        } else {
            System.out.println("Doctor not found.");
        }
    }

    private static void handleSearchByName(DoctorManager manager, Scanner scanner) {
        System.out.print("Enter doctor's name to search: ");
        String name = scanner.nextLine();
        List<Doctor> results = manager.searchDoctorByName(name);
        if (results.isEmpty()) {
            System.out.println("No doctors found with that name.");
        } else {
            results.forEach(System.out::println);
        }
    }
}


