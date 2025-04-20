# Homework 6 Discussions

Here are the results of finding each path. In each case, I ran several trials for both the system runtime and memory monitor tests and selected trials that were approximately representative of the average outcome. There are results for both before and after the optimization, and the discussion below explains the results.

Path 1: JHU to Druid Lake
Total Distance: 8818.5187
121.60 	45662:
137.15 	40816:
318.90 	40867:N_CHARLES_ST
60.49 	42002:E_33RD_ST
293.14 	8344:3200_BLK_N_CHARLES_ST
318.96 	11147:3200_BLK_N_CHARLES_ST
151.62 	39907:ART_MUSEUM_DR
665.00 	48094:UNIT__BLK_ART_MUSEUM_DR
129.55 	43910:ART_MUSEUM_DR
213.64 	46364:WYMAN_PARK_DR
255.02 	26692:2900_BLK_WYMAN_PARK_DR
42.03 	39554:N_HOWARD_ST
136.52 	26872:200_BLK_W_29TH_ST
146.67 	26712:200_BLK_W_29TH_ST
167.43 	15177:200_BLK_W_29TH_ST
230.86 	11871:200_BLK_W_29TH_ST
196.68 	14691:300_BLK_W_29TH_ST
224.61 	30101:300_BLK_W_29TH_ST
123.84 	5917:300_BLK_W_29TH_ST
79.80 	21125:300_BLK_W_29TH_ST
78.16 	21194:400_BLK_W_29TH_ST
115.90 	17656:400_BLK_W_29TH_ST
600.37 	26121:500_BLK_W_29TH_ST
480.41 	14609:2900_BLK_SISSON_ST
284.62 	23569:700_BLK_WYMAN_PARK_DR
394.12 	18109:800_BLK_WYMAN_PARK_DR
281.96 	31600:900_BLK_WYMAN_PARK_DR
39.03 	33121:900_BLK_WYMAN_PARK_DR
71.18 	34391:1000_BLK_WYMAN_PARK
1160.95 	41471:EAST_DR
190.65 	43386:UNNAMED_ST
1107.65 	41640:

Before Optimization:
~~~ SystemRuntimeTest ~~~
Config: baltimore.streets.txt from -76.6175,39.3296 to -76.6383,39.3206
Loading network took 91 milliseconds.
Finding shortest path took 23 milliseconds.
~~~~~~     END     ~~~~~~
~~~ MemoryMonitorTest ~~~
Config: baltimore.streets.txt from -76.6175,39.3296 to -76.6383,39.3206
	Used memory: 5129.23 KB (Δ = 0.000)
Instantiating empty Graph data structure
Instantiating empty StreetSearcher object
	Used memory: 5480.37 KB (Δ = 351.141)
Loading the network
	Used memory: 20024.15 KB (Δ = 14543.781)
Finding the shortest path
	Used memory: 19021.36 KB (Δ = -1002.789)
Setting objects to null (so GC does its thing!)
	Used memory: 9118.84 KB (Δ = -9902.516)
~~~~~~     END     ~~~~~~

After Optimization:
~~~ SystemRuntimeTest ~~~
Config: baltimore.streets.txt from -76.6175,39.3296 to -76.6383,39.3206
Loading network took 105 milliseconds.
Finding shortest path took 11 milliseconds.
~~~~~~     END     ~~~~~~
~~~ MemoryMonitorTest ~~~
Config: baltimore.streets.txt from -76.6175,39.3296 to -76.6383,39.3206
	Used memory: 5130.66 KB (Δ = 0.000)
Instantiating empty Graph data structure
Instantiating empty StreetSearcher object
	Used memory: 5489.23 KB (Δ = 358.570)
Loading the network
	Used memory: 16902.96 KB (Δ = 11413.734)
Finding the shortest path
	Used memory: 17838.44 KB (Δ = 935.477)
Setting objects to null (so GC does its thing!)
	Used memory: 8068.05 KB (Δ = -9770.391)
~~~~~~     END     ~~~~~~

Path 2: 7-11 to Druid Lake
Total Distance: 5827.3652
	397.42 	24509:2800_BLK_REMINGTON_AVE
	196.68 	14691:300_BLK_W_29TH_ST
	224.61 	30101:300_BLK_W_29TH_ST
	123.84 	5917:300_BLK_W_29TH_ST
	 79.80 	21125:300_BLK_W_29TH_ST
	 78.16 	21194:400_BLK_W_29TH_ST
	115.90 	17656:400_BLK_W_29TH_ST
	600.37 	26121:500_BLK_W_29TH_ST
	480.41 	14609:2900_BLK_SISSON_ST
	284.62 	23569:700_BLK_WYMAN_PARK_DR
	394.12 	18109:800_BLK_WYMAN_PARK_DR
	281.96 	31600:900_BLK_WYMAN_PARK_DR
	 39.03 	33121:900_BLK_WYMAN_PARK_DR
	 71.18 	34391:1000_BLK_WYMAN_PARK
	1160.95 	41471:EAST_DR
	190.65 	43386:UNNAMED_ST
	1107.65 	41640:


Before Optimization: TODO
~~~ SystemRuntimeTest ~~~
Config: baltimore.streets.txt from -76.6214,39.3212 to -76.6383,39.3206
Loading network took 102 milliseconds.
Finding shortest path took 26 milliseconds.
~~~~~~     END     ~~~~~~
~~~ MemoryMonitorTest ~~~
Config: baltimore.streets.txt from -76.6214,39.3212 to -76.6383,39.3206
	Used memory: 5128.88 KB (Δ = 0.000)
Instantiating empty Graph data structure
Instantiating empty StreetSearcher object
	Used memory: 5468.56 KB (Δ = 339.680)
Loading the network
	Used memory: 20861.06 KB (Δ = 15392.500)
Finding the shortest path
	Used memory: 20062.63 KB (Δ = -798.430)
Setting objects to null (so GC does its thing!)
	Used memory: 10180.88 KB (Δ = -9881.750)
~~~~~~     END     ~~~~~~
After Optimization:
~~~ SystemRuntimeTest ~~~
Config: baltimore.streets.txt from -76.6214,39.3212 to -76.6383,39.3206
Loading network took 124 milliseconds.
Finding shortest path took 11 milliseconds.
~~~~~~     END     ~~~~~~
~~~ MemoryMonitorTest ~~~
Config: baltimore.streets.txt from -76.6214,39.3212 to -76.6383,39.3206
	Used memory: 5117.61 KB (Δ = 0.000)
Instantiating empty Graph data structure
Instantiating empty StreetSearcher object
	Used memory: 5460.51 KB (Δ = 342.898)
Loading the network
	Used memory: 19907.14 KB (Δ = 14446.633)
Finding the shortest path
	Used memory: 18309.90 KB (Δ = -1597.242)
Setting objects to null (so GC does its thing!)
	Used memory: 8520.69 KB (Δ = -9789.211)
~~~~~~     END     ~~~~~~


Path 3: Inner Harbor to JHU
Total Distance: 16570.4909
	462.64 	20226:100_BLK_SOUTH_ST
	163.04 	48137:UNIT__BLK_SOUTH_ST
	 71.81 	47386:UNIT__BLK_SOUTH_ST
	158.76 	47419:UNIT__BLK_SOUTH_ST
	271.10 	47548:UNIT__BLK_SOUTH_ST
	268.59 	47459:UNIT__BLK_GUILFORD_AVE
	343.86 	28621:200_BLK_E_FAYETTE_ST
	296.82 	33813:100_BLK_N_CALVERT_ST
	134.19 	28959:200_BLK_N_CALVERT_ST
	299.37 	24432:200_BLK_N_CALVERT_ST
	454.21 	23235:300_BLK_N_CALVERT_ST
	147.99 	9313:300_BLK_N_CALVERT_ST
	185.36 	5947:400_BLK_N_CALVERT_ST
	151.15 	30373:100_BLK_ORLEANS_ST
	156.75 	31819:100_BLK_ORLEANS_ST
	165.18 	36462:400_BLK_SAINT_PAUL_PL
	199.96 	33237:500_BLK_SAINT_PAUL_PL
	 50.14 	42343:SAINT_PAUL_PL
	204.02 	35069:500_BLK_SAINT_PAUL_PL
	369.08 	27667:600_BLK_SAINT_PAUL_ST
	 67.58 	23282:600_BLK_SAINT_PAUL_ST
	 63.19 	8359:700_BLK_SAINT_PAUL_ST
	201.99 	31442:700_BLK_SAINT_PAUL_ST
	120.04 	22312:700_BLK_SAINT_PAUL_ST
	164.64 	24117:800_BLK_SAINT_PAUL_ST
	 58.65 	2462:800_BLK_SAINT_PAUL_ST
	163.17 	14582:800_BLK_SAINT_PAUL_ST
	347.58 	16051:900_BLK_SAINT_PAUL_ST
	147.31 	17769:900_BLK_SAINT_PAUL_ST
	159.48 	34028:1000_BLK_SAINT_PAUL_ST
	341.45 	8766:1000_BLK_SAINT_PAUL_ST
	383.17 	1691:1100_BLK_SAINT_PAUL_ST
	386.86 	4356:1200_BLK_SAINT_PAUL_ST
	338.91 	19656:1300_BLK_SAINT_PAUL_ST
	 42.19 	40957:SAINT_PAUL_ST
	 46.30 	44623:SAINT_PAUL_ST
	216.46 	39136:RAMP
	338.93 	43462:I_83___S
	 92.13 	45367:I_83___S
	107.28 	40942:N_CHARLES_ST
	123.62 	40898:N_CHARLES_ST
	468.44 	13325:1500_BLK_N_CHARLES_ST
	386.86 	4876:1700_BLK_N_CHARLES_ST
	212.37 	22851:1800_BLK_N_CHARLES_ST
	244.03 	8607:1800_BLK_N_CHARLES_ST
	 46.58 	20118:1800_BLK_N_CHARLES_ST
	382.67 	19448:1900_BLK_N_CHARLES_ST
	366.72 	11838:2000_BLK_N_CHARLES_ST
	367.72 	1734:2100_BLK_N_CHARLES_ST
	376.14 	26449:2200_BLK_N_CHARLES_ST
	393.44 	5511:2300_BLK_N_CHARLES_ST
	239.67 	18280:2400_BLK_N_CHARLES_ST
	 99.97 	2083:2400_BLK_N_CHARLES_ST
	204.48 	30800:2400_BLK_N_CHARLES_ST
	209.45 	28376:2500_BLK_N_CHARLES_ST
	 75.71 	10673:2500_BLK_N_CHARLES_ST
	102.26 	18381:2500_BLK_N_CHARLES_ST
	165.69 	25136:2500_BLK_N_CHARLES_ST
	467.97 	8403:2600_BLK_N_CHARLES_ST
	467.02 	21531:2700_BLK_N_CHARLES_ST
	465.66 	21565:2800_BLK_N_CHARLES_ST
	231.86 	42406:N_CHARLES_ST
	166.74 	41710:N_CHARLES_ST
	 64.04 	39032:N_CHARLES_ST
	240.14 	41330:N_CHARLES_ST
	233.97 	44412:N_CHARLES_ST
	195.68 	44001:N_CHARLES_ST
	205.02 	44656:N_CHARLES_ST
	202.15 	42144:N_CHARLES_ST
	245.47 	42334:N_CHARLES_ST
	318.90 	40867:N_CHARLES_ST
	137.15 	40816:
	121.60 	45662:

Before Optimization:
~~~ SystemRuntimeTest ~~~
Config: baltimore.streets.txt from -76.6107,39.2866 to -76.6175,39.3296
Loading network took 130 milliseconds.
Finding shortest path took 29 milliseconds.
~~~~~~     END     ~~~~~~
~~~ MemoryMonitorTest ~~~
Config: baltimore.streets.txt from -76.6107,39.2866 to -76.6175,39.3296
	Used memory: 5117.41 KB (Δ = 0.000)
Instantiating empty Graph data structure
Instantiating empty StreetSearcher object
	Used memory: 5473.54 KB (Δ = 356.133)
Loading the network
	Used memory: 21213.88 KB (Δ = 15740.336)
Finding the shortest path
	Used memory: 20361.34 KB (Δ = -852.531)
Setting objects to null (so GC does its thing!)
	Used memory: 10470.96 KB (Δ = -9890.383)
~~~~~~     END     ~~~~~~

After Optimization:
~~~ SystemRuntimeTest ~~~
Config: baltimore.streets.txt from -76.6107,39.2866 to -76.6175,39.3296
Loading network took 118 milliseconds.
Finding shortest path took 23 milliseconds.
~~~~~~     END     ~~~~~~
~~~ MemoryMonitorTest ~~~
Config: baltimore.streets.txt from -76.6107,39.2866 to -76.6175,39.3296
	Used memory: 5122.27 KB (Δ = 0.000)
Instantiating empty Graph data structure
Instantiating empty StreetSearcher object
	Used memory: 5455.44 KB (Δ = 333.172)
Loading the network
	Used memory: 20953.30 KB (Δ = 15497.859)
Finding the shortest path
	Used memory: 19686.80 KB (Δ = -1266.500)
Setting objects to null (so GC does its thing!)
	Used memory: 9896.72 KB (Δ = -9790.078)
~~~~~~     END     ~~~~~~

Some interesting notes about the results: The longest path initially had a longer runtime than the first two by about 4ms at around 29ms on average compared to 25ms. The cost of loading the network is fairly consistent accross the different paths, which makes sense given that the same initial set of vertices will be loaded into the graph in each case. Interestingly, there is not a significant improvement in memory usage between any of the endpoints. This is likely a result of the fact that we must load and explore every vertex by the initial setup of the algorithm, giving it a consistent spacial complexity regardless of the distance between the endpoints.
After these results, I thought that there must be a way to avoid exploring all the vertices once the shortest path to the end vertex is found, so I added the following break condition within my implementation of Dijkstra's algorithm:
    if (min.getFirst().get().equals(endName)) {
      break;
    }
Once the end is explored, we know that we have found the shortest path from the start, so there is no reason to continue exploring irrelevant paths. With this change, there was an immediate drop in the runtime, with both of the first two path running at about 11ms, more than half of what they were before. The longest path had an average of about 23ms, which was not as significant of a drop. However, this can be explained by the fact that a larger percentage of the map had to be explored regardless, meaning that it's base runtime was guaranteed to be higher. This also created a greater difference between paths of different lengths, as the necessary amount of exploration changed significantly to have a larger gap between the two.
I also saw an improvement in memory usage. The initial instantiation had a very similar cost, but the cost of loading the network and finding the shortest path reduced significantly. This makes sense, as these is an equivalent base cost for loading the data structures and vertices, but shorter paths will have fewer vertices to explore and store distances for as there will be less intermediate steps to get from the starting point to the destination.