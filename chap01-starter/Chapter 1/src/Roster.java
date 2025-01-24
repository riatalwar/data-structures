import java.lang.reflect.Array;

public class Roster {

    private Student[] students;
    private int numStudent;

    public Roster(int size) {
        students = new Student[size];
        numStudent = 0;
    }

    public void addStudent(Student s) {
        students[numStudent] = s;
    }

    public void removeStudent(Student s) {
        //stub
    }

    public Student find(String email) {
        // linear search; best case: 1; worst case: numStudent --> not ideal w large datasets
        // example exercise: best: 0.004 (always true), worst: 4.00 (can get significantly worse)
        for (int i = 0; i < numStudent; i++) {
            if (students[i].getEmail().equals(email)) { //== compares mem location, check if is same object
                return students[i];
            }
        }
        return null;
    }

    // runtime: best case: 1, worse case: log2(n)
    public Student findBinary(String email) {
        // binary search iterative vers
        int start = 0;
        int end = numStudent - 1;
        while (start <= end) {
           int mid = (start + end) / 2;
           if (students[mid].getEmail().equals(email)) {
               return students[mid];
           } else if (students[mid].getEmail().compareTo(email) > 0) {
               end = mid - 1;
           } else {
               start = mid + 1;
           }
        }
        return null;
    }

    // private to hide implementation details
    private Student findRec(String email, int start, int end) {
        // binary search recursive vers
        int mid = (start + end) / 2;
        if (start > end) { // base case
            return null;
        } else if (students[mid].getEmail().equals(email)) { // found
            return students[mid];
        } else if (students[mid].getEmail().compareTo(email) > 0) {
            return findRec(email, start, mid - 1);
        } else {
            return findRec(email, mid + 1, end);
        }
    }

    public Student findRec(String email) {
        //easier usage for recursive binary search
        return findRec(email, 0, numStudent - 1);
    }
}
