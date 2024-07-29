package com.yadirichukwu.schoolmanagementsystem.mappers;

import com.yadirichukwu.schoolmanagementsystem.domain.Student;
import com.yadirichukwu.schoolmanagementsystem.dto.StudentDto;
import com.yadirichukwu.schoolmanagementsystem.dto.StudentResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {

    private static final Logger log = LoggerFactory.getLogger(StudentMapperTest.class);
    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        studentMapper = new StudentMapper();
    }

    @Test
    public void shouldMapStudentDtoToStudent() {

        StudentDto studentDto = new StudentDto(
                "Raymond","McAllister",
                "ray@gmail.com",1);
        Student student = studentMapper.toStudent(studentDto);
        assertEquals(studentDto.firstname(),student.getFirstname());
        assertEquals(studentDto.lastname(),student.getLastname());
        assertEquals(studentDto.email(),student.getEmail());
        assertNotNull(student.getSchool());
        assertEquals(studentDto.schoolId(),student.getSchool().getId());

    }
    @Test
    public void should_throw_nullpointer_exception_when_studentDto_is_null() {
        var ex = assertThrows(NullPointerException.class, () -> studentMapper.toStudent(null));
        assertEquals("The student dto is null", ex.getMessage());

    }


@Test
    public void shouldMapStudentToStudentResponseDto() {
        Student student = new Student("Fara","Tread","yuno@mail.com","28");
        StudentResponseDto response = studentMapper.toStudentResponseDto(student);
        assertEquals(student.getFirstname(),response.firstname());
        assertEquals(student.getLastname(), response.lastname());
        assertEquals(student.getEmail(), response.email());

}

}