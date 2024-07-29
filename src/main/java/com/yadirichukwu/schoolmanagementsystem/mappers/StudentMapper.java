package com.yadirichukwu.schoolmanagementsystem.mappers;

import com.yadirichukwu.schoolmanagementsystem.domain.School;
import com.yadirichukwu.schoolmanagementsystem.domain.Student;
import com.yadirichukwu.schoolmanagementsystem.dto.StudentDto;
import com.yadirichukwu.schoolmanagementsystem.dto.StudentResponseDto;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public StudentResponseDto toStudentResponseDto(Student student) {
        return new StudentResponseDto(student.getFirstname(),student.getLastname(),student.getEmail());
    }

    public Student toStudent(StudentDto dto) {
        if(dto == null) {
            throw new NullPointerException("The student dto is null");
        }
        Student student = new Student();
        student.setName(dto.firstname());
        student.setLastname(dto.lastname());
        student.setEmail(dto.email());

        var school = new School();
        school.setId(dto.schoolId());

        student.setSchool(school);
        return student;
    }
}
