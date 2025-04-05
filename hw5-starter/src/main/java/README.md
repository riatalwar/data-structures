# Discussion

While developing the chaining hash map I tested different load factor thresholds of 0.5, 0.75, and 0.9 to see which resulted in better performance. A higher load factor will produce less space cost due to fewer resizings, but may result in greater time complexity due to longer chains in the buckets resulting in longer search times. Lower load factors will result in shorter times to search through buckets, but will consume greater space resources as more space will have to be allocated for less data. In addition, the overhead caused by rehashing may outweigh the benefits of sparser data in terms of time. By testing a few different values, I was able to find a balance between these two issues.

Threshold 0.75
JmhRuntimeTest.buildSearchEngine                                     apache.txt  avgt    2         199.142           ms/op
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc          apache.txt  avgt    2    92106868.000           bytes
JmhRuntimeTest.buildSearchEngine                                     jhu.txt  avgt    2           0.218           ms/op
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc          jhu.txt  avgt    2    17007500.000           bytes
JmhRuntimeTest.buildSearchEngine                                     random164.txt  avgt    2         815.623           ms/op
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc          random164.txt  avgt    2   994996400.000           bytes

Threshold 0.5
JmhRuntimeTest.buildSearchEngine                                     apache.txt  avgt    2         199.575           ms/op
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc          apache.txt  avgt    2    91216540.000           bytes
JmhRuntimeTest.buildSearchEngine                                     jhu.txt  avgt    2           0.234           ms/op
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc          jhu.txt  avgt    2    17402252.000           bytes
JmhRuntimeTest.buildSearchEngine                                     random164.txt  avgt    2         859.572           ms/op
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc          random164.txt  avgt    2  1099454040.000           bytes

Threshold 0.9
JmhRuntimeTest.buildSearchEngine                                     apache.txt  avgt    2         208.494           ms/op
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc          apache.txt  avgt    2    96056640.000           bytes
JmhRuntimeTest.buildSearchEngine                                     jhu.txt  avgt    2           0.227           ms/op
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc          jhu.txt  avgt    2    16851300.000           bytes
JmhRuntimeTest.buildSearchEngine                                     random164.txt  avgt    2         821.552           ms/op
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc          random164.txt  avgt    2  1099381312.000           bytes

One interesting comparison is the results of random164.txt, as the 0.5 threshold has both the worse space and time usage, while the 0.75 threshold has the best for both. This is likely because the 0.5 threshold caused more significantly costly rehashes of the data, resulting in both more time to complete the operation and more space used as a result.

The jhu.txt file produces very similar results, with the 0.75 threshold having the best time results due to less rehashing, and 0.9 threshold having slightly better time and the best space usage due to minimal resizing. Here, the tradeoff of sparser data with a low threshold is not worth it due to the cost of rehashing.

Next, for apache.txt the 0.5 and 0.75 thresholds have similar performance, with 0.75 actually having slightly better space usage, but the 0.9 threshold is worse on both counts, running slower due to longer searches and consuming more space. Given these results, I concluded that the 0.75 threshold was a reasonably balanced choice, as it reduces overhead from constant rehashing but maintains the benefits of having more sparsely distributed data.

Secondly, while working on the open addressing hash map I tested three probing methods: linear, quadratic, and double hashing, and tried each one with and without tomb replacement (overwriting the first tomb found during probing during insert after ensuring the key is not already mapped).

Linear Probing w/o Replacement
JmhRuntimeTest.buildSearchEngine                                       joanne.txt  avgt    2          0.085           ms/op
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc            joanne.txt  avgt    2   17623280.000           bytes
JmhRuntimeTest.buildSearchEngine                                         urls.txt  avgt    2          0.028           ms/op
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc              urls.txt  avgt    2   16413176.000           bytes

Linear Probing w/ Replacement
JmhRuntimeTest.buildSearchEngine                                       joanne.txt  avgt    2          0.188           ms/op
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc            joanne.txt  avgt    2   16042960.000           bytes
JmhRuntimeTest.buildSearchEngine                                         urls.txt  avgt    2          0.017           ms/op
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc              urls.txt  avgt    2   17330132.000           bytes

Quadratic Probing w/o Replacement
JmhRuntimeTest.buildSearchEngine                                       joanne.txt  avgt    2          0.086           ms/op
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc            joanne.txt  avgt    2   17657572.000           bytes
JmhRuntimeTest.buildSearchEngine                                         urls.txt  avgt    2          0.016           ms/op
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc              urls.txt  avgt    2   17382556.000           bytes

Quadratic Probing w/ Replacement
JmhRuntimeTest.buildSearchEngine                                       joanne.txt  avgt    2          0.092           ms/op
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc            joanne.txt  avgt    2   17659988.000           bytes
JmhRuntimeTest.buildSearchEngine                                         urls.txt  avgt    2          0.016           ms/op
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc              urls.txt  avgt    2   17429512.000           bytes

Double Hash Probing w/o Replacement
JmhRuntimeTest.buildSearchEngine                                       joanne.txt  avgt    2          0.234           ms/op
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc            joanne.txt  avgt    2   15817912.000           bytes
JmhRuntimeTest.buildSearchEngine                                         urls.txt  avgt    2          0.028           ms/op
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc              urls.txt  avgt    2   16434552.000           bytes

Double Hash Probing w/ Replacement
JmhRuntimeTest.buildSearchEngine                                       joanne.txt  avgt    2          0.105           ms/op
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc            joanne.txt  avgt    2   15902384.000           bytes
JmhRuntimeTest.buildSearchEngine                                         urls.txt  avgt    2          0.016           ms/op
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc              urls.txt  avgt    2   16190084.000           bytes

On the joanne.txt file, double hashing had significantly improved space complexity compared to both linear and quadratic probing. However, it did demonstrate worse time performance in the case of probing without tomb replacement. This was a slightly strange result as double hashing is meant to spread out the data more and result in less clustering than quadratic and linear probing as each key will be probed at a unique interval based on its hash instead of all keys probing the same way. In comparing with versus without tomb replacement, most of the space complexities were similar for the same algorithm, but linear probing was noticeably worse without tomb replacement. This is consistent with expectations because maps littered with tombs will need to be rehashed and therefore resized more often, causing them to be larger for the same amount of data. Interestingly, the time complexity compared across tomb/no tomb replacement was very inconsistent, with linear probing getting worse with replacement and double hashing improving.

For urls.txt, double hashing again had the best space complexity with relatively comparable time complexities to the other two probing methods. In this case, we saw an consistent improvement or similarity in time complexity when comparing with and without tomb replacement. This strategy allows for shorter search chains, resulting in better overall time complexity and less necessary rehashings as the map will fill up slower. Quadratic probing has the overall worst space complexity in both situations (joanne.txt and urls.txt), likely due to the possibility of failing to find open space while probing, resulting in a resizing earlier than necessary.

From this analysis, I concluded that double hashing with tomb replacement had the best overall performance for the open addressing methods. Using this result, I proceeded to compare the 0.75 load threshold chaining map with the double hashing with tomb replacement open addressing map.

Double Hash
JmhRuntimeTest.buildSearchEngine                            apache.txt  avgt    2      20863.330           ms/op
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc apache.txt  avgt    2   67230460.000           bytes
JmhRuntimeTest.buildSearchEngine                               jhu.txt  avgt    2          0.327           ms/op
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc    jhu.txt  avgt    2   16489420.000           bytes

Chaining
JmhRuntimeTest.buildSearchEngine                            apache.txt  avgt    2         199.142           ms/op
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc apache.txt  avgt    2    92106868.000           bytes
JmhRuntimeTest.buildSearchEngine                               jhu.txt  avgt    2           0.218           ms/op
JmhRuntimeTest.buildSearchEngine:+c2k.gc.maximumUsedAfterGc    jhu.txt  avgt    2    17007500.000           bytes

There are two interesting patterns here: double hashing shows better space complexity compared to the chaining map, and the chaining map shows better time complexity, significantly so in the case of apache.txt. Because of this, the choice is depending on which metric we should prioritize. I decided to prioritize time, as the space complexity is not nearly as wildly different as the time complexity, and I concluded that the chaining hash map appears to be the best choice of data structure in this case.