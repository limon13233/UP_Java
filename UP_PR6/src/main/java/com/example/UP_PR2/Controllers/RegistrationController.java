package com.example.UP_PR2.Controllers;

import com.example.UP_PR2.Models.Employee;
import com.example.UP_PR2.Models.Role;
import com.example.UP_PR2.Repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/registration")
    public String reg(Employee employee) {
        return ("registration");
    }

    @PostMapping("/registration")
    public String reg(Employee employee,
                      Model model) {

        if(employeeRepository.findByusername(employee.getUsername()) != null) {
            model.addAttribute("error", "Логин занят!");
            return ("registration");
        }

        employee.setActive(true);
        employee.setRoles(Collections.singleton(Role.USER));

        employeeRepository.save(employee);

        return ("redirect:/login");
    }
}
