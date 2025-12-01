import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class UniversityDriver {
    private static Scanner input = new Scanner(System.in);
    private static University uni;

    public static void main(String[] args) {
    
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("UniversityPersons.per"))) {
            uni = (University) in.readObject();
            System.out.println("Loaded saved university data.");
        } catch (Exception e) {
            uni = new University("HERO UNIVERSITY", "ex tenebris ad lucem alis novis volabimus");
            System.out.println("No saved data found. Loaded initial university data.");
        }

        while (true) {
            printMenu();
            int option = input.nextInt();
            input.nextLine();

            switch (option) {
                case 1: hire(); break;
                case 2: admit(); break;
                case 3: findStudent(); break;
                case 4: findFaculty(); break;
                case 5: listStudents(); break;
                case 6: listFaculty(); break;
                case 7: instructorsPerCourse(); break;
                case 8: studentsPerMajor(); break;
                case 9: quit(); return;
                default: System.out.println("Invalid option"); break;
            }
        }
    }

    private static void printMenu() {
        System.out.println("\nWelcome To " + uni.universityName);
        System.out.println(uni.motto);
        System.out.println("What would you like to do?");
        System.out.println("1: Hire Faculty");
        System.out.println("2: Admit Student");
        System.out.println("3: Find Student");
        System.out.println("4: Find Faculty");
        System.out.println("5: List Students");
        System.out.println("6: List Faculty");
        System.out.println("7: Instructors per Course");
        System.out.println("8: Students per Major");
        System.out.println("9: Quit");
        System.out.print("> ");
    }

    public static void hire() {
        String[] allCourses = uni.getAllCourses();
        String[] assignedCourses = new String[allCourses.length]; // max possible size
        int count = 0;

        System.out.println("What is the person's first name:");
        String first = input.nextLine();
        System.out.println("What is the person's last name:");
        String last = input.nextLine();

        System.out.println("What is the person's month of birth (1-12):");
        int month = input.nextInt();
        System.out.println("What is the person's day of birth (1-31):");
        int day = input.nextInt();
        System.out.println("What is the person's year of birth (4 digits):");
        int year = input.nextInt();
        input.nextLine();

        System.out.println("Assign courses to this Faculty (enter one at a time, and type 'done' when there are no other courses):");
        System.out.println("The majors offered are:");
        for (String c : allCourses) {
            System.out.println(c);
        }

        while (true) {
            System.out.print("> ");
            String course = input.nextLine();

            if (course.equalsIgnoreCase("done")) {
                break;
            }

            boolean valid = false;
            for (String offered : allCourses) {
                if (offered.equalsIgnoreCase(course)) {
                    valid = true;
                    break;
                }
            }

            if (valid) {
                assignedCourses[count] = course;
                count++;
            } else {
                System.out.println("That is not a course offered.");
            }
        }

        String[] courses = new String[count];
        for (int i = 0; i < count; i++) {
            courses[i] = assignedCourses[i];
        }

        Faculty f = new Faculty(first, last, month, day, year, courses);
        uni.hire(f);
        saveData();
        System.out.println("Faculty hired successfully!");
    }


    public static void admit() {
    	String[] courses = uni.getAllCourses();
    	String major = null;
    	
    	boolean flag = true;
    	
    	while(flag) {
    		 System.out.println("What is the person's major: ");
    		 

 	        System.out.println("The majors offered are: ");
    		 for (int i=0; i<courses.length; i++) {
 	        	System.out.println(courses[i]);
    		 }	
    		 major = input.nextLine();
    	        
    	        for (int i=0; i<courses.length; i++) {
    	        	if (!courses[i].equalsIgnoreCase(major)) {
    	        		flag = true;
    	        	} else {
    	        		flag = false;
    	        		break;
    	        	}
    	        }
    	        if (flag) {
    	        	System.out.println("This is not a major offered.");
    	        }
    	        
    	}
       
         
    	
        System.out.println("What is the person's first name:");
        String first = input.nextLine();
        System.out.println("What is the person's last name:");
        String last = input.nextLine();

        System.out.println("What is the person's month of birth (1-12):");
        int month = input.nextInt();
        System.out.println("What is the person's day of birth (1-31):");
        int day = input.nextInt();
        System.out.println("What is the person's year of birth (4 digits):");
        int year = input.nextInt();
        input.nextLine();

       

        Student s = new Student(first, last, month, day, year, major);
        uni.admit(s);
        saveData();
        System.out.println("Student admitted successfully!");
    }

    public static void findStudent() {
        System.out.println("Enter student's first name:");
        String first = input.nextLine();
        System.out.println("Enter student's last name:");
        String last = input.nextLine();

        Student s = uni.findStudent(first, last);
        if (s != null) {
            System.out.println("Student: " + s.firstName + " " + s.lastName);
            System.out.println("DOB: " + s.monthBirth + "/" + s.dayBirth + "/" + s.yearBirth);
            System.out.println("Major: " + s.major);
        } else {
            System.out.println("Student not found.");
        }
    }

    public static void findFaculty() {
        System.out.println("Enter faculty's first name:");
        String first = input.nextLine();
        System.out.println("Enter faculty's last name:");
        String last = input.nextLine();

        Faculty f = uni.findFaculty(first, last);
        if (f != null) {
            System.out.println("Faculty: " + f.firstName + " " + f.lastName);
            System.out.println("DOB: " + f.monthBirth + "/" + f.dayBirth + "/" + f.yearBirth);
            System.out.println("Courses: ");
            for (int i=0 ; i<f.courses.length; i++) {
            	System.out.println(f.courses[i]);
            }
        } else {
            System.out.println("Faculty not found.");
        }
    }

    public static void listStudents() {
        Person[] students = uni.getStudents();
        for (Person p : students) {
            System.out.println(p.firstName + " " + p.lastName);
        }
    }

    public static void listFaculty() {
        Person[] faculty = uni.getFaculty();
        for (Person p : faculty) {
            System.out.println(p.firstName + " " + p.lastName);
        }
    }

    public static void instructorsPerCourse() {
        String[] courses = uni.getAllCourses();
    	
    	for (int i=0 ; i<courses.length; i++) {
    		int count = 0;
          for (Person p : uni.getFaculty()) {
              Faculty f = (Faculty) p;
              for (String c : f.courses) {
                  if (c.trim().equalsIgnoreCase(courses[i])) {
                      count++;
                  }
             }
          }
    		System.out.println(courses[i] + "=" + count);
    	}

    }

    public static void studentsPerMajor() {
        Person[] students = uni.getStudents();
        if (students == null || students.length == 0) {
            System.out.println("No students admitted yet.");
            return;
        }

        String[] allMajors = new String[students.length];
        for (int i = 0; i < students.length; i++) {
            if (students[i] instanceof Student) {
                Student s = (Student) students[i];
                allMajors[i] = s.major != null ? s.major.trim() : "Undecided";
            }
        }

        boolean[] counted = new boolean[allMajors.length];
        
        System.out.println("Students per Major:");

        for (int i = 0; i < allMajors.length; i++) {
            if (counted[i] || allMajors[i] == null) {
                continue;
            }

            String currentMajor = allMajors[i];
            int count = 1; 
            counted[i] = true; 

            for (int j = i + 1; j < allMajors.length; j++) {
                if (!counted[j] && allMajors[j] != null && currentMajor.equalsIgnoreCase(allMajors[j])) {
                    count++;
                    counted[j] = true; 
                }
            }

            System.out.println(currentMajor + " =" + count);
        }
        
    }

    public static void quit() {
        saveData();
        System.out.println("Goodbye!");
    }

    private static void saveData() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("UniversityPersons.per"))) {
            out.writeObject(uni);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
}
