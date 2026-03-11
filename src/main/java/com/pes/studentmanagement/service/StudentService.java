package com.pes.studentmanagement.service;

import com.pes.studentmanagement.model.Student;
import com.pes.studentmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // Create: Add a new student record
    public void saveStudent(Student student) {
        // Prevent duplicate email registration
        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new IllegalArgumentException("A student with this email already exists.");
        }
        studentRepository.save(student); // Coordinate with repository
    }

    // Read: Retrieve all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Read: Retrieve student by ID
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    // Read: Retrieve students by course
    public List<Student> getStudentsByCourse(String course) {
        return studentRepository.findByCourse(course);
    }
}