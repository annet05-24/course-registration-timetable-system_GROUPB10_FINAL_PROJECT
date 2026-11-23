package main.java.model;

// A course the student chooses outside their required courses
public class ElectiveCourse extends Course {

    public ElectiveCourse(String courseCode, String courseName, double units, int recommendedYear) {
        super(courseCode, courseName, units, recommendedYear);
    }

    @Override
    public String getCourseType() {
        return "Elective";
    }
}
