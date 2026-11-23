package main.java.validation;

import main.java.model.Course;
import main.java.model.Student;
import main.java.model.Timetable;

// Checks a new course against the student's semester credit limit.
// Credits are doubles because some courses, such as labs, carry half a credit.
public class CreditValidator {

    // Reaching the limit exactly is allowed, only going over it is not
    public boolean checkCreditLimit(Student student, Course course, Timetable timetable) {
        double total = timetable.getTotalUnits() + course.getUnits();
        return total <= student.getMaxUnits();
    }

    // How many credits the student can still take. Never goes below zero.
    public double getRemainingUnits(Student student, Timetable timetable) {
        return Math.max(0, student.getMaxUnits() - timetable.getTotalUnits());
    }

    // The message the student sees when a course is turned down for going over the limit
    public String describeLimitProblem(Student student, Course course, Timetable timetable) {
        return "Cannot register for " + course.getCourseCode() + ": it adds "
                + course.getUnits() + " credits to your current " + timetable.getTotalUnits()
                + ", which exceeds your limit of " + student.getMaxUnits()
                + ". You have " + getRemainingUnits(student, timetable) + " credits left.";
    }
}
