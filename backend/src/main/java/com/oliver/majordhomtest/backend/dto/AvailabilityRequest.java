package com.oliver.majordhomtest.backend.dto;

public class AvailabilityRequest {
    private String day;
    private String hour;
    private String minute;

    public AvailabilityRequest() {
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }
}
