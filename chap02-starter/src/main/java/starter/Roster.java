package starter;

public abstract class Roster {

  // if interface --> no fields, all methods implicitly abstract public, no implementations
  // use implements instead of extend for subclasses
  // interfaces can extend each other

  protected Student[] students; //can also put GradStudents in this array since they are Students
  protected int numStudents;

  // redundant
  // private GradStudent[] gStudents;
  // private int numGradStudents;

  // can use abstract constructor w super()
  public Roster(int size) {
    students = new Student[size];
    numStudents = 0;
    //gStudents = new GradStudent[size];
    //numStudents = numGradStudents = 0;
  }

  abstract public void add(Student s);

  abstract public void remove(Student s);

  // Assumption: students' emails are unique
  public Student find(String email) {
    return null; // stub
  }
}
