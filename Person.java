import java.io.Serializable;

public class Person implements Serializable {
    String firstName, lastName;
    int monthBirth, dayBirth, yearBirth;

    Person(String fn, String ln, int month, int day, int year) {
        this.firstName = fn;
        this.lastName = ln;
        this.monthBirth = month;
        this.dayBirth = day;
        this.yearBirth = year;
    }
}
