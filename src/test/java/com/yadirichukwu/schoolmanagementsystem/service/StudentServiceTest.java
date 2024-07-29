package com.yadirichukwu.schoolmanagementsystem.service;

import com.yadirichukwu.schoolmanagementsystem.domain.Student;
import com.yadirichukwu.schoolmanagementsystem.dto.StudentDto;
import com.yadirichukwu.schoolmanagementsystem.dto.StudentResponseDto;
import com.yadirichukwu.schoolmanagementsystem.mappers.StudentMapper;
import com.yadirichukwu.schoolmanagementsystem.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class StudentServiceTest {

    //which service we want to test
    @InjectMocks
    private StudentService studentService;

    //declare the dependencies
    @Mock
    private StudentMapper studentMapper;
    @Mock
    private StudentRepository studentRepository;
    @BeforeEach
    void setUp() {
        openMocks(this);
    }
    @Test
    public void should_successfully_save_student() {
//         Given
        StudentDto dto = new StudentDto(
                "Joseph",
                "Luke",
                "ebiluke@gmail.com",
                1
        );

        Student student = new Student(
                "Joseph","Luke","ebiluke@gmail.com","28"
        );
        Student savedStudent = new Student(
                "Joseph","Luke","ebiluke@gmail.com","28"
        );
        savedStudent.setId(1);
//        mock the calls to the dependencies
        when(studentMapper.toStudent(dto)).thenReturn(student);
        when(studentRepository.save(student)).thenReturn(savedStudent);
        when(studentMapper.toStudentResponseDto(savedStudent))
                .thenReturn(new StudentResponseDto("Joseph","Luke", "ebiluke@gmail.com"));
//         When
        StudentResponseDto responseDto = studentService.createStudent(dto);

//        Then
        assertEquals(dto.firstname(), responseDto.firstname());
        assertEquals(dto.lastname(), responseDto.lastname());
        assertEquals(dto.email(), responseDto.email());
//        verify the performance of the test
        verify(studentMapper, times(1)).toStudent(dto);
        verify(studentRepository, times(1)).save(student);
        verify(studentMapper, times(1)).toStudentResponseDto(savedStudent);
    }

    @Test
    public void should_return_all_saved_student() {
//        Given
        List<Student> savedStudents = new ArrayList<>();
        savedStudents.add(new Student(
                "John","Doe", "john@gmail.com","25"
        ));
//        mock the calls
        when(studentRepository.findAll()).thenReturn(savedStudents);
        when(studentMapper.toStudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto("John","Doe", "john@gmail.com"));

//
        List<StudentResponseDto> responseDtos = studentService.getAllStudents();

//
        assertEquals(savedStudents.size(), responseDtos.size());
    }

    @Test
    public void should_return_student_by_id() {
//        Given
        var studentId = 1;
        Student student = new Student(
                "John","Doe", "john@gmail.com","25"
        );
//      mock the calls
        when(studentRepository.findById(studentId))
                .thenReturn(Optional.of(student));
        when(studentMapper.toStudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto("John","Doe", "john@gmail.com"));
//        When
        StudentResponseDto responseDto = studentService.getStudentById(studentId);

//          Then
        assertEquals(student.getFirstname(), responseDto.firstname());
        assertEquals(student.getLastname(), responseDto.lastname());
        assertEquals(student.getEmail(), responseDto.email());
        verify(studentRepository, times(1)).findById(studentId);
    }

    @Test
    public void should_find_student_by_name() {
//      Given
        String studentName = "John";
        List<Student> students = new ArrayList<>();
        students.add(new Student("John", "Doe", "john@gmail.com","25"));
//        Mock the calls
        when(studentRepository.findAllByFirstnameContaining(studentName)).thenReturn(students);
        when(studentMapper.toStudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto("John","Doe", "john@gmail.com"));

        //    When
        var responseDto = studentService.getStudentsByName(studentName);

        assertEquals(students.size(), responseDto.size());

        verify(studentRepository, times(1))
                .findAllByFirstnameContaining(studentName);
    }



}