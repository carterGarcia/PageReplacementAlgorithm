package ReplacementAlgorithm;

import java.util.*;

public class OPT extends ReplacementAlgorithm {
    private List<Integer> pages;
    private Set<Integer> currentPages;
    private int currentIndex;

    public OPT(int pageFrameCount, List<Integer> pages) {
        super(pageFrameCount);
        this.pages = pages;
        this.currentPages = new HashSet<>();
        this.currentIndex = 0;
    }

    @Override
    public void insert(int pageNumber) {
        if (!currentPages.contains(pageNumber)) {
            pageFaultCount++;

            if (currentPages.size() < pageFrameCount) {
                currentPages.add(pageNumber);
            } else {
                int pageToReplace = findPageToReplace(currentPages);
                currentPages.remove(pageToReplace);
                currentPages.add(pageNumber);
            }
        }
        currentIndex++;
    }

    private int findPageToReplace(Set<Integer> currentPages) {
        int pageToReplace = -1;
        int furthestIndex = -1;

        for (int page : currentPages) {
            int nextUse = findNextUse(page);
            if (nextUse == -1) {
                return page;
            } else if (nextUse > furthestIndex) {
                furthestIndex = nextUse;
                pageToReplace = page;
            }
        }

        return pageToReplace;
    }

    private int findNextUse(int page) {
        for (int i = currentIndex; i < pages.size(); i++) {
            if (pages.get(i) == page) {
                return i;
            }
        }
        return -1;
    }
}
