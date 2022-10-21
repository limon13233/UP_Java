package com.example.UP_PR2.Controllers;

import com.example.UP_PR2.Models.*;
import com.example.UP_PR2.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Controller
//@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    DopInfoRepository dopInfoRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    OfficeRepository officeRepository;


    @GetMapping("/employee")
    public String Employee(Model model) {

        Iterable<Employee> listEmployee = employeeRepository.findAll();
        model.addAttribute(("list_employee"), listEmployee);
        Iterable<Office> listOffice = officeRepository.findAll();
        model.addAttribute("list_office",listOffice);


        return ("employee/index");
    }

    @GetMapping("/employee/add")
    public String EmployeeAddView(Employee employee, Model model)
    {
        Iterable<DopInfo> Dopinfos = dopInfoRepository.findAll();
        ArrayList<DopInfo> DopinfosArrayList = new ArrayList<>();

        for(DopInfo di: Dopinfos) {
            if(di.getOwner() == null) {
                DopinfosArrayList.add(di);
            }
        }
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("dopinf", DopinfosArrayList);
        model.addAttribute("post", posts);

        return ("employee/employeeADD");}

    @PostMapping("/employee/add")
    public String EmployeeAdd(
           @Valid Employee employee,
           BindingResult bindingResult,
           @RequestParam String number,
           @RequestParam String pname
    ) {
        if(bindingResult.hasErrors())
            return ("employee/employeeADD");
        DopInfo dp = dopInfoRepository.findByemail(number);
        Post post = postRepository.findBypostname(pname);
        employee.setDopinfo(dp);
        employee.setPost(post);
        employeeRepository.save(employee);
        return ("redirect:/employee");
//            @RequestParam String surname,
//                              @RequestParam String name,
//                              @RequestParam String middleName,
//                              @RequestParam Integer passport,
//                              @RequestParam String birthday


       // SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);


//        Employee employee = new Employee(surname, name, middleName, passport, formatter.parse(birthday, new ParsePosition(0)));

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

        Optional<Employee> employee = employeeRepository.findById(id);
        ArrayList<Employee> result = new ArrayList<>();

        employee.ifPresent(result::add);
        model.addAttribute("employee", result);
        return ("/employee/employee-details");
    }

    @GetMapping("employee/delete/{id}")
    public String EmployeeDelete(@PathVariable long id) {

        employeeRepository.deleteById(id);
        return("redirect:/employee");
    }

    @GetMapping("employee/employee-edit/{id}")
    public String EmployeeEdit(Model model, @PathVariable long id) {

        Employee emp = employeeRepository.findById(id).orElseThrow();
        model.addAttribute("employee", emp);
        return("/employee/employee-edit");

    }
    @PostMapping("employee/employee-edit/{id}")
    public String employeeEdit(@Valid Employee employee,
            BindingResult bindingResult
    ){
        if(bindingResult.hasErrors())
            return("/employee/employee-edit");

        employeeRepository.save(employee);

        return("redirect:/employee/details/" + employee.getId());
//            Model model,
//                               @PathVariable long id
//                          @RequestParam String surname,
//                          @RequestParam String name,
//                          @RequestParam String middleName,
//                          @RequestParam Integer passport,
//                          @RequestParam String birthday) {
//
//        Employee emp = employeeRepository.findById(id).orElseThrow();
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
//        emp.setName(name);
//        emp.setBirthday(formatter.parse(birthday, new ParsePosition(0)));
//        emp.setPassport(passport);
//        emp.setMiddleName(middleName);
//        emp.setSurname(surname);
//
//        model.addAttribute("employee", employee);
//        employeeRepository.save(employee);
//        return("/employee/employee-details");

    }

    @GetMapping("/employee/dop_infoADD")
    public String DopInfoAdd(Model model)
    {

        return ("/employee/dop_infoADD");
    }

    @PostMapping("/employee/dop_infoADD")
    public String DopInfoAdd(
                        @RequestParam String Phone,
                             @RequestParam String Email
    ) {
        DopInfo DI = new DopInfo(Phone,Email);
        dopInfoRepository.save(DI);
        return ("redirect:/employee");
    }

    @GetMapping("/employee/PostADD")
    public String PostAdd(Model model)
    {
        return ("/employee/PostADD");
    }

    @PostMapping("/employee/PostADD")
    public String PostAdd(
            @RequestParam String PostName,
            @RequestParam String Salary
    ) {
        Post post = new Post(PostName,Salary);
        postRepository.save(post);
        return ("redirect:/employee");
    }

    @GetMapping("/employee/OfficeADD")
    public String OfficeAdd(Model model)
    {
        return ("/employee/OfficeADD");
    }

    @PostMapping("/employee/OfficeADD")
    public String OfficeAdd(
            @RequestParam String Address,
            @RequestParam String Schedule
    ) {
        Office office = new Office(Address,Schedule);
        officeRepository.save(office);
        return ("redirect:/employee");
    }

    @GetMapping("/employee/Office_employeeADD")
    public String Office_employeeAdd(Model model)
    {
        Iterable<Employee> students = employeeRepository.findAll();
        model.addAttribute("employees", students);
        Iterable<Office> universities = officeRepository.findAll();
        model.addAttribute("offices", universities);

        return ("/employee/Office_employeeADD");
    }

    @PostMapping("/employee/Office_employeeADD")
    public String Office_employeeAdd(
            @RequestParam String office,
            @RequestParam String employee
    ) {
        Employee student2 = employeeRepository.findBySurname(Arrays.stream((employee.split(" "))).toList().get(0)).get(0);
        Office university2 = officeRepository.findByaddress(office);
        List<Office> of = student2.getOffices();
        of.add(university2);
        student2.setOffices(of);
        employeeRepository.save(student2);
        return ("redirect:/employee");
    }
}
