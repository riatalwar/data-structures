package starter;

public class GradStudent extends Student {
  private String advisor;

  public GradStudent(String name, String email) {
    super(name, email);
    //this.advisor = advisor;
  }

  public String getAdvisor() {
    return advisor;
  }

  public void setAdvisor(String advisor) {
    this.advisor = advisor;
  }

  public String toString() {
    return this.getName(); // this. is not necessary, only for cases of resolving ambiguity
    // also works: getName(), super.getName()--this one will always go look at the super function, could result in using incorrect implementation
    // doesn't work: name, this.name, super.name, super(name)
  }
}