# UniversityManagementSystem

## Overview
**UniversityManagementSystem** is a Java program that allows users to manage university information, including students and faculty. The program supports adding, querying, listing, and storing data about university personnel. All data is persisted to a binary file, allowing the program to retain information between runs.

This project demonstrates object-oriented programming concepts such as:
- Inheritance and polymorphism
- Interfaces
- File input/output
- Encapsulation and modular design

---

## Features
The program allows users to:

- Hire new faculty members and assign them courses
- Admit new students and assign them majors
- Search for specific students or faculty by name
- List all students or faculty
- Display the number of students per major
- Display the number of instructors per course
- Save all data to a binary file (`UniversityPersons.per`) on program exit

---

## Classes
The project contains the following main classes:

1. **UniversityDriver** – Contains the `main` method and handles user interaction.
2. **University** – Stores university information, including all students and faculty. Implements the `School` interface.
3. **Person** – Base class for all people in the university.
4. **Student** – Extends `Person`; contains major information.
5. **Faculty** – Extends `Person`; contains the courses the faculty teaches.
6. **School (Interface)** – Provides methods for adding, finding, and listing students and faculty.

---

## How to Run
1. Clone the repository:
```bash
git clone https://github.com/ShaheerAli01/UniversityManagementSystem.git
