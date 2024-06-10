Page Replacement Algorithms

Algorithms Implemented: FIFO (First-In-First-Out), LRU (Least Recently Used), OPT (Optimal)

Predefined Reference Strings
1. [6, 9, 5, 1, 7, 3, 8, 2, 5, 6, 5, 6, 8, 7, 9]
2. [3, 4, 1, 7, 6, 3, 6, 9, 0, 7, 2, 7, 2, 5, 1]
3. [8, 2, 5, 2, 0, 4, 5, 1, 2, 1, 9, 0, 0, 7, 0]

The randomly generated reference strings were tested for the following configurations:
- Reference String Size: 15
- Number of Page Frames: 3, 5, 7

Results

| Reference String | Page Frames | FIFO Page Faults | LRU Page Faults | OPT Page Faults |
|------------------|-------------|------------------|-----------------|-----------------|
| `[6, 9, 5, 1, 7, 3, 8, 2, 5, 6, 5, 6, 8, 7, 9]` | 7 | 10 | 10 | 8 |
| `[3, 4, 1, 7, 6, 3, 6, 9, 0, 7, 2, 7, 2, 5, 1]` | 7 | 9 | 10 | 9 |
| `[8, 2, 5, 2, 0, 4, 5, 1, 2, 1, 9, 0, 0, 7, 0]` | 7 | 8 | 8 | 8 |

Overall Performance

| Algorithm | Total Page Faults |
|-----------|-------------------|
| FIFO      | 27                |
| LRU       | 28                |
| OPT       | 25                |

Best Performing Algorithm
OPT performed the best overall with the least number of page faults across all reference strings and configurations.

Analysis
The optimal page replacement algorithm performed the best because it always replaces the page that will not be used for the longest period of time in the future. This is theoretically the best possible page replacement strategy but is pretty impractical for real-time systems because it requires future knowledge of the reference string.

Big O Runtimes

1. FIFO (First-In-First-Out)
   - Time Complexity**: O(n) for each page reference (where n is the number of page frames).

2. LRU (Least Recently Used)
   - Time Complexity**: O(1) for access and O(n) for updating the order (using a linked hash map).

3. OPT (Optimal)
   - Time Complexity**: O(n) for each page reference (finding the page to replace requires scanning the future references).

Conclusion

The OPT algorithm is the best performing in terms of minimizing page faults. However, it is impractical for real-time use due to its need for future knowledge of page references. The FIFO and LRU algorithms are practical and have their own use cases depending on the workload characteristics.