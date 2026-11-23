package main.java.model;

// A student taking part in registration. maxUnits is the most credits they can carry in a semester.
public class Student {

    private String studentID;
    private String name;
    private String major;
    private int yearGroup;
    private double maxUnits;

    public Student(String studentID, String name, String major, int yearGroup, double maxUnits) {
        // A limit of zero or less would block every registration, so we stop it here
        if (maxUnits <= 0) {
            throw new IllegalArgumentException("Credit limit must be more than zero.");
        }
        this.studentID = studentID;
        this.name = name;
        this.major = major;
        this.yearGroup = yearGroup;
        this.maxUnits = maxUnits;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public String getMajor() {
        return major;
    }

    public int getYearGroup() {
        return yearGroup;
    }

    public double getMaxUnits() {
        return maxUnits;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setYearGroup(int yearGroup) {
        this.yearGroup = yearGroup;
    }

    @Override
    public String toString() {
        return name + " (" + studentID + ")";
    }
}
