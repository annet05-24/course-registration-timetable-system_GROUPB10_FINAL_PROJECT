package main.java.validation;

import main.java.model.Course;
import main.java.model.Student;
import main.java.model.Timetable;

public class CreditValidator {

    public boolean checkCreditLimit(Student student, Course course, Timetable timetable) {
        int total = timetable.getTotalUnits() + course.getUnits();
        return total <= student.getMaxUnits();
    }

    /** Units the student can still add before hitting the limit (never negative). */
    public int getRemainingUnits(Student student, Timetable timetable) {
        return Math.max(0, student.getMaxUnits() - timetable.getTotalUnits());
    }

    /** Human-readable explanation shown to the student when the check fails. */
    public String describeLimitProblem(Student student, Course course, Timetable timetable) {
        int current = timetable.getTotalUnits();
        return "Cannot register for " + course.getCourseCode() + ": it adds "
                + course.getUnits() + " credits to your current " + current
                + ", which exceeds your limit of " + student.getMaxUnits() + ".";
    }
}
    
}
