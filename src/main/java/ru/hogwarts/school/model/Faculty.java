package ru.hogwarts.school.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "faculty_id")
    private Long id;
    @OneToMany(mappedBy = "faculty")
    private List<Student> students;
    @Column(name = "faculty_name")
    private String name;
    @Column(name = "faculty_color")
    private String color;

}
