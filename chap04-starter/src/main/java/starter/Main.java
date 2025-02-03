package starter;

public class Main {
  public static void main(String[] args) {
    // Object implementation garbage
//    ArrayIndexedList al = new ArrayIndexedList(5, "");
//    al.put(2, new NullPointerException());
//    al.put(3, new Main());
//
//    String retrieved = (String) al.get(3);
//
//    System.out.println(retrieved);

    IndexedList<String> al = new ArrayIndexedList<>(10, "Hello, world!");

    al.put(1, "Goodbye");
    al.put(2, "What's up");
    // al.put(3, Main()); -- doesn't work
    String retrieved = al.get(2);

    IndexedList<Integer> intal = new ArrayIndexedList<>(10, 1);
    // Integer is a wrapper class for int so it can be used as a reference type
    intal.put(1, 20); // 20 autoboxed to Integer
  }
}
