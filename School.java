public interface School {
    Student findStudent(String fn, String ln);
    Faculty findFaculty(String fn, String ln);
    Faculty hire(Person p);
    Student admit(Person p);
    Person[] getAllPersons();
    String[] getAllMajors();
    String[] getAllCourses();
    Person[] getStudents();
    Person[] getFaculty();
}
