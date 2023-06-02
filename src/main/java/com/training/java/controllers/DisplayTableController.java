package com.training.java.controllers;

import com.training.java.entities.Student;
import com.training.java.entities.enums.StatesOfIndia;
import com.training.java.entities.enums.StreamOfStudents;
import com.training.java.services.DisplayTableService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DisplayTableController {

    @Autowired
    private DisplayTableService displayTableService;

    @GetMapping("/students")
    public String showAllStudents(Model model) {
    	
        List<Student> students = displayTableService.getAllStudents();
        
        Map<Student, String> formattedDates = displayTableService.formatDates(students);

        model.addAttribute("formattedDates", formattedDates);
        model.addAttribute("students", students);

        return "display";
    }

    @GetMapping("/students/edit/{id}")
    public String editStudent(@PathVariable int id, Model model) {
    	
        Student student = displayTableService.getStudentById(id);

        if (student != null) {
            model.addAttribute("student", student);
            model.addAttribute("streams", StreamOfStudents.values()); 
            model.addAttribute("states", StatesOfIndia.values()); 
            return "edit-student";
        } else {
            return "redirect:/students";
        }
    }

    @PostMapping("/students/edit/{id}")
    public String updateStudent(@PathVariable int id, @ModelAttribute Student updatedStudent) {
        displayTableService.updateStudent(id, updatedStudent);
        return "redirect:/students";
    }

    @PostMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable int id) {
        displayTableService.deleteStudent(id);
        return "redirect:/students";
    }

}
