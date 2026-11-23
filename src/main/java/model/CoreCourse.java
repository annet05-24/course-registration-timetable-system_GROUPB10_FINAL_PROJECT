package main.java.model;

// A required course for the student's major
public class CoreCourse extends Course {

    public CoreCourse(String courseCode, String courseName, double units, int recommendedYear) {
        super(courseCode, courseName, units, recommendedYear);
    }

    @Override
    public String getCourseType() {
        return "Core";
    }
}
