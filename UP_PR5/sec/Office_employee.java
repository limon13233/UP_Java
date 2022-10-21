package com.example.UP_PR2.Models;

import com.example.UP_PR2.Repositories.EmployeeRepository;
import com.example.UP_PR2.Repositories.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Office_employee {
    @Id
    private String officeid;

    private String employee_id;

    public String getoffice_id() {
        return officeid;
    }

    public String getemployee_id() {
        return employee_id;
    }

    EmployeeRepository employeerep;
    OfficeRepository officeRepository;
    public String GetSurname(){

        return employeerep.findByid(getemployee_id()).getSurname();
    }
    public String GetOffice(){
        return officeRepository.findByid(getoffice_id()).getAddress();
    }
    public String GetTime(Office id){

        return officeRepository.findByid(getoffice_id()).getSchedule();
    }
}

