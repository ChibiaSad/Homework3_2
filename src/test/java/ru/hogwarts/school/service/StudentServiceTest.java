package ru.hogwarts.school.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;
    @InjectMocks
    private StudentService studentService;

//    @Test
//    void addStudentTest() {
//        assertThat(studentService.getAll())
//                .isEmpty();
//
//        Student expected1 = new Student(1L, "Harry", 11);
//        Student expected2 = new Student(2L, "Ron", 11);
//
//        studentService.addStudent(new Student(0L, "Harry", 11));
//
//        assertThat(studentService.getAll())
//                .isNotEmpty()
//                .hasSize(1)
//                .containsOnly(expected1);
//
//        studentService.addStudent(new Student(0L, "Ron", 11));
//
//        assertThat(studentService.getAll())
//                .isNotEmpty()
//                .hasSize(2)
//                .containsOnly(expected1, expected2);
//    }
//
//    @Test
//    void getStudentPositiveTest() {
//        assertThat(studentService.getAll())
//                .isEmpty();
//
//        Student expected1 = new Student(1L, "Harry", 11);
//        Student expected2 = new Student(2L, "Ron", 11);
//
//        studentService.addStudent(expected1);
//        studentService.addStudent(expected2);
//
//        assertThat(studentService.getStudent(1))
//                .isNotNull()
//                .isEqualTo(expected1);
//
//        assertThat(studentService.getStudent(2))
//                .isNotNull()
//                .isEqualTo(expected2);
//    }
//
//    @Test
//    void getStudentNegativeTest() {
//        Student expected = new Student(-1L, "", 0);
//        assertThat(studentService.getStudent(1))
//                .isEqualTo(expected);
//    }
//
//
//    @Test
//    void setStudentPositiveTest() {
//        assertThat(studentService.getAll())
//                .isEmpty();
//
//        Student expected = new Student(1L, "Harry", 11);
//
//        studentService.addStudent(new Student(0L, "Hary", 1));
//
//        studentService.setStudent(expected);
//
//        assertThat(studentService.getAll())
//                .isNotEmpty()
//                .hasSize(1)
//                .containsOnly(expected);
//    }
//
//    @Test
//    void setStudentNegativeTest() {
//        assertThat(studentService.setStudent(new Student(1L, "", 123)))
//                .isEqualTo(null);
//    }
//
//    @Test
//    void deleteStudentTest() {
//        assertThat(studentService.getAll())
//                .isEmpty();
//
//        Student student1 = new Student(1L, "Harry", 11);
//        Student student2 = new Student(2L, "Ron", 11);
//
//        studentService.addStudent(student1);
//        studentService.addStudent(student2);
//
//        assertThat(studentService.getAll())
//                .isNotEmpty()
//                .hasSize(2)
//                .containsOnly(student1, student2);
//
//        studentService.deleteStudent(1);
//
//        assertThat(studentService.getAll())
//                .isNotEmpty()
//                .hasSize(1)
//                .containsOnly(student2);
//
//        studentService.deleteStudent(2);
//
//        assertThat(studentService.getAll())
//                .isEmpty();
//    }
//
//    @Test
//    void getAllByAgeTest() {
//        assertThat(studentService.getAll())
//                .isEmpty();
//
//        Student student1 = new Student(1L, "Harry", 11);
//        Student student2 = new Student(2L, "Ron", 11);
//        Student student3 = new Student(3L, "Hermione", 12);
//
//        studentService.addStudent(student1);
//        studentService.addStudent(student2);
//        studentService.addStudent(student3);
//
//        assertThat(studentService.getAllByAge(11))
//                .isNotEmpty()
//                .hasSize(2)
//                .containsOnly(student1, student2);
//
//        assertThat(studentService.getAllByAge(12))
//                .isNotEmpty()
//                .hasSize(1)
//                .containsOnly(student3);
//
//        assertThat(studentService.getAllByAge(13))
//                .isEmpty();
//    }
}