# Discussion

## PART I: MeasuredIndexedList Iterator

When looking at the implementation of the iterator, we see that it does effectively the same thing as the get method: it checks that the index is valid by using hasNext() and then directly indexes into the array to get the element. However, it is also important to consider the purpose of an iterator relative to the purpose of measuring the mutations and accesses. Measuring these values is useful for determining the complexity of algorithms such as find() or insert(). We can determine how many operations are used in these algorithms to evaluate the efficiency. However, the iterator is not typically used here, and is more useful for outputting the contents of the array without much computational purpose. The number of accesses executed by an iterator is easy to determine, and is not useful for evaluating the overall efficiency of the data structure, only the efficiency of the iterator itself.
Regardless of this, it is not possible to override the next() and hasNext() methods, as they are members of a private class within ArrayIndexedList, meaning that they cannot be accessed by the subclasses of ArrayIndexedList. So, it is not possible to override the next() and hasNext() methods without creating an entirely new iterator class within MeasuredIndexedList.


## PART II: Profiling Sorting Algorithms

When initially running the driver program in its original state, I noticed that bubble sort was producing 5,074,020 mutations for the ascending data. This was strange, since bubble sort is implemented with the optimization to only make swaps when the data is out of order and stop if no swaps are made. This means that it should not make any mutations when operating on already ordered data like the ascending data. There were also 12,055,014 accesses, which is unusually large given that the program should have stopped after one pass through the ordered data. Selection sort should follow a similar pattern and not make swaps if the data is ordered since it would not be able to find a value lower than the one it is at, but it also produced 7,980 mutations. Insertion sort should also produce only as many mutations as there are elements, as there should only be one insertion for each round of the outer loop on ordered data. After looking at the ascending data, I determined that it was, in fact, ordered by increasing numbers, so I concluded that there must be an error in the code somewhere.
To get a better sense of the driver program, I ran it on a small set of 3 single digit numbers, producing 0 mutations as expected. There were also 4 accesses, which matched my expectations. I then began testing smaller portions of the original ascending datasets, testing random sizes between 1 and 100 until I narrowed down the discrepancy to changing the number of values from 10 to 11. At 10 values, the program produced 18 accesses and 0 mutations as expected, indicating that only one pass was made. However, this number increased dramatically at 11 values to 124 accesses and 16 mutations, indicating that there was something different about the data at this point. The runtime also increased from 0.000013 seconds to 0.000031 seconds despite 5-10 values having very similar runtimes ranging from 0.000012 to 0.000015, another indication of change at this point.
Upon closer inspection of the data, I noticed that adding an 11th value is when the data changed from 1 to 2 digits, which I quickly connected to the code, which stores the data as strings. The numbers were being ordered by the ASCII values of their characters, not the values of the numbers themselves. Therefore, I concluded that the discrepancy was caused by the fact that the ascending integer values were being stored as strings, changing the expected ordering of the data. A more accurate profiling program would store the data as integers to get statistics that correspond with the ordering. Alternatively, the numbers could be placed in ascending ASCII order, just as the descending.data file is ordered by the ASCII values of the numbers instead of the numerical values.
On accurately ascending data, bubble sort would have been the most efficient algorithm, followed by selection and then insertion sort. However, when it comes to randomized or descending data, selection sort becomes the best algorithm with bubble sort actually being the least efficient.


## PART III: Analysis of Selection Sort

Comparisons
Line by line counts:
3 - a.length
5 - sum 2 to a.length
6 - sum 1 to a.length - 1

Explanation:
The outer loop will run n - 1 times, but the condition will be evaluated one extra time to account for the check to terminate the loop, so we have n comparisons at line 3. The inner loop at line 5 then evaluates its condition n - i times for each of the n - 1 iterations of the outer loop, so this requires us to sum the numbers 2 through n. This can be done using Gauss's formula, which gives us (n - 1) * (n + 2) / 2 = (n^2 + n - 2) / 2 comparisons. Lastly, we have the condition at line six evaluated inside the inner loop. The inner loop is run n - (i + 1) times for each iteration of the outer loop, so we must sum the numbers 1 through n - 1. We can again use the formula to get (n - 1) * (n - 1 + 1) / 2 = (n^2 - n) / 2 comparisons. Overall, this gives us n + ((n^2 + n - 2) / 2) + ((n^2 - n) / 2) = n^2 + n - 1 comparisons.

Assignments
Line by line counts:
3 - 1
4 - a.length - 1
5 - a.length - 1
7 - sum 1 to a.length - 1
10 - a.length - 1
11 - a.length - 1
12 - a.length - 1

Explanation:
Next, we have the number of assignments. Line 3 will produce one assignment. Line 4 produces n - 1 assignments, one for each iteration of the loop. The same is true for line 5. Line 7 then produces n - i assignments in the worst case scenario, so we must sum the numbers 1 through n - 1, which gives us (n - 1) * (n - 1 + 1) / 2 = (n^2 - n) / 2 assignments by the same formula as above. Next, line 10, 11, and 12 each result in n - 1 comparisons, so we have 3(n - 1). Overall, this adds up to 1 + (n - 1) + (n - 1) + (n^2 - n) / 2 + 3(n - 1) = 1 + 5n - 5 + (1/2) n^2 - (1/2) n = (1/2) n^2 + (9/2) n - 4 assignments.


## PART IV: Reflecting on Search Heuristics

In the case of TransposeArraySet, the removal logic is actually working against the heuristic. When an element is removed, the last element in the array is inserted at the index of the removed item. Since the goal of the TransposeArraySet is to gradually order the array by most searched for, this will throw off the ordering of the array, as the items at the end are theoretically the least searched for but will be moved up within the list due to the structure of remove. Because of this, the removal logic conflicts with the heuristic and hampers its goal.

For MoveToFrontLinkedSet, the removal logic is more reasonable, as it will not alter the overall ordering aside from removing the designated node by the end of the function. However, there is still the issue of redundancy. Because the remove operation uses find to first locate the node, it will end up moving the node to the beginning of the list despite the fact that it is about to be removed. While this does not end up affecting the efficiency of has since the general ordering of the list is not affected, it is not necessary to have that additional step when removing an element.

## PART V: Profiling Search Heuristics

