package com.yadirichukwu.schoolmanagementsystem.repository;

import com.yadirichukwu.schoolmanagementsystem.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findAllByFirstnameContaining(String firstname);
}
