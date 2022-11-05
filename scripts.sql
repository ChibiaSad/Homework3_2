select * from student where age > 10 and age < 20;

select name from student;

select * from student where name like '%o%';

select * from student where age < id;

select * from student order by age;



SELECT f.* FROM student as s, faculty as f WHERE s.faculty_id = f.id and s.id = 6;

SELECT s.* FROM student as s, faculty as f WHERE s.faculty_id = f.id and f.id = 1;