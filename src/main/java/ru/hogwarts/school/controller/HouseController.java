package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.dto.FacultyDTO;
import ru.hogwarts.school.dto.StudentDTO;
import ru.hogwarts.school.service.HouseService;

import java.util.Collection;

@RestController
@RequestMapping("/faculty")
public class HouseController {
    private final HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @GetMapping("{id}")
    public ResponseEntity<FacultyDTO> getFacultyInfo(@PathVariable Long id) {
        FacultyDTO facultyDTO = houseService.findFacultyById(id);
        if (facultyDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facultyDTO);
    }

    @GetMapping
    public ResponseEntity<Collection<FacultyDTO>> getAllFaculties(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String color) {
        if (name != null && !name.isBlank()) {
            return ResponseEntity.ok(houseService.findFacultyByNameIgnoreCase(name));
        }
        if (color != null && !color.isBlank()) {
            return ResponseEntity.ok(houseService.findFacultiesByColorIgnoreCase(color));
        }
        Collection<FacultyDTO> faculties = houseService.getAllFaculties();
        return ResponseEntity.ok(faculties);
    }

    @PostMapping
    public ResponseEntity<FacultyDTO> createFaculty(@RequestBody FacultyDTO facultyDTO) {
        FacultyDTO savedFacultyDTO = houseService.createFaculty(facultyDTO);
        return ResponseEntity.ok(savedFacultyDTO);
    }
    @PutMapping("{id}")
    public ResponseEntity<FacultyDTO> editFaculty(@RequestBody FacultyDTO facultyDTO) {
        FacultyDTO updatedFacultyDTO = houseService.updateFaculty(facultyDTO);
        if (updatedFacultyDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedFacultyDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<FacultyDTO> deleteFaculty(@PathVariable Long id) {
        houseService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{facultyId}/students")
    public ResponseEntity<Collection<StudentDTO>> findStudentsByFacultyId(@PathVariable Long facultyId) {
        return ResponseEntity.ok(houseService.findStudentsByFacultyId(facultyId));
    }
}
