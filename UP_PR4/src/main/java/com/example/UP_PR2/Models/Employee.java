package com.example.UP_PR2.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Обязательное поле")
    @Size(min = 1, max = 50, message = "Колл-во символов 1-50")
    private String surname;
    @NotBlank(message = "Обязательное поле")
    @Size(min = 1, max = 50, message = "Поле должно содержать от 1 до 50 символов")
    private String name;

    private String middleName;
    @NotNull(message = "Обязательное поле")
    //@Size(min = 1, max= 11,message = "Паспорт введен не верно")
    //@Pattern(regexp = "[0-9][0-9][0-9][0-9] [0-9][0-9][0-9][0-9][0-9][0-9]", message = "Паспорт не соответсвует маске ввода")
    private Long passport;
    @NotNull(message = "Обязательное поле")
    @Pattern(regexp = "[0-9][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]", message = "Дата должна быть записана в формате гггг-мм-дд")
    private String birthday;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @NotNull
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    @NotNull
    public Long getPassport() {
        return passport;
    }

    public void setPassport(Long passport) {
        this.passport = passport;
    }
    @NotNull
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Employee() { }

    public Employee(String surname, String name, String middleName, Long passport, String birthday) {
        this.surname = surname;
        this.name = name;
        this.middleName = middleName;
        this.passport = passport;
        this.birthday = birthday;
    }
}
