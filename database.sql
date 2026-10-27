
-- Create the table for student records
CREATE TABLE students (
    id NUMBER PRIMARY KEY,
    name VARCHAR2(30),
    marks NUMBER
);

-- Display all student records
SELECT * FROM students;

-- Count the records
SELECT COUNT(*) AS total_students FROM students;

