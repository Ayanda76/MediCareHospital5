/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package medicarehospital5;

/**
 *
 * @author Student
 */
public class Bed {
    private String bedID;
    private boolean isOccupied;
    private String patientID;

    public Bed(String bedID) {
        this.bedID = bedID;
        this.isOccupied = false;
        this.patientID = null;
    }

    public String getBedID() { return bedID; }
    public boolean isOccupied() { return isOccupied; }
    public String getPatientID() { return patientID; }

    public void allocate(String patientID) {
        this.isOccupied = true;
        this.patientID = patientID;
    }

    public void release() {
        this.isOccupied = false;
        this.patientID = null;
    }
}

