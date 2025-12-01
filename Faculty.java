import java.io.Serializable;

public class Faculty extends Person implements Serializable {
    String[] courses;

    Faculty(String fn, String ln, int month, int day, int year, String[] c) {
        super(fn, ln, month, day, year);
        this.courses = c;
    }
    
    
}
