package main.java.model;

import java.time.LocalTime;

public class TimeSlot {
    private String day;
    private LocalTime startTime;
    private LocalTime endTime;
    private String venue;
    private String sessionType;

public TimeSlot(String day, LocalTime startTime, LocalTime endTime, String venue,  String sessionType ) {
    this.day = day;
    this. startTime = startTime;
    this.endTime = endTime;
    this.venue = venue;
    this.sessionType = sessionType;

}
public String getDay() {
    return day;
}
public LocalTime getStartTime() {
    return startTime;
}
public LocalTime getEndTime() {
    return endTime;
}
public String getVenue() {
    return venue;
}
public String getSessionType() {
    return sessionType;
}

public boolean overlapsWith(TimeSlot other) {
    if (!day.equalsIgnoreCase(other.getDay())) {
        return false; // Different days, no overlap
    }
    return this.startTime.isBefore(other.getEndTime()) && other.getStartTime().isBefore(this.endTime);
}
}




