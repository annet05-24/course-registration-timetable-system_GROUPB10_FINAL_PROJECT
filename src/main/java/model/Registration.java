package main.java.model;

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
        public String toString() { 
            return (success ? "OK: " : "REJECTED: ") + message; 
        }
    }

    
    public RegistrationResult registerCourse(Section section) {
        if (section == null) {
            return new RegistrationResult(false, "No section was selected.");
        }
        Course course = section.getCourse();      // a Course reference: could be Core, Elective or Lab

        if (timetable.hasCourse(course)) {
            return new RegistrationResult(false,
                    "You are already registered for " + course.getCourseCode() + ".");
        }
        if (!creditValidator.checkCreditLimit(student, course, timetable)) {
            return new RegistrationResult(false,
                    creditValidator.describeLimitProblem(student, course, timetable));
        }
        if (clashDetector.detectClash(section, timetable)) {
            Section other = clashDetector.findConflicts(section, timetable).get(0);
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

    /** Drops the registered section of the given course, if there is one. */
    public RegistrationResult dropCourse(Course course) {
        if (course == null || !timetable.hasCourse(course)) {
            return new RegistrationResult(false, "You are not registered for that course.");
        }
        timetable.removeSection(course);
        return new RegistrationResult(true,
                "Dropped " + course.getCourseCode() + ". Total: "
                        + timetable.getTotalUnits() + "/" + student.getMaxUnits() + ".");
    }

    public Student getStudent() { return student; }
    public String getSemester() { return semester; }
    public Timetable getTimetable() { return timetable; }
}
    

