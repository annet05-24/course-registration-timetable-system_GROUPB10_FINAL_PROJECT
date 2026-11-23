package main.java.manager;

import java.util.ArrayList;
import java.util.HashMap;
import main.java.model.Course;
import main.java.model.Section;

// Keeps every course on offer. Courses are stored in a HashMap with the course code as the key,
// so finding a course by its code is a direct lookup instead of searching through a list.
public class CourseManager {

    private HashMap<String, Course> courses;

    public CourseManager() {
        courses = new HashMap<>();
    }

    // Two courses cannot share a code, otherwise findCourse would not know which one to return
    public void addCourse(Course course) {
        if (courses.containsKey(course.getCourseCode())) {
            throw new IllegalArgumentException(course.getCourseCode() + " already exists.");
        }
        courses.put(course.getCourseCode(), course);
    }

    // Returns null when no course has that code, so the caller has to check for it
    public Course findCourse(String courseCode) {
        return courses.get(courseCode);
    }

    // Section IDs are not keys in the map, so this goes through each course's sections
    public Section findSection(String sectionID) {
        for (Course course : courses.values()) {
            for (Section section : course.getSections()) {
                if (section.getSectionID().equals(sectionID)) {
                    return section;
                }
            }
        }
        return null;
    }

    // A HashMap does not keep any order, so the courses are copied into a list and sorted by code for display
    public ArrayList<Course> getAvailableCourses() {
        ArrayList<Course> list = new ArrayList<>(courses.values());
        list.sort((a, b) -> a.getCourseCode().compareTo(b.getCourseCode()));
        return list;
    }
}
