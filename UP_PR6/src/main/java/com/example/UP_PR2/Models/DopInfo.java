package com.example.UP_PR2.Models;

import javax.persistence.*;

@Entity
@Table(name = "DopInfo")
public class DopInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String phone;
    private String email;
    @OneToOne(optional = true, mappedBy = "dopinfo")
    private Employee owner;

    public Employee getOwner() {return owner;}
    public void setOwner(Employee owner){this.owner = owner;}

    public DopInfo() { }

    public DopInfo(String phone, String Email) {
        this.phone = phone;
        this.email = Email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String Email) {
        this.email = Email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String Phone) {
        this.phone = Phone;
    }

}

