package com.training.java.controllers;

import com.training.java.entities.Student;
import com.training.java.services.RegistrationService;

import com.training.java.entities.enums.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/register")
    public String showRegistrationsForm(Model model){
        model.addAttribute("student", new Student());
        model.addAttribute("states", StatesOfIndia.values());
        model.addAttribute("streams", StreamOfStudents.values());
        return "registration";
    }

    @PostMapping("/register")
    public String registerStudent(Student student){
        registrationService.registerStudent(student);
        return "redirect:/register?success";
    }
}
