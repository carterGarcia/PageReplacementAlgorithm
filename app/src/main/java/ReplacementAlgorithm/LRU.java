package ReplacementAlgorithm;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRU extends ReplacementAlgorithm {
    private LinkedHashMap<Integer, Integer> cache;

    public LRU(int pageFrameCount) {
        super(pageFrameCount);
        //create a LinkedHashMap with access order set to true
        this.cache = new LinkedHashMap<>(pageFrameCount, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > pageFrameCount;
            }
        };
    }

    @Override
    //insert page into cache if page is not already in cache and increment pageFaultCount
    public void insert(int pageNumber) {
        if (!cache.containsKey(pageNumber)) {
            pageFaultCount++;
        }
        cache.put(pageNumber, pageNumber); //add new page to cache
    }
}
