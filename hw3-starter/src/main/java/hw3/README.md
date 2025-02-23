# Discussion

## PART I: MeasuredIndexedList Iterator


## PART II: Profiling Sorting Algorithms

When initially running the driver program in its original state, I noticed that bubble sort was producing 5,074,020 mutations for the ascending data. This was strange, since bubble sort is implemented with the optimization to only make swaps when the data is out of order and stop if no swaps are made. This means that it should not make any mutations when operating on already ordered data like the ascending data. There were also 12,055,014 accesses, which is unusually large given that the program should have stopped after one pass through the ordered data. After looking at the ascending data, I determined that it was, in fact, ordered by increasing numbers, so I concluded that there must be an error in the code somewhere.
To get a better sense of the driver program, I ran it on a small set of 3 single digit numbers, producing 0 mutations as expected. There were also 4 accesses, which matched my expectations. I then began testing smaller portions of the original ascending datasets, testing random sizes between 1 and 100 until I narrowed down the discrepancy to changing the number of values from 10 to 11. At 10 values, the program produced 18 accesses and 0 mutations as expected, indicating that only one pass was made. However, this number increased dramatically at 11 values to 124 accesses and 16 mutations, indicating that there was something different about the data at this point. The runtime also increased from 0.000013 seconds to 0.000031 seconds despite 5-10 values having very similar runtimes ranging from 0.000012 to 0.000015, another indication of change at this point.
Upon closer inspection of the data, I noticed that adding an 11th value is when the data changed from 1 to 2 digits, which I quickly connected to the code, which stores the data as strings. The numbers were being ordered by the ASCII values of their characters, not the values of the numbers themselves. Therefore, I concluded that the discrepancy was caused by the fact that the ascending integer values were being stored as strings, changing the expected ordering of the data. A more accurate profiling program would store the data as integers to get statistics that correspond with the ordering. Alternatively, the numbers could be placed in ascending ASCII order, just as the descending.data file is ordered by the ASCII values of the numbers instead of the numerical values.


## PART III: Analysis of Selection Sort


## PART IV: Reflecting on Search Heuristics


## PART V: Profiling Search Heuristics

