package starter;

public class MoocRoster extends Roster {

  public MoocRoster(int size) {
    super(size);
  }

  @Override
  public void add(Student s) {
    students[numStudents++] = s;
  }

  @Override
  public void remove(Student s) {
    for (int i = 0; i < numStudents; i++) {
      if (students[i].equals(s)) {
        //remove
      }
    }
  }
}
