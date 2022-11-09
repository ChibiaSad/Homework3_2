package ru.hogwarts.school.record;

public class StudentRecord {
    private long id;
    private String name;
    private int age;
    private long faculty;

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

    public long getFaculty() {
        return faculty;
    }

    public void setFaculty(long faculty) {
        this.faculty = faculty;
    }
}
