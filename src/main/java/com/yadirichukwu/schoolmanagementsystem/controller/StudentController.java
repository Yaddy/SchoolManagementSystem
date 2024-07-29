package com.yadirichukwu.schoolmanagementsystem.controller;



import com.yadirichukwu.schoolmanagementsystem.dto.StudentDto;
import com.yadirichukwu.schoolmanagementsystem.dto.StudentResponseDto;
import com.yadirichukwu.schoolmanagementsystem.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService service) {
        this.studentService = service;
    }

    @PostMapping("/students")
    public StudentResponseDto post(@Valid @RequestBody StudentDto dto) {
       return studentService.createStudent(dto);
    }



    @GetMapping("/students")
    public List<StudentResponseDto> getAllStudents() {
        return studentService.getAllStudents();
    }


    @GetMapping("/students/{student-id}")
    public StudentResponseDto getStudentById(@PathVariable("student-id") Integer studentId) {
        return studentService.getStudentById(studentId);
    }

    @GetMapping("/students/search/{student-name}")
    public List<StudentResponseDto> getStudentsByName(@PathVariable("student-name") String name) {
        return studentService.getStudentsByName(name);
    }

    @DeleteMapping("/students/delete/{student-id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteStudent(@PathVariable("student-id") Integer studentId) {
        studentService.deleteStudent(studentId);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        var errors = new HashMap<String, String>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            var fieldName = ((FieldError) error).getField();
            var errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
