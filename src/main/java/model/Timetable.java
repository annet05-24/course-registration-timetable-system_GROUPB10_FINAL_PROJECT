package main.java.model;

import java.util.ArrayList;

public class Timetable {

    private ArrayList<Section> registeredSections;

    public Timetable() {
        registeredSections = new ArrayList<>();
    }

    public ArrayList<Section> getRegisteredSections() {
        return registeredSections;
    }

    public void addSection(Section section) {
        registeredSections.add(section);
    }

    public void removeSection(Section section) {
        registeredSections.remove(section);
    }

    public boolean hasCourse(Course course) {

        for (Section section : registeredSections) {
            if (section.getCourse().getCourseCode()
                    .equals(course.getCourseCode())) {
                return true;
            }
        }

        return false;
    }

    public double getTotalUnits() {

        double total = 0;

        for (Section section : registeredSections) {
            total += section.getCourse().getUnits();
        }

        return total;
    }

    public ArrayList<TimeSlot> getSessionsForDay(String day) {

        ArrayList<TimeSlot> dailySessions = new ArrayList<>();

        for (Section section : registeredSections) {

            for (TimeSlot session : section.getSessions()) {

                if (session.getDay().equalsIgnoreCase(day)) {
                    dailySessions.add(session);
                }
            }
        }

        return dailySessions;
    }
}