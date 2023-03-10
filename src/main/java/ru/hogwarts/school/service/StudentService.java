package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.dto.FacultyDTO;
import ru.hogwarts.school.dto.StudentDTO;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private final FacultyRepository facultyRepository;

    public StudentService(StudentRepository studentRepository, FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
    }


    public StudentDTO createStudent(StudentDTO studentDTO) {
        Faculty faculty = facultyRepository.findById(studentDTO.getFacultyId()).get();
        Student student = studentDTO.toStudent();
        student.setFaculty(faculty);
        Student newStudent = studentRepository.save(student);
        return StudentDTO.fromStudent(newStudent);
    }

    public StudentDTO findStudent(Long id) {
        return StudentDTO.fromStudent(studentRepository.findById(id).get());
    }

    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {
        Faculty faculty = facultyRepository.findById(studentDTO.getFacultyId()).get();
        Student student = studentDTO.toStudent();
        student.setFaculty(faculty);
        Student newStudent = studentRepository.save(student);
        return StudentDTO.fromStudent(newStudent);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(StudentDTO::fromStudent)
                .collect(Collectors.toList());
    }

    public List<StudentDTO> findStudentsByAge(int age) {
        return studentRepository.findStudentsByAge(age).stream()
                .map(StudentDTO::fromStudent)
                .collect(Collectors.toList());
    }

    public List<StudentDTO> findStudentsByAgeBetween(int fromAge, int toAge) {
        return studentRepository.findStudentsByAgeBetween(fromAge, toAge).stream()
                .map(StudentDTO::fromStudent)
                .collect(Collectors.toList());
    }

    public FacultyDTO getFacultyByStudentId(Long id) {
        Faculty faculty = facultyRepository.findById(findStudent(id).getFacultyId()).get();
        return FacultyDTO.fromFaculty(faculty);
    }
}
