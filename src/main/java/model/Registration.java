package main.java.model;

import java.util.ArrayList;
import main.java.validation.ClashDetector;
import main.java.validation.CreditValidator;

public class Registration {

    private final Student student;
    private final String semester;
    private final Timetable timetable;
    private final CreditValidator creditValidator;
    private final ClashDetector clashDetector;


    public Registration(Student student, String semester) {
        this.student = student;
        this.semester = semester;
        this.timetable = new Timetable();            // composition: Registration owns it
        this.creditValidator = new CreditValidator();
        this.clashDetector = new ClashDetector();
    }

    
    public static class RegistrationResult {
        private final boolean success;
        private final String message;

        public RegistrationResult(boolean success, String message) {
            this.success = success;
            this.message = message;
        }
        public boolean isSuccess() { 
            return success; 

        }
        public String getMessage() { 
            return message; 

        }
        @Override
        public String toString() {
            return (success ? "OK: " : "REJECTED: ") + message;
        }
    }

    
    public RegistrationResult registerCourse(Section section) {
        if (section == null) {
            return new RegistrationResult(false, "No section was selected.");
        }
        Course course = section.getCourse();      // a Course reference: could be Core, Elective or Lab

        // The checks run in this order on purpose. The duplicate check comes first so that
        // taking the same course twice is reported as a duplicate, not as going over the limit.

        if (timetable.hasCourse(course)) {
            return new RegistrationResult(false,
                    "You are already registered for " + course.getCourseCode() + ".");
        }
        if (!creditValidator.checkCreditLimit(student, course, timetable)) {
            return new RegistrationResult(false,
                    creditValidator.describeLimitProblem(student, course, timetable));
        }
        // findConflicts gives back every registered section that overlaps, so one call
        // tells us both whether there is a clash and which course it is with
        ArrayList<Section> conflicts = clashDetector.findConflicts(section, timetable);
        if (!conflicts.isEmpty()) {
            Section other = conflicts.get(0);
            return new RegistrationResult(false,
                    "Time clash: " + course.getCourseCode() + " overlaps with "
                            + other.getCourse().getCourseCode() + ".");
        }
        if (!course.validateSection(section)) {
            return new RegistrationResult(false,
                    "Section " + section.getSectionID() + " does not meet the rules for a "
                            + course.getCourseType() + " course.");
        }

        timetable.addSection(section);
        return new RegistrationResult(true,
                "Registered for " + course.getCourseCode() + " (" + course.getUnits()
                        + " credits). Total: " + timetable.getTotalUnits() + "/"
                        + student.getMaxUnits() + ".");
    }

    // A student drops a course, but the timetable stores sections,
    // so we first find which section of that course they are in
    public RegistrationResult dropCourse(Course course) {
        if (course == null || !timetable.hasCourse(course)) {
            return new RegistrationResult(false, "You are not registered for that course.");
        }
        Section toRemove = null;
        for (Section section : timetable.getRegisteredSections()) {
            if (section.getCourse().getCourseCode().equals(course.getCourseCode())) {
                toRemove = section;
            }
        }
        timetable.removeSection(toRemove);
        return new RegistrationResult(true,
                "Dropped " + course.getCourseCode() + ". Total: "
                        + timetable.getTotalUnits() + "/" + student.getMaxUnits() + ".");
    }

    public Student getStudent() { return student; }
    public String getSemester() { return semester; }
    public Timetable getTimetable() { return timetable; }
}
