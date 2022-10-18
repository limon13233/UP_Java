package com.example.UP_PR2.Controllers;

import com.example.UP_PR2.Models.Employee;
import com.example.UP_PR2.Repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
//@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/employee")
    public String Employee(Model model) {

        Iterable<Employee> listEmployee = employeeRepository.findAll();
        model.addAttribute(("list_employee"), listEmployee);
        return ("employee/index");
    }

    @GetMapping("/employee/add")
    public String EmployeeAddView() {return ("employee/employeeADD");}

    @PostMapping("/employee/add")
    public String EmployeeAdd(@RequestParam String surname,
                              @RequestParam String name,
                              @RequestParam String middleName,
                              @RequestParam Integer passport,
                              @RequestParam String birthday) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        Employee employee = new Employee(surname, name, middleName, passport, formatter.parse(birthday, new ParsePosition(0)));
        employeeRepository.save(employee);
        return ("redirect:/employee");
    }

    @GetMapping("/filterACCU")
    public String EmployeeFilterACCU(Model model,
                                     @RequestParam(name = "search") String surname) {

        List<Employee> employeeList = employeeRepository.findBySurname(surname);
        model.addAttribute("searchRes", employeeList);
        return ("employee/employeeFilter");
    }

    @GetMapping("/filter")
    public String EmployeeFilter(Model model,
                                 @RequestParam(name = "search") String surname) {

        List<Employee> employeeList = employeeRepository.findBySurnameContains(surname);
        model.addAttribute("searchRes", employeeList);
        return ("employee/employeeFilter");
    }

    @GetMapping("/employee/details/{id}")
    public String EmployeeDetails(Model model,
                             @PathVariable long id) {

        Optional<Employee> car = employeeRepository.findById(id);
        ArrayList<Employee> result = new ArrayList<>();

        car.ifPresent(result::add);
        model.addAttribute("employee", result);
        return ("/employee/employee-details");
    }

    @GetMapping("employee/delete/{id}")
    public String EmployeeDelete(@PathVariable long id) {

        employeeRepository.deleteById(id);
        return("redirect:/employee");
    }

    @GetMapping("employee/employee-edit/{id}")
    public String CarEdit(Model model,
                          @PathVariable long id) {

        Employee emp = employeeRepository.findById(id).orElseThrow();
        model.addAttribute("employee", emp);
        return("/employee/employee-edit");
    }
    @PostMapping("employee/employee-edit/{id}")
    public String employeeEdit(Model model,
                          @PathVariable long id,
                          @RequestParam String surname,
                          @RequestParam String name,
                          @RequestParam String middleName,
                          @RequestParam Integer passport,
                          @RequestParam String birthday) {

        Employee emp = employeeRepository.findById(id).orElseThrow();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        emp.setName(name);
        emp.setBirthday(formatter.parse(birthday, new ParsePosition(0)));
        emp.setPassport(passport);
        emp.setMiddleName(middleName);
        emp.setSurname(surname);
        model.addAttribute("employee", emp);
        employeeRepository.save(emp);
        return("/employee/employee-details");
    }
}
