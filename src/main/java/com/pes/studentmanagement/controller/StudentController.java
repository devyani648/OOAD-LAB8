package com.pes.studentmanagement.controller;

import com.pes.studentmanagement.model.Student;
import com.pes.studentmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Home Page: Displays the registration form and the student list
    @GetMapping("/")
    public String viewHomePage(Model model) {
        // Creates an empty student object to bind form data
        model.addAttribute("student", new Student());
        // Fetches all students to display in the table
        model.addAttribute("listStudents", studentService.getAllStudents());
        return "index";
    }

    // Creates a new student record based on user input
    @PostMapping("/api/students")
    public String saveStudent(@ModelAttribute("student") Student student) {
        // Validate incoming requests to ensure required fields are present
        if (student.getName() == null || student.getName().trim().isEmpty() ||
            student.getEmail() == null || student.getEmail().trim().isEmpty() ||
            student.getCourse() == null || student.getCourse().trim().isEmpty()) {
            return "redirect:/?error=missing_fields";
        }

        try {
            studentService.saveStudent(student);
        } catch (IllegalArgumentException e) {
            // Catches the duplicate email error from the Service layer
            return "redirect:/?error=duplicate_email";
        }
        
        // Page must refresh after saving
        return "redirect:/"; 
    }

    // Fetches a list of all student records as JSON
    @GetMapping("/api/students")
    @ResponseBody
    public List<Student> getAllStudentsApi() {
        return studentService.getAllStudents();
    }
}