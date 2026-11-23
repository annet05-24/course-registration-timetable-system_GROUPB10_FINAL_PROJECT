package main.java.model;

import java.util.ArrayList;

// One offering of a course, for example CS213-A. It belongs to a cohort and has its own weekly sessions.
public class Section {

    private String sectionID;
    private String cohort;
    private String lecturerName;
    private int semester;
    private Course course;
    private ArrayList<TimeSlot> sessions;

    public Section(String sectionID, String cohort, String lecturerName, int semester, Course course) {
        this.sectionID = sectionID;
        this.cohort = cohort;
        this.lecturerName = lecturerName;
        this.semester = semester;
        this.course = course;
        this.sessions = new ArrayList<>();
    }

    public String getSectionID() {
        return sectionID;
    }

    public String getCohort() {
        return cohort;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public int getSemester() {
        return semester;
    }

    public Course getCourse() {
        return course;
    }

    // Returns a copy so the sessions can only be changed through addSession
    public ArrayList<TimeSlot> getSessions() {
        return new ArrayList<>(sessions);
    }

    public void addSession(TimeSlot session) {
        sessions.add(session);
    }

    // This is what shows up in the GUI list, e.g. "CS213-A  Object Oriented Programming  (Mon, Wed)"
    @Override
    public String toString() {
        String days = "";
        for (TimeSlot session : sessions) {
            if (!days.isEmpty()) {
                days += ", ";
            }
            days += session.getDay();
        }
        return sectionID + "  " + course.getCourseName() + "  (" + days + ")";
    }
}
