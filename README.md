# Course Registration and Timetable Management System

**Course:** CS 213 – Object-Oriented Programming  
**Group:** B10  
**Institution:** Ashesi University  
**Project Status:** Initial Development Stage

---

## 1. Project Overview

The Course Registration and Timetable Management System is a proposed Java desktop application designed to help Ashesi University students manage their course registration and organise their academic timetables.

The application will allow students to view available courses, select course sections, register for courses, and generate a weekly timetable. It will also incorporate validation mechanisms to prevent students from exceeding the permitted credit limit or registering for sections with overlapping lecture times.

The system is intended to simplify the course registration process and reduce the challenges associated with manually planning academic schedules.

## 2. Problem Statement

Students often need to consider several factors when registering for courses, including course availability, section allocation, lecturers, venues, meeting times and credit limits.

Managing these requirements manually can result in timetable clashes, registration errors and difficulties organising weekly academic schedules.

This project proposes a system that automates important parts of the course registration process by providing course selection, registration validation and timetable generation functionalities.

## 3. Project Objectives

The main objectives of the project are to:

- Simplify the course selection and registration process.
- Improve the management of courses and their respective sections.
- Enforce credit limits during registration.
- Detect and prevent timetable conflicts.
- Generate organised weekly timetables.
- Provide an accessible graphical user interface.

## 4. Proposed System Features

The proposed application will include:

1. **Student Management:** Maintain basic student information.
2. **Course Management:** Display and manage available courses.
3. **Section Selection:** Allow students to select available course sections.
4. **Course Registration:** Support course registration and dropping.
5. **Credit Validation:** Ensure students remain within the permitted credit limit.
6. **Clash Detection:** Identify overlapping lecture times before registration.
7. **Timetable Generation:** Generate a weekly timetable based on registered sections.
8. **Data Storage:** Save and retrieve relevant application information.
9. **Graphical User Interface:** Provide an interface through which students interact with the system.

## 5. Project Scope

### Included in the project

- Student management.
- Course and section management.
- Course registration and dropping.
- Credit limit validation.
- Timetable clash detection.
- Weekly timetable generation.
- JavaFX graphical user interface.
- Saving and loading application data.

### Excluded from the project

- Direct integration with Ashesi University's official registration platform.
- Tuition and course payment processing.
- Access to confidential student records or official university databases.
- Official university registration approval or confirmation.
- Examination management, grades, transcripts and GPA calculations.

## 6. Proposed System Design

The system will be developed using Object-Oriented Programming principles. Its design will consist of 14 proposed classes.

### Student and Course Management

- `Student`
- `Course` (Abstract Class)
- `CoreCourse`
- `ElectiveCourse`
- `LabCourse`
- `Section`
- `CourseManager`

### Registration and Validation

- `Registration`
- `CreditValidator`
- `ClashDetector`

### Timetable Management

- `Timetable`
- `TimeSlot`

### Application and Data Storage

- `RegistrationApp`
- `DataStore`

### Object-Oriented Programming Concepts

The proposed design will demonstrate:

- **Encapsulation:** Organising data and related methods within classes while controlling access to internal information.
- **Abstraction:** Using the abstract `Course` class to define common course characteristics and behaviours.
- **Inheritance:** Using `CoreCourse`, `ElectiveCourse` and `LabCourse` as subclasses of `Course`.
- **Polymorphism:** Allowing different course types to provide their own implementations of relevant methods.
- **Relationships between classes:** Applying association, aggregation, composition and dependency where appropriate.

The final class relationships and implementation details will be documented using UML diagrams.

## 7. Technologies and Tools

The proposed technologies include:

- **Java:** Main programming language.
- **JavaFX:** Graphical user interface development.
- **Java Collections Framework:** Managing and organising application data.
- **File Handling:** Saving and retrieving application data.
- **PlantUML:** Creating UML diagrams and documenting system design.

## 8. Project Directory Structure

The repository is organised as follows:

```text
course-registration-timetable-system/
│
├── src/
│   └── main/
│       └── java/
│
├── uml/
│
├── sample-data/
│
├── README.md
│
└── .gitignore
```

### Directory descriptions

- `src/main/java/`: Contains the Java source code for the application.
- `uml/`: Contains UML diagrams documenting the system design.
- `sample-data/`: Contains sample data used for development and testing.
- `README.md`: Provides an overview and documentation of the project.
- `.gitignore`: Specifies files and directories that should not be tracked by Git.

## 9. Proposed Testing Strategy

The system will be tested to verify that its registration and timetable management functionalities work correctly.

The proposed test cases include:

1. Rejecting registration when two selected sections have overlapping meeting times.
2. Accepting sections with back-to-back meeting times.
3. Rejecting registration when the student exceeds the credit limit.
4. Accepting registration when the student is exactly at the credit limit.
5. Preventing duplicate course registration.
6. Ensuring that dropping a course updates the student's total registered credits.
7. Verifying that the generated timetable correctly displays registered sections.
8. Verifying that saved data can be retrieved after closing and reopening the application.

## 10. Project Status

The project is currently in its initial development stage.

The repository structure and initial project documentation are being prepared. The proposed system design, class structure and implementation will be developed progressively according to the project roadmap.

Features will be marked as completed only after they have been implemented and tested.

## 11. Development Roadmap

| Milestone | Target Date |
|---|---|
| Project proposal | November 15, 2026 |
| Initial design | November 19, 2026 |
| Functional beta | November 26, 2026 |
| Feature freeze | November 30, 2026 |
| Final review | December 2, 2026 |
| Internal completion | December 3, 2026 |
| Final submission | December 4, 2026 |

## 12. Contributors

**Group B10**

- Annet Nassuna
- Ronald Selasie Ocloo
- Abigeal Seifu Admassu
- Josiah Oguntona

## 13. Installation and Execution

Installation and execution instructions will be added once the application structure and implementation are established.

The completed application is intended to run as a Java desktop application with a JavaFX graphical interface.

## 14. Future Updates

This README will be updated throughout the development process to reflect:

- Changes to the system design.
- Finalised class responsibilities and relationships.
- Implemented application features.
- Updated project directory structure.
- Testing results.
- Installation and execution instructions.

---

**Group B10 – CS 213 Object-Oriented Programming Project**
