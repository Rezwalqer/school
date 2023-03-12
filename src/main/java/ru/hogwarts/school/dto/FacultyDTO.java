package ru.hogwarts.school.dto;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hogwarts.school.model.Faculty;
@Data
@NoArgsConstructor
public class FacultyDTO {
    private Long id;
    private String name;
    private String color;

    public Faculty toFaculty() {
        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);
        return faculty;
    }

    public static FacultyDTO fromFaculty(Faculty faculty) {
        FacultyDTO facultyDTO = new FacultyDTO();
        facultyDTO.setId(faculty.getId());
        facultyDTO.setName(faculty.getName());
        facultyDTO.setColor(faculty.getColor());
        return facultyDTO;
    }

}