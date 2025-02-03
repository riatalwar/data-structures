package starter;

public class IndexException extends Throwable { //must be throwable to be caught

  //standard to have default and string input constructors
  public IndexException () {
    super();
  }

  public IndexException (String message) {
    super(message);
  }
}
