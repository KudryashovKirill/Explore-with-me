package com.example.ExploreWithMeStats.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Table(name = "visits")
@Entity
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "address", nullable = false)
    String address;
    @Column(name = "date_start", nullable = false)
    LocalDate dateStart;
    @Column(name = "date_end", nullable = false)
    LocalDate dateEnd;
    @Column(name = "user_ip", nullable = false)
    String userIp;

    public Visit(String address, LocalDate dateStart, LocalDate dateEnd, String userIp) {
        this.address = address;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.userIp = userIp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }
}
