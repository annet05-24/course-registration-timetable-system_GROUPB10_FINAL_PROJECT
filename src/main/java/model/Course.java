package main.java.model;

import java.util.ArrayList;
import java.util.HashSet;

// Parent class for every course type. It is abstract because a course is always
// either a core, elective or lab course, never just a plain "Course".
public abstract class Course {

    private String courseCode;
    private String courseName;
    private double units;
    private int recommendedYear;
    private HashSet<String> requiredMajors;
    private ArrayList<Section> sections;

    public Course(String courseCode, String courseName, double units, int recommendedYear) {
        if (units <= 0) {
            throw new IllegalArgumentException("A course must carry more than zero credits.");
        }
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.units = units;
        this.recommendedYear = recommendedYear;
        this.requiredMajors = new HashSet<>();
        this.sections = new ArrayList<>();
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public double getUnits() {
        return units;
    }

    public int getRecommendedYear() {
        return recommendedYear;
    }

    // Returns a copy so other classes can read the sections but cannot add or remove them directly
    public ArrayList<Section> getSections() {
        return new ArrayList<>(sections);
    }

    public void addSection(Section section) {
        sections.add(section);
    }

    // HashSet ignores duplicates, so adding the same major twice has no effect
    public void addRequiredMajor(String major) {
        requiredMajors.add(major);
    }

    public boolean isRequiredFor(String major) {
        return requiredMajors.contains(major);
    }

    // Basic rule for all courses: a section has to meet at least once a week.
    // Subclasses can override this to add rules of their own.
    public boolean validateSection(Section section) {
        return !section.getSessions().isEmpty();
    }

    // Each subclass decides what type it reports
    public abstract String getCourseType();

    @Override
    public String toString() {
        return courseCode + " " + courseName + " (" + units + " credits, " + getCourseType() + ")";
    }
}
