/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package medicarehospital5;
import java.util.Scanner;
/**
 *
 * @author Student
 */
public class Main {
   
    public static void main(String[] args) {
        HospitalManager manager = new HospitalManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("\n=== MEDICARE HOSPITAL 5 SYSTEM ===");
                System.out.println("1. Register Patient");
                System.out.println("2. Search Patient");
                System.out.println("3. Update Patient Condition");
                System.out.println("4. Delete Patient");
                System.out.println("5. Display All Patients");
                System.out.println("6. Allocate Bed (Inpatients Only)");
                System.out.println("7. Release Bed");
                System.out.println("8. Display Ward Layout");
                System.out.println("9. Reports");
                System.out.println("0. Exit");
                System.out.print("Choose: ");
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1 -> {
                        System.out.print("Patient ID: "); String id = sc.nextLine();
                        System.out.print("First Name: "); String fn = sc.nextLine();
                        System.out.print("Last Name: "); String ln = sc.nextLine();
                        System.out.print("Age: "); int age = Integer.parseInt(sc.nextLine());
                        System.out.print("Gender: "); String gender = sc.nextLine();
                        System.out.print("Condition: "); String cond = sc.nextLine();
                        System.out.print("Category (INPATIENT/OUTPATIENT/EMERGENCY): ");
                        PatientCategory cat = PatientCategory.valueOf(sc.nextLine().toUpperCase().trim());
                        Patient p = (cat == PatientCategory.INPATIENT)? new Inpatient(id, fn, ln, age, gender, cond, 1, null) : new Patient(id, fn, ln, age, gender, cond, cat);
                        System.out.println(manager.registerPatient(p)? "Registered Successfully!" : "Error: Duplicate ID!");
                    }
                    case 2 -> {
                        System.out.print("Enter Patient ID: "); String id = sc.nextLine();
                        Patient f = manager.searchPatient(id);
                        if (f!= null) f.displayDetails(); else System.out.println("Patient not found!");
                    }
                    case 3 -> {
                        System.out.print("Patient ID: "); String id = sc.nextLine();
                        System.out.print("New Condition: "); String c = sc.nextLine();
                        System.out.println(manager.updatePatient(id, c)? "Updated!" : "Not found!");
                    }
                    case 4 -> {
                        System.out.print("Patient ID to delete: "); String id = sc.nextLine();
                        System.out.println(manager.deletePatient(id)? "Deleted!" : "Not found!");
                    }
                    case 5 -> manager.getAllPatients().forEach(Patient::displayDetails);
                    case 6 -> {
                        System.out.print("Inpatient ID: "); String pid = sc.nextLine();
                        System.out.print("Bed (B01-B20): "); String bid = sc.nextLine();
                        System.out.println(manager.allocateBed(pid, bid)? "Bed allocated!" : "Failed! Bed occupied or not Inpatient.");
                    }
                    case 7 -> {
                        System.out.print("Bed to release (B01-B20): "); String bid = sc.nextLine();
                        System.out.println(manager.releaseBed(bid)? "Bed released!" : "Bed empty or not found!");
                    }
                    case 8 -> manager.displayWardLayout();
                    case 9 -> {
                        System.out.println("\n--- REPORTS ---");
                        System.out.println("Total Patients: " + manager.getAllPatients().size());
                        System.out.println("Occupied Beds: " + manager.getOccupiedBeds().size());
                        System.out.println("Available Beds: " + manager.getAvailableBeds().size());
                        System.out.printf("Occupancy Rate: %.1f%%\n", manager.getOccupiedBeds().size() / 20.0 * 100);
                        manager.displayWardLayout();
                    }
                    case 0 -> { System.out.println("Exiting..."); return; }
                    default -> System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}

