# Discussion
# Part 1
Raw Experiment Results
hotel_california
JmhRuntimeTest.arrayMap    avgt    2  0.241          ms/op
JmhRuntimeTest.avlTreeMap  avgt    2  0.176          ms/op
JmhRuntimeTest.bstMap      avgt    2  0.172          ms/op
JmhRuntimeTest.treapMap    avgt    2  0.220          ms/op

federalist01
Benchmark                  Mode  Cnt  Score   Error  Units
JmhRuntimeTest.arrayMap    avgt    2  0.230          ms/op
JmhRuntimeTest.avlTreeMap  avgt    2  0.171          ms/op
JmhRuntimeTest.bstMap      avgt    2  0.176          ms/op
JmhRuntimeTest.treapMap    avgt    2  0.224          ms/op

moby_dick
Benchmark                  Mode  Cnt  Score   Error  Units
JmhRuntimeTest.arrayMap    avgt    2  0.239          ms/op
JmhRuntimeTest.avlTreeMap  avgt    2  0.171          ms/op
JmhRuntimeTest.bstMap      avgt    2  0.177          ms/op
JmhRuntimeTest.treapMap    avgt    2  0.224          ms/op

pride_and_prejudice
Benchmark                  Mode  Cnt  Score   Error  Units
JmhRuntimeTest.arrayMap    avgt    2  0.254          ms/op
JmhRuntimeTest.avlTreeMap  avgt    2  0.180          ms/op
JmhRuntimeTest.bstMap      avgt    2  0.180          ms/op
JmhRuntimeTest.treapMap    avgt    2  0.222          ms/op

In each trial, we see that ArrayMap is consistently the worst, followed by TreapMap, and then AVLTreeMap and BSTMap which have similar performance. It is easy to see why ArrayMap would have the worst performance, as it is unsorted and each search will require a linear traversal of the array, unlike the AVL, BST, and Treap, which are more likely to have closer to O(lgn) operation runtimes (this is not guaranteed for BST and Treap, but it is more likely). The lessened performance of Treap is likely due to the variability and readjustments of the structure, as the random ordering involved adds additional complexity requiring O(h) (h = height of Treap) runtime for rotations in addition to the insertion logic. Meanwhile, a BST requires no rotations, and an AVL tree will have O(1) runtime for rotation after insertion. The BST and AVL tree have relatively similar performance, which is likely due to a combination of the BST being somewhat balanced by chance and the AVL tree having more involved operations, as the rotations and height calculations are an extra step in the process. To study this further, we could look more at the performance on highly skewed data that would result in an imbalanced BST, while the AVL tree self-balances.

# Part 2:
Strategy 1 will be effective because the largest element will be removed k times, as best is always guaranteed to be the maximum of the dataset by the rules of heaps. This ensures that the kth removed element will be the kth largest. Insertion into a binary heap will take O(lgn) time, and given that there are n insertions, creating the heap has an overall O(nlgn) complexity. In addition, each of the k removals will have O(lgn) complexity, so we have O(klgn) complexity for the removals and an overall time complexity of O(nlgn) since nlgn > klgn. For space complexity, we will have an input space of O(n) for the array and auxiliary space of O(n) for the heap, giving us overall space complexity of O(n).

Strategy 2 is also effective, as each of the n - k removals will remove one of the n - k smallest elements. When left with the k largest elements, the minimum will be the kth largest. Building the initial tree will have O(klgk) time complexity given that k elements are being inserted with O(lgk) complexity, and each of the n - k removals will have a similar worst case O((n - k)lg(k)) complexity. Overall, the time complexity will be O(nlgn) since klgn + (n - k)lgn = nlgn. For space complexity, the input space will have O(n) complexity for the array and the auxiliary space complexity will be O(k) for the heap, making the overall space complexity O(n) since n > k.