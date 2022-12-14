package ru.hogwarts.school.record;

public class StudentRecord {
    private long id;
    private String name;
    private int age;
    private FacultyRecord facultyRecord;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public FacultyRecord getFacultyRecord() {
        return facultyRecord;
    }

    public void setFacultyRecord(FacultyRecord facultyRecord) {
        this.facultyRecord = facultyRecord;
    }
}
