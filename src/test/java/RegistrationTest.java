import java.time.LocalTime;
import main.java.model.*;

// Tests for the registration rules (Role B). Plain Java, so no JUnit setup is needed.
// Each test builds a fresh student and courses, so one test cannot affect another.
public class RegistrationTest {

    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {
        validRegistrationIsAccepted();
        sameCourseTwiceIsRejected();
        goingOverTheLimitIsRejected();
        reachingTheLimitExactlyIsAccepted();
        halfCreditLabIsCountedCorrectly();
        labSectionWithoutLabSessionIsRejected();
        clashingSectionIsRejected();
        backToBackSectionsAreAccepted();
        droppingACourseUpdatesTheTotal();
        droppingAnUnregisteredCourseIsRejected();
        noSelectedSectionIsRejected();

        System.out.println();
        System.out.println(passed + " passed, " + failed + " failed");
    }

    // The tests

    static void validRegistrationIsAccepted() {
        Registration reg = new Registration(student(5.0), "2026 Semester 1");
        Section s = section("CS213-A", core("CS213", 1.0), "Mon", 9, 0, 10, 30, "Lecture");

        Registration.RegistrationResult result = reg.registerCourse(s);

        check("valid registration is accepted", result.isSuccess());
        check("total is 1.0 after one course", reg.getTimetable().getTotalUnits() == 1.0);
    }

    static void sameCourseTwiceIsRejected() {
        Registration reg = new Registration(student(5.0), "2026 Semester 1");
        Course oop = core("CS213", 1.0);
        reg.registerCourse(section("CS213-A", oop, "Mon", 9, 0, 10, 30, "Lecture"));

        Registration.RegistrationResult result =
                reg.registerCourse(section("CS213-B", oop, "Tue", 13, 30, 15, 0, "Lecture"));

        check("same course in another section is rejected", !result.isSuccess());
        check("message says it is a duplicate", result.getMessage().contains("already registered"));
    }

    static void goingOverTheLimitIsRejected() {
        Registration reg = new Registration(student(2.0), "2026 Semester 1");
        reg.registerCourse(section("A-1", core("AAA", 1.0), "Mon", 8, 0, 9, 0, "Lecture"));
        reg.registerCourse(section("B-1", core("BBB", 1.0), "Tue", 8, 0, 9, 0, "Lecture"));

        Registration.RegistrationResult result =
                reg.registerCourse(section("C-1", core("CCC", 1.0), "Wed", 8, 0, 9, 0, "Lecture"));

        check("third course over a 2.0 limit is rejected", !result.isSuccess());
        check("total stays at 2.0", reg.getTimetable().getTotalUnits() == 2.0);
    }

    static void reachingTheLimitExactlyIsAccepted() {
        Registration reg = new Registration(student(2.0), "2026 Semester 1");
        reg.registerCourse(section("A-1", core("AAA", 1.0), "Mon", 8, 0, 9, 0, "Lecture"));

        Registration.RegistrationResult result =
                reg.registerCourse(section("B-1", core("BBB", 1.0), "Tue", 8, 0, 9, 0, "Lecture"));

        check("reaching the limit exactly is accepted", result.isSuccess());
    }

    static void halfCreditLabIsCountedCorrectly() {
        Registration reg = new Registration(student(1.5), "2026 Semester 1");
        reg.registerCourse(section("A-1", core("AAA", 1.0), "Mon", 8, 0, 9, 0, "Lecture"));

        Registration.RegistrationResult result =
                reg.registerCourse(section("L-1", lab("LLL", 0.5), "Fri", 14, 0, 16, 0, "Lab"));

        check("0.5 credit lab fits a 1.5 limit after 1.0", result.isSuccess());
        check("total is 1.5", reg.getTimetable().getTotalUnits() == 1.5);
    }

    static void labSectionWithoutLabSessionIsRejected() {
        Registration reg = new Registration(student(5.0), "2026 Semester 1");

        Registration.RegistrationResult result =
                reg.registerCourse(section("L-1", lab("LLL", 0.5), "Wed", 14, 0, 16, 0, "Lecture"));

        check("lab section with only a lecture is rejected", !result.isSuccess());
    }

    static void clashingSectionIsRejected() {
        Registration reg = new Registration(student(5.0), "2026 Semester 1");
        reg.registerCourse(section("A-1", core("AAA", 1.0), "Mon", 9, 0, 10, 30, "Lecture"));

        Registration.RegistrationResult result =
                reg.registerCourse(section("B-1", core("BBB", 1.0), "Mon", 10, 0, 11, 30, "Lecture"));

        check("overlapping section is rejected", !result.isSuccess());
        check("message names the clash", result.getMessage().contains("clash"));
    }

    static void backToBackSectionsAreAccepted() {
        Registration reg = new Registration(student(5.0), "2026 Semester 1");
        reg.registerCourse(section("A-1", core("AAA", 1.0), "Mon", 9, 0, 10, 30, "Lecture"));

        Registration.RegistrationResult result =
                reg.registerCourse(section("B-1", core("BBB", 1.0), "Mon", 10, 30, 12, 0, "Lecture"));

        check("section starting as another ends is accepted", result.isSuccess());
    }

    static void droppingACourseUpdatesTheTotal() {
        Registration reg = new Registration(student(5.0), "2026 Semester 1");
        Course oop = core("CS213", 1.0);
        reg.registerCourse(section("CS213-A", oop, "Mon", 9, 0, 10, 30, "Lecture"));

        Registration.RegistrationResult result = reg.dropCourse(oop);

        check("drop is accepted", result.isSuccess());
        check("total goes back to 0.0", reg.getTimetable().getTotalUnits() == 0.0);
        check("course is no longer in the timetable", !reg.getTimetable().hasCourse(oop));
    }

    static void droppingAnUnregisteredCourseIsRejected() {
        Registration reg = new Registration(student(5.0), "2026 Semester 1");

        Registration.RegistrationResult result = reg.dropCourse(core("CS213", 1.0));

        check("dropping a course you never took is rejected", !result.isSuccess());
    }

    static void noSelectedSectionIsRejected() {
        Registration reg = new Registration(student(5.0), "2026 Semester 1");

        Registration.RegistrationResult result = reg.registerCourse(null);

        check("pressing Register with nothing selected is rejected", !result.isSuccess());
    }

    // Helpers that keep the tests short

    static void check(String name, boolean condition) {
        if (condition) {
            passed++;
            System.out.println("PASS  " + name);
        } else {
            failed++;
            System.out.println("FAIL  " + name);
        }
    }

    static Student student(double limit) {
        return new Student("S001", "Sample Student", "Computer Science", 3, limit);
    }

    static Course core(String code, double units) {
        return new CoreCourse(code, code + " sample course", units, 2);
    }

    static Course lab(String code, double units) {
        return new LabCourse(code, code + " sample lab", units, 2);
    }

    // Builds a section with one weekly session and attaches it to its course
    static Section section(String id, Course course, String day,
                           int startH, int startM, int endH, int endM, String type) {
        Section s = new Section(id, "Cohort A", "Sample Lecturer", 1, course);
        s.addSession(new TimeSlot(day, LocalTime.of(startH, startM), LocalTime.of(endH, endM), "Room 101", type));
        course.addSection(s);
        return s;
    }
}
