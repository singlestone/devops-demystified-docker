package com.singlestoneconsulting.devops.participant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "participants")
public class Participant {
    @Id
    private String phoneNumber;
    private String name;
    private java.util.Date registered;

    public Participant() {
    }

    public Participant(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.registered = new Date();
    }

    public Participant(final String phoneNumber, final String name) {
        this(phoneNumber);
        this.setName(name);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getRegistered() {
        return registered == null ? null : new Date(registered.getTime());
    }

    public void setRegistered(final Date registered) {
        this.registered = registered == null ? null : new Date(registered.getTime());
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Participant that = (Participant) o;

        return phoneNumber.equals(that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return phoneNumber.hashCode();
    }
}
