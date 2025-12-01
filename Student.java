import java.io.Serializable;

public class Student extends Person implements Serializable {
    String major;

    Student(String fn, String ln, int month, int day, int year, String m) {
        super(fn, ln, month, day, year);
        this.major = m;
    }
    
   public static void main(String[] args) {
  
   }
}
