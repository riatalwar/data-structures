package starter;

public class Student {
  private final String name;
  private final String email;

  public Student(String name, String email) {
    // not inherited, can still be called w/in subclass
    this.name = name;
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }
}
