package main.java.validation;

import java.util.ArrayList;
import java.util.List;
import main.java.model.Section;
import main.java.model.TimeSlot;
import main.java.model.Timetable;

public class ClashDetector {

    /**
     * Returns every registered {@link Section} in {@code timetable} whose
     * {@link TimeSlot}s overlap with any {@link TimeSlot} in {@code section},
     * or an empty list if there are no clashes.
     */
    public List<Section> findConflicts(Section section, Timetable timetable) {
        List<Section> conflictingSections = new ArrayList<>();
        if (section == null || timetable == null) {
            return conflictingSections;
        }

        List<TimeSlot> newSessions = section.getSessions();
        List<Section> registeredSections = timetable.getRegisteredSections();
        if (newSessions == null || newSessions.isEmpty()
                || registeredSections == null || registeredSections.isEmpty()) {
            return conflictingSections;
        }

        for (Section registered : registeredSections) {
            if (registered != null && hasOverlap(registered.getSessions(), newSessions)) {
                conflictingSections.add(registered);
            }
        }

        return conflictingSections;
    }

    /**
     * Overload matching the parameter order shown in the proposal's UML diagram.
     */
    public List<Section> findConflicts(Timetable timetable, Section section) {
        return findConflicts(section, timetable);
    }

    /**
     * Convenience helper returning {@code true} if at least one registered
     * section clashes with {@code section}.
     */
    public boolean detectClash(Timetable timetable, Section section) {
        return !findConflicts(section, timetable).isEmpty();
    }

    public boolean detectClash(Section section, Timetable timetable) {
        return !findConflicts(section, timetable).isEmpty();
    }

    private boolean hasOverlap(List<TimeSlot> registeredSlots, List<TimeSlot> newSlots) {
        if (registeredSlots == null || registeredSlots.isEmpty()) {
            return false;
        }
        for (TimeSlot existingSlot : registeredSlots) {
            if (existingSlot == null) {
                continue;
            }
            for (TimeSlot newSlot : newSlots) {
                if (newSlot != null && existingSlot.overlapsWith(newSlot)) {
                    return true;
                }
            }
        }
        return false;
    }
}   

