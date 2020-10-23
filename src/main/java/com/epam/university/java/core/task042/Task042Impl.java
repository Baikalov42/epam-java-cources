package com.epam.university.java.core.task042;

import java.time.LocalTime;
import java.util.List;

public class Task042Impl implements Task042 {

    private final LocalTime morningTime = LocalTime.parse("09:00");
    private final LocalTime eveningTime = LocalTime.parse("18:00");

    @Override
    public BookingResponse checkAvailability(List<String> schedule, String currentTime) {

        if (schedule == null || currentTime == null) {
            throw new IllegalArgumentException();
        }

        LocalTime current = LocalTime.parse(currentTime.substring(0, 5));
        LocalTime temp;

        if (current.compareTo(LocalTime.parse("09:00")) >= 0) {
            temp = current;

        } else {
            temp = morningTime;
        }

        if (schedule.size() == 0) {
            return getResponse(temp, current);
        }

        for (String string : schedule) {

            LocalTime start = LocalTime.parse(string.substring(0, 5));
            LocalTime end = LocalTime.parse(string.substring(6, 11));

            if (temp.compareTo(end) < 0 && temp.compareTo(start) >= 0) {
                temp = end;
            }
        }
        return getResponse(temp, current);
    }

    /**
     * Get response method.
     * @param temp temp time
     * @param current current time
     * @return answer
     */

    public BookingResponse getResponse(LocalTime temp, LocalTime current) {

        if (temp.compareTo(eveningTime) < 0 && temp.compareTo(current) == 0) {
            return new AvailableResponse();

        } else if (temp.compareTo(eveningTime) < 0) {

            TimeProposalResponse timeProposalResponse = new TimeProposalResponse();
            timeProposalResponse.setProposedTime(temp.toString());

            return timeProposalResponse;

        } else {
            return new BusyResponse();
        }
    }
}
