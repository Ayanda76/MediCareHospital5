/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package medicarehospital5;
import java.util.*;
/**
 *
 * @author Student
 */
public class HospitalManager {
   
    private ArrayList<Patient> patients = new ArrayList<>();
    private Bed[][] wardLayout = new Bed[4][5];

    public HospitalManager() {
        int count = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                wardLayout[i][j] = new Bed(String.format("B%02d", count++));
            }
        }
    }

    public boolean registerPatient(Patient p) {
        if (searchPatient(p.getPatientID())!= null) return false;
        patients.add(p);
        return true;
    }

    public Patient searchPatient(String id) {
        for (Patient p : patients) {
            if (p.getPatientID().equalsIgnoreCase(id)) return p;
        }
        return null;
    }

    public boolean updatePatient(String id, String newCondition) {
        Patient p = searchPatient(id);
        if (p!= null) {
            p.setMedicalCondition(newCondition);
            return true;
        }
        return false;
    }

    public boolean deletePatient(String id) {
        Patient p = searchPatient(id);
        if (p!= null) {
            if (p instanceof Inpatient) {
                String bed = ((Inpatient) p).getBedNumber();
                if (bed!= null) releaseBed(bed);
            }
            patients.remove(p);
            return true;
        }
        return false;
    }

    public Bed findBed(String bedID) {
        for (Bed[] row : wardLayout) {
            for (Bed b : row) {
                if (b.getBedID().equalsIgnoreCase(bedID)) return b;
            }
        }
        return null;
    }

    public boolean allocateBed(String patientID, String bedID) {
        Patient p = searchPatient(patientID);
        Bed b = findBed(bedID);
        if (p == null || b == null || b.isOccupied() || p.getCategory()!= PatientCategory.INPATIENT) return false;
        b.allocate(patientID);
        ((Inpatient) p).setBedNumber(bedID);
        return true;
    }

    public boolean releaseBed(String bedID) {
        Bed b = findBed(bedID);
        if (b!= null && b.isOccupied()) {
            b.release();
            return true;
        }
        return false;
    }

    public void displayWardLayout() {
        System.out.println("\n--- WARD LAYOUT 4x5 (20 Beds) ---");
        for (Bed[] row : wardLayout) {
            for (Bed b : row) {
                System.out.print(b.getBedID() + (b.isOccupied()? "[X] " : "[ ] "));
            }
            System.out.println();
        }
    }

    public ArrayList<Patient> getAllPatients() { return patients; }

    public ArrayList<Bed> getAvailableBeds() {
        ArrayList<Bed> list = new ArrayList<>();
        for (Bed[] row : wardLayout) for (Bed b : row) if (!b.isOccupied()) list.add(b);
        return list;
    }

    public ArrayList<Bed> getOccupiedBeds() {
        ArrayList<Bed> list = new ArrayList<>();
        for (Bed[] row : wardLayout) for (Bed b : row) if (b.isOccupied()) list.add(b);
        return list;
    }

    public void sortBySurname() {
        patients.sort(Comparator.comparing(Patient::getLastName));
    }
}

