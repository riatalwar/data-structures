package starter;

import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    GradStudent s1 = new GradStudent("Joe Smith", "joe@jhu.edu");
    s1.setAdvisor("Advisor");

    //Roster roster = new Roster(30); -- illegal bc abstract
    Roster roster = new MoocRoster(30);
    roster.add(new Student("Spock", "spock@jhu.edu"));
    roster.add(s1); // type substitution--can use more specialized type vers where super expected
    Student jane = new GradStudent("jane", "email");
    // jane cannot call gradstudent exclusive functions, apparent type controls what var can do
    // however, if the function does exist for Student, then jane will execute the gradStudent vers of the function
    // downcast the var to be able to call the gradstudent exclusive functions
    // java will auto upcast (always safe bc always true), not the case for downcasting
    System.out.print(jane.toString()); // all classes inherit object, which has to string function

    ArrayList<Integer> numbers = new ArrayList<Integer>();
    numbers.add(10);
    // if you wanted to change to a linked list, you would have to change this in a lot of places
    // use List<Integer> as the apparent type
  }
}
