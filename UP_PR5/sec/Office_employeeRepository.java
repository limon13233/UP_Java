package com.example.UP_PR2.Repositories;

import com.example.UP_PR2.Models.Office_employee;
import org.springframework.data.repository.CrudRepository;

public interface Office_employeeRepository extends CrudRepository<Office_employee, Long> {

    Office_employee findByofficeid(String of_id);
}
