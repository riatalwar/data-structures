package starter;

import java.util.List;

public class Main {

  public static void main(String [] args) {
    int nums[] = new int[]{1, 2, 3, 4, 5, 6};
    List<Integer> lNums = List.of(1, 2, 3, 4, 5, 6, 7);

    for (int i = 0; i < nums.length; i++) {
      System.out.println(nums[i]);
    }

    for (int i : nums) {
      System.out.println(i);
    }

    for (int i : lNums) {
      System.out.println(i);
    }

  }
}
