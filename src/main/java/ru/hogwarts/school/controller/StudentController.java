package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.dto.FacultyDTO;
import ru.hogwarts.school.dto.StudentDTO;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public ResponseEntity<StudentDTO> getStudentInfo(@PathVariable Long id) {
        StudentDTO studentDTO = studentService.findStudent(id);
        if (studentDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentDTO);
    }

    @GetMapping
    public ResponseEntity<Collection<StudentDTO>> getAllStudents(
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) Integer maxAge) {
        if (minAge != null && minAge > 0 && maxAge != null && maxAge > 0) {
            return ResponseEntity.ok(studentService.findStudentsByAgeBetween(minAge, maxAge));
        }
        if (age != null && age > 0) {
            return ResponseEntity.ok(studentService.findStudentsByAge(age));
        }
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) {
        StudentDTO createdStudentDTO = studentService.createStudent(studentDTO);
        if (studentDTO.getFacultyId() == null) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudentDTO);
    }

    @PutMapping("{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id, @RequestBody StudentDTO studentDTO) {
        StudentDTO updatedStudentDTO = studentService.updateStudent(id, studentDTO);
        if (updatedStudentDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedStudentDTO);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{studentId}/faculty")
    public ResponseEntity<FacultyDTO> findFacultyByStudentId(@PathVariable Long studentId) {
        FacultyDTO facultyDTO = studentService.getFacultyByStudentId(studentId);
        if (facultyDTO == null) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(facultyDTO);
    }
}
