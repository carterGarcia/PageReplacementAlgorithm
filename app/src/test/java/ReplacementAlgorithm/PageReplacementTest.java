package ReplacementAlgorithm;

import java.util.List;

public class PageReplacementTest {

    public static void main(String[] args) {
        List<List<Integer>> referenceStrings = List.of(
                List.of(6, 9, 5, 1, 7, 3, 8, 2, 5, 6, 5, 6, 8, 7, 9),
                List.of(3, 4, 1, 7, 6, 3, 6, 9, 0, 7, 2, 7, 2, 5, 1),
                List.of(8, 2, 5, 2, 0, 4, 5, 1, 2, 1, 9, 0, 0, 7, 0)
        );

        int pageFrameCount = 7;
        int[] expectedPageFaultsFIFO = {10, 9, 8};
        int[] expectedPageFaultsLRU = {10, 10, 8};
        int[] expectedPageFaultsOPT = {8, 9, 8};

        int totalPageFaultsFIFO = 0;
        int totalPageFaultsLRU = 0;
        int totalPageFaultsOPT = 0;

        for (int i = 0; i < referenceStrings.size(); i++) {
            List<Integer> referenceString = referenceStrings.get(i);
            System.out.println("Reference String: " + referenceString);
            System.out.println("Page Frames: " + pageFrameCount);
            totalPageFaultsFIFO += testAlgorithm(referenceString, pageFrameCount, "FIFO", expectedPageFaultsFIFO[i]);
            totalPageFaultsLRU += testAlgorithm(referenceString, pageFrameCount, "LRU", expectedPageFaultsLRU[i]);
            totalPageFaultsOPT += testAlgorithm(referenceString, pageFrameCount, "OPT", expectedPageFaultsOPT[i]);
            System.out.println();
        }

        System.out.println("Overall Performance:");
        System.out.println("FIFO Total Page Faults: " + totalPageFaultsFIFO);
        System.out.println("LRU Total Page Faults: " + totalPageFaultsLRU);
        System.out.println("OPT Total Page Faults: " + totalPageFaultsOPT);

        String bestAlgorithm = getBestAlgorithm(totalPageFaultsFIFO, totalPageFaultsLRU, totalPageFaultsOPT);
        System.out.println("Best Performing Algorithm: " + bestAlgorithm);

        if (!"OPT".equals(bestAlgorithm)) {
            System.err.println("Test failed: Expected OPT to perform the best");
        } else {
            System.out.println("Test passed: OPT performed the best");
        }
    }

    private static int testAlgorithm(List<Integer> referenceString, int pageFrameCount, String algorithmType, int expectedPageFaults) {
        ReplacementAlgorithm algorithm;
        switch (algorithmType) {
            case "FIFO":
                algorithm = new FIFO(pageFrameCount);
                break;
            case "LRU":
                algorithm = new LRU(pageFrameCount);
                break;
            case "OPT":
                algorithm = new OPT(pageFrameCount, referenceString);
                break;
            default:
                throw new IllegalArgumentException("Invalid algorithm type: " + algorithmType);
        }

        algorithm.processReferenceString(referenceString);
        int pageFaults = algorithm.getPageFaultCount();
        System.out.println(algorithmType + " Page Faults: " + pageFaults + (pageFaults == expectedPageFaults ? ", CORRECT" : ", INCORRECT"));
        if (pageFaults != expectedPageFaults) {
            System.err.println(algorithmType + " page faults do not match expected value. Expected: " + expectedPageFaults + ", Got: " + pageFaults);
        }
        return pageFaults;
    }

    private static String getBestAlgorithm(int totalPageFaultsFIFO, int totalPageFaultsLRU, int totalPageFaultsOPT) {
        if (totalPageFaultsOPT < totalPageFaultsFIFO && totalPageFaultsOPT < totalPageFaultsLRU) {
            return "OPT";
        } else if (totalPageFaultsLRU < totalPageFaultsFIFO) {
            return "LRU";
        } else {
            return "FIFO";
        }
    }
}