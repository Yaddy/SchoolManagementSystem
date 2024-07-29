package com.yadirichukwu.schoolmanagementsystem.service;

import com.yadirichukwu.schoolmanagementsystem.dto.StudentDto;
import com.yadirichukwu.schoolmanagementsystem.dto.StudentResponseDto;
import com.yadirichukwu.schoolmanagementsystem.mappers.StudentMapper;
import com.yadirichukwu.schoolmanagementsystem.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper mapper;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper mapper, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.mapper = mapper;
        this.studentMapper = studentMapper;
    }

    public StudentResponseDto createStudent(StudentDto dto) {
        var student = mapper.toStudent(dto);
        var savedStudent = studentRepository.save(student);
        return mapper.toStudentResponseDto(savedStudent);
    }

    public List<StudentResponseDto> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(mapper::toStudentResponseDto)
                .collect(Collectors.toList());
    }

    public StudentResponseDto getStudentById(Integer studentId) {
        return studentRepository.findById(studentId)
                .map(studentMapper::toStudentResponseDto)
                .orElse(null);
    }

    public List<StudentResponseDto> getStudentsByName(String name) {
        return studentRepository.findAllByFirstnameContaining(name)
                .stream()
                .map(mapper::toStudentResponseDto)
                .collect(Collectors.toList());
    }

    public void deleteStudent(Integer studentId) {
        studentRepository.deleteById(studentId);
    }

}
