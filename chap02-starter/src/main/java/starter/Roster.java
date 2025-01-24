package starter;

public class Roster {
  private final Student[] students;
  private final int numStudents;

  public Roster(int size) {
    students = new Student[size];
    numStudents = 0;
  }

  public void add(Student s) {
    // stub
  }

  public void remove(Student s) {
    // stub
  }

  // Assumption: students' emails are unique
  public Student find(String email) {
    return null; // stub
  }
}
