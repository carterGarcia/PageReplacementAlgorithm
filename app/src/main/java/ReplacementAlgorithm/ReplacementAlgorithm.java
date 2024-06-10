package ReplacementAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class ReplacementAlgorithm {
    protected int pageFaultCount;
    protected int pageFrameCount;

    public ReplacementAlgorithm(int pageFrameCount) {
        if (pageFrameCount < 0) {
            throw new IllegalArgumentException();
        }

        this.pageFrameCount = pageFrameCount;
        this.pageFaultCount = 0;
    }

    public int getPageFaultCount() {
        return pageFaultCount;
    }

    public abstract void insert(int pageNumber);

    public void processReferenceString(List<Integer> referenceString) {
        for (int page : referenceString) {
            insert(page);
        }
    }

    public static List<Integer> generateReferenceString(int size) {
        List<Integer> referenceString = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            referenceString.add(random.nextInt(10));
        }
        return referenceString;
    }

    public static void main(String[] args) {
        int[] pageFrames = {3, 5, 7};

        for (int npf : pageFrames) {
            System.out.println("Testing with " + npf + " page frames.");
            for (int i = 0; i < 3; i++) {
                List<Integer> randomReferenceString = generateReferenceString(15);
                testAlgorithms(randomReferenceString, npf);
            }
        }
    }

    private static void testAlgorithms(List<Integer> referenceString, int pageFrameCount) {
        FIFO fifo = new FIFO(pageFrameCount);
        LRU lru = new LRU(pageFrameCount);
        OPT opt = new OPT(pageFrameCount, referenceString);

        fifo.processReferenceString(referenceString);
        lru.processReferenceString(referenceString);
        opt.processReferenceString(referenceString);

        System.out.println("Reference String: " + referenceString);
        System.out.println("Page Frames: " + pageFrameCount);
        System.out.println("FIFO Page Faults: " + fifo.getPageFaultCount());
        System.out.println("LRU Page Faults: " + lru.getPageFaultCount());
        System.out.println("OPT Page Faults: " + opt.getPageFaultCount());
        System.out.println();
    }
}
