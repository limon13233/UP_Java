package com.example.UP_PR2.Models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String address;
    private String schedule;

    @ManyToMany
    @JoinTable(name = "office_employee",
    joinColumns = @JoinColumn(name ="office_id"),
    inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private List<Employee> employees;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String Address) { this.address = Address; }

    public String getSchedule() {
        return schedule;
    }
    public void setSchedule(String Schedule) {
        this.schedule = Schedule;
    }


    public Office() { }

    public Office(String address, String schedule) {
        this.address = address;
        this.schedule = schedule;

    }

}
