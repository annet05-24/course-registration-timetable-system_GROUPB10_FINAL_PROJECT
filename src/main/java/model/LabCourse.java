package main.java.model;

// A practical course. Unlike the other types, its sections must include an actual lab session.
public class LabCourse extends Course {

    public LabCourse(String courseCode, String courseName, double units, int recommendedYear) {
        super(courseCode, courseName, units, recommendedYear);
    }

    @Override
    public String getCourseType() {
        return "Lab";
    }

    // Overrides the default rule: a lab section with only lectures is not accepted
    @Override
    public boolean validateSection(Section section) {
        for (TimeSlot session : section.getSessions()) {
            if (session.getSessionType().equalsIgnoreCase("Lab")) {
                return true;
            }
        }
        return false;
    }
}
