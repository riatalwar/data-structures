package starter;

public interface Roster {
//  private Student[] students;
//  private int numStudents;
//
//  public Roster(int size) {
//    students = new Student[size];
//    numStudents = 0;
//  }

  public void add(Student s);

  public void remove(Student s);

  public Student find(String email);
}
