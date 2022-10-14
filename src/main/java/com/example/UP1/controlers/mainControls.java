package com.example.UP1.controlers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class mainControls {

    @GetMapping("/")
    public String index(){
        return ("index");
    }
    @GetMapping("/main/")
    public String home(Model model,
                       @RequestParam(name = "x1", defaultValue = "1") int x1,
                       @RequestParam(name = "x2", defaultValue = "1") int x2,
                       @RequestParam(name="sss")  String name){
        model.addAttribute("results", method(x1,x2,name));
        return ("home");
    }
    @PostMapping("/main/")
    public String homePost(Model model,
                       @RequestParam(name = "x1", defaultValue = "1") int x1,
                       @RequestParam(name = "x2", defaultValue = "1") int x2,
                       @RequestParam(name="sss")  String name){
        model.addAttribute("results", method(x1,x2,name));
        return ("home");
    }

    private int method(int x1, int x2, String sss){
        int results;
        switch (sss){
            case  "+" -> results = x1 + x2;
            case "-" -> results = x1 - x2;
            case "*" -> results = x1 * x2;
            default -> results = x1 / x2;

        }
        return results;
    }
}
