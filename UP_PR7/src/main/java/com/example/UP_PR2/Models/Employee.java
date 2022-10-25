package com.example.UP_PR2.Models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee {

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

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

    @OneToOne(optional = true,cascade = CascadeType.ALL)
    @JoinColumn(name="dopinfo_id")
    private DopInfo dopinfo;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Post post;

    @ManyToMany
    @JoinTable(name = "office_employee",
            joinColumns = @JoinColumn(name ="employee_id"),
            inverseJoinColumns = @JoinColumn(name = "office_id"))
    private List<Office> offices;

    private String username;
    private String password;
    private Boolean active;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Set<Role> getRoles() { return roles; }
    public void setRoles(Set<Role> roles) { this.roles = roles; }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    @NotNull
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @NotNull
    public String getMiddleName() { return middleName; }
    public void setMiddleName(String middleName) { this.middleName = middleName; }

    @NotNull
    public Long getPassport() { return passport; }
    public void setPassport(Long passport) { this.passport = passport; }

    @NotNull
    public String getBirthday() { return birthday; }
    public void setBirthday(String birthday) { this.birthday = birthday; }

    public DopInfo getDopinfo() { return dopinfo; }
    public void setDopinfo(DopInfo dopinfo) { this.dopinfo = dopinfo; }

    public Post getPost() { return post; }

    public void setPost(Post post) { this.post = post; }

    public List<Office> getOffices() { return offices; }
    public void setOffices(List<Office> offices1) { this.offices = offices1; }

    public String getEmail(){return dopinfo.getEmail();}
    public String getPhone(){return dopinfo.getPhone();}

    public String getPostName(){return post.getPostName();}
    public String getSalary(){return post.getSalary();}
    public Employee() { }

    public Employee(String username, String password, Boolean active, Set<Role> roles, String surname, String name, String middleName, Long passport, String birthday, DopInfo dopinfo, Post post) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.roles = roles;
        this.surname = surname;
        this.name = name;
        this.middleName = middleName;
        this.passport = passport;
        this.birthday = birthday;
        this.dopinfo = dopinfo;
        this.post = post;
    }
}
