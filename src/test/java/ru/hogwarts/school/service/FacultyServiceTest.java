package ru.hogwarts.school.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class FacultyServiceTest {
    @Mock
    private FacultyRepository facultyRepository;
    @InjectMocks
    private FacultyService facultyService;

//    @BeforeEach
//    void setUp(){
//        facultyService = new FacultyService();
//    }
//
//    @Test
//    void addFacultyTest() {
//        assertThat(facultyService.getAll())
//                .isEmpty();
//
//        Faculty expected1 = new Faculty(1L, "Griff", "red");
//        Faculty expected2 = new Faculty(2L, "Sliz", "green");
//
//        facultyService.addFaculty(new Faculty(0L, "Griff", "red"));
//
//        assertThat(facultyService.getAll())
//                .isNotEmpty()
//                .hasSize(1)
//                .containsOnly(expected1);
//
//        facultyService.addFaculty(new Faculty(0L, "Sliz", "green"));
//
//        assertThat(facultyService.getAll())
//                .isNotEmpty()
//                .hasSize(2)
//                .containsOnly(expected1, expected2);
//    }
//
//    @Test
//    void getFacultyPositiveTest() {
//        assertThat(facultyService.getAll())
//                .isEmpty();
//
//        Faculty expected1 = new Faculty(1L, "Griff", "red");
//        Faculty expected2 = new Faculty(2L, "Sliz", "green");
//
//        facultyService.addFaculty(expected1);
//        facultyService.addFaculty(expected2);
//
//        assertThat(facultyService.getFaculty(1))
//                .isNotNull()
//                .isEqualTo(expected1);
//
//        assertThat(facultyService.getFaculty(2))
//                .isNotNull()
//                .isEqualTo(expected2);
//    }
//
//    @Test
//    void getFacultyNegativeTest() {
//        assertThat(facultyService.getFaculty(1))
//                .isEqualTo(null);
//    }
//
//
//    @Test
//    void setFacultyPositiveTest() {
//        assertThat(facultyService.getAll())
//                .isEmpty();
//
//        Faculty expected = new Faculty(1L, "Griff", "red");
//
//        facultyService.addFaculty(new Faculty(0L, "Grif", "1"));
//
//        facultyService.setFaculty(expected);
//
//        assertThat(facultyService.getAll())
//                .isNotEmpty()
//                .hasSize(1)
//                .containsOnly(expected);
//    }
//
//    @Test
//    void setFacultyNegativeTest() {
//        assertThat(facultyService.setFaculty(new Faculty(1L, "", "123")))
//                .isEqualTo(null);
//    }
//
//    @Test
//    void deleteFacultyTest() {
//        assertThat(facultyService.getAll())
//                .isEmpty();
//
//        Faculty faculty1 = new Faculty(1L, "Griff", "red");
//        Faculty faculty2 = new Faculty(2L, "Sliz", "green");
//
//        facultyService.addFaculty(faculty1);
//        facultyService.addFaculty(faculty2);
//
//        assertThat(facultyService.getAll())
//                .isNotEmpty()
//                .hasSize(2)
//                .containsOnly(faculty1, faculty2);
//
//        facultyService.deleteFaculty(1);
//
//        assertThat(facultyService.getAll())
//                .isNotEmpty()
//                .hasSize(1)
//                .containsOnly(faculty2);
//
//        facultyService.deleteFaculty(2);
//
//        assertThat(facultyService.getAll())
//                .isEmpty();
//    }
//
//    @Test
//    void getAllByAgeTest() {
//        assertThat(facultyService.getAll())
//                .isEmpty();
//
//        Faculty faculty1 = new Faculty(1L, "Griff", "red");
//        Faculty faculty2 = new Faculty(2L, "Sliz", "green");
//        Faculty faculty3 = new Faculty(3L, "Puff", "blue");
//
//        facultyService.addFaculty(faculty1);
//        facultyService.addFaculty(faculty2);
//        facultyService.addFaculty(faculty3);
//
//        assertThat(facultyService.getAllByAge("red"))
//                .isNotEmpty()
//                .hasSize(1)
//                .containsOnly(faculty1);
//
//        assertThat(facultyService.getAllByAge("blue"))
//                .isNotEmpty()
//                .hasSize(1)
//                .containsOnly(faculty3);
//
//        assertThat(facultyService.getAllByAge("green"))
//                .isNotEmpty()
//                .hasSize(1)
//                .containsOnly(faculty2);
//
//        assertThat(facultyService.getAllByAge("pink"))
//                .isEmpty();
//    }
}