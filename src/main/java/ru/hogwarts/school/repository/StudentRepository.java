package ru.hogwarts.school.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Student;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findStudentsByAge(int age, PageRequest pageRequest);

    List<Student> findStudentsByAgeBetween(int fromAge, int toAge, PageRequest pageRequest);

    @Query(value = "SELECT COUNT(s) FROM Student s", nativeQuery = true)
    Long getCountOfStudents();
    @Query(value = "SELECT AVG(s.age) FROM Student s", nativeQuery = true)
    Double getAverageAge();

    @Query(value = "SELECT * FROM Student ORDER BY student_age LIMIT 5", nativeQuery = true)
    List<Student> findYoungestStudents();
}
