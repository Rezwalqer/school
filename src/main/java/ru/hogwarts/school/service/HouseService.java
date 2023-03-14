package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.dto.FacultyDTO;
import ru.hogwarts.school.dto.StudentDTO;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HouseService {
    private final FacultyRepository facultyRepository;

    public HouseService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }
    public FacultyDTO createFaculty(FacultyDTO facultyDTO) {
        Faculty faculty = facultyDTO.toFaculty();
        Faculty newFaculty = facultyRepository.save(faculty);
        return FacultyDTO.fromFaculty(newFaculty);
    }


    public FacultyDTO findFacultyById(Long id) {
        return FacultyDTO.fromFaculty(facultyRepository.findById(id).get());
    }


    public FacultyDTO updateFaculty(FacultyDTO facultyDTO) {
        Faculty faculty = facultyDTO.toFaculty();
        Faculty newFaculty = facultyRepository.save(faculty);
        return FacultyDTO.fromFaculty(newFaculty);
    }

    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }

    public List<FacultyDTO> getAllFaculties() {
        return facultyRepository.findAll().stream()
                .map(FacultyDTO::fromFaculty)
                .collect(Collectors.toList());
    }

    public Collection<FacultyDTO> findFacultiesByColorIgnoreCase(String color) {
        return facultyRepository.findFacultiesByColorIgnoreCase(color).stream()
                .map(FacultyDTO::fromFaculty)
                .collect(Collectors.toList());
    }

    public Collection<FacultyDTO> findFacultyByNameIgnoreCase(String name) {
        return facultyRepository.findFacultyByNameIgnoreCase(name).stream()
                .map(FacultyDTO::fromFaculty)
                .collect(Collectors.toList());
    }

    public Collection<StudentDTO> findStudentsByFacultyId(Long id) {
        List<Student>students = facultyRepository.findById(id).get().getStudents();
        List<StudentDTO> studentsDTO = new ArrayList<>();
        for (Student student : students) {
            StudentDTO studentDTO = StudentDTO.fromStudent(student);
            studentsDTO.add(studentDTO);
        }
        return studentsDTO;
    }
}
