import java.io.Serializable;
import java.util.Arrays;

public class University implements School, Serializable {
    String universityName;
    String motto;
    private Person[] people;
    private String[] majors;
    private String[] courses;

    public University(String name, String mo) {
        this.universityName = name;
        this.motto = mo;

        this.majors = new String[]{
            "Hardware Architecture",
            "Information Analytics",
            "Quantum Computing",
            "Undecided"
        };

        this.courses = new String[]{
            "Computers",
            "Advance Physics",
            "Quantum Entanglement",
            "Parallel Programming",
            "Advance Algorithms",
            "FPGA Programming",
            "Hardware Design",
            "Embedded Systems",
            "Signal Processing",
            "Artificial Intelligence",
            "Bayesian Logic",
            "Probability"
        };

        this.people = new Person[]{
            new Faculty("Bruce", "Wayne", 9, 27, 1995,
                    new String[]{"Bayesian Logic", "Artificial Intelligence", "Hardware Design"}),
            new Faculty("Diana", "Prince", 11, 5, 2006,
                    new String[]{"Hardware Design", "FPGA Programming", "Embedded Systems"}),
            new Faculty("Barbara", "Gordon", 5, 23, 1980,
                    new String[]{"Probability", "Signal Processing", "Advance Algorithms"}),
            new Faculty("Charles", "Xavier", 11, 5, 1966,
                    new String[]{"Signal Processing", "Embedded Systems", "Parallel Programming"}),

            new Student("Billy", "Baston", 7, 12, 1990, "Information Analytics"),
            new Student("Carol", "Danvers", 4, 9, 1992, "Quantum Computing"),
            new Student("Clark", "Kent", 5, 5, 1994, "Hardware Architecture"),
            new Student("Kara", "Zorel", 4, 13, 1989, "Hardware Architecture"),
            new Student("Peter", "Parker", 6, 25, 1997, "Quantum Computing"),
            new Student("Tony", "Stark", 2, 2, 2004, "Hardware Architecture"),
            new Student("Stephen", "Strange", 12, 15, 1976, "Quantum Computing"),
            new Student("Bruce", "Banner", 9, 9, 2000, "Undecided")
        };
    }
    
    public String getUniversityName() { return universityName; }
    public String getMotto() { return motto; }

    public Student findStudent(String firstName, String lastName) {
        for (Person p : people) {
            if (p instanceof Student &&
                p.firstName.equalsIgnoreCase(firstName) &&
                p.lastName.equalsIgnoreCase(lastName)) {
                return (Student) p;
            }
        }
        return null;
    }

    @Override
    public Faculty findFaculty(String firstName, String lastName) {
        for (Person p : people) {
            if (p instanceof Faculty &&
                p.firstName.equalsIgnoreCase(firstName) &&
                p.lastName.equalsIgnoreCase(lastName)) {
                return (Faculty) p;
            }
        }
        return null;
    }

    @Override
    public Faculty hire(Person p) {
        if (!(p instanceof Faculty)) {
            throw new IllegalArgumentException("Only Faculty can be hired.");
        }
        people = Arrays.copyOf(people, people.length + 1);
        people[people.length - 1] = p;
        return (Faculty) p;
    }

    @Override
    public Student admit(Person p) {
        if (!(p instanceof Student)) {
            throw new IllegalArgumentException("Only Students can be admitted.");
        }
        people = Arrays.copyOf(people, people.length + 1);
        people[people.length - 1] = p;
        return (Student) p;
    }

    @Override
    public Person[] getAllPersons() {
        return people;
    }

    @Override
    public String[] getAllMajors() {
        return majors;
    }

    @Override
    public String[] getAllCourses() {
        return courses;
    }

    private Person[] filterPeople(Class<?> type) {
        int count = 0;
        for (Person p : people) {
            if (type.isInstance(p)) count++;
        }
        Person[] result = new Person[count];
        int index = 0;
        for (Person p : people) {
            if (type.isInstance(p)) {
                result[index++] = p;
            }
        }
        return result;
    }

    @Override
    public Person[] getStudents() {
        return filterPeople(Student.class);
    }

    @Override
    public Person[] getFaculty() {
        return filterPeople(Faculty.class);
    }
}
