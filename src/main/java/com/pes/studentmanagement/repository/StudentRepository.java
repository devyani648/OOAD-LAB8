package com.pes.studentmanagement.repository;

import com.pes.studentmanagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    
    // Custom method to check for duplicate emails
    boolean existsByEmail(String email);
    
    // Custom method to find students by a specific course
    List<Student> findByCourse(String course);
}