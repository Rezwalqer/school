package ru.hogwarts.school.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hogwarts.school.model.Student;
@Data
@NoArgsConstructor
public class StudentDTO {
    private Long id;
    private String name;
    private int age;
    private Long facultyId;

    public Student toStudent() {
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);
        return student;
    }

    public static StudentDTO fromStudent(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setAge(student.getAge());
        studentDTO.setId(student.getId());
        studentDTO.setName(student.getName());
        studentDTO.setFacultyId(student.getFaculty().getId());
        return studentDTO;
    }

}