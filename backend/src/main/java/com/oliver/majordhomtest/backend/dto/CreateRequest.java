package com.oliver.majordhomtest.backend.dto;

import java.util.ArrayList;
import java.util.List;

public class CreateRequest {

    private String civility;
    private String lastName;
    private String firstName;
    private String email;
    private String phone;
    private String requestType;
    private String message;
    private List<AvailabilityRequest> availabilities = new ArrayList<>();

    public CreateRequest() {
    }

    public String getCivility() {
        return civility;
    }

    public void setCivility(String civility) {
        this.civility = civility;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<AvailabilityRequest> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(List<AvailabilityRequest> availabilities) {
        this.availabilities = availabilities != null ? availabilities : new ArrayList<>();
    }
}