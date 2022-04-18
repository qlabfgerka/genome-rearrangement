package inc.premzl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static inc.premzl.Files.FileOperations.readFile;
import static inc.premzl.Sorts.BreakpointReversalSort.breakpointReversalSort;
import static inc.premzl.Sorts.SimpleReversalSort.simpleReversalSort;

public class GenomeRearrangement {
    public static void main(String[] args) throws Exception {
        if (args.length != 2) throw new Exception("Invalid arguments size");

        List<Integer> unsorted = init(args[1]);
        int max = unsorted.size() - 1;
        List<Integer> sorted = new ArrayList<>(new ArrayList<>(IntStream.rangeClosed(0, max).boxed().toList()));

        if (Objects.equals(args[0], "simple"))
            System.out.printf("ROTATIONS: %d", simpleReversalSort(unsorted, sorted, max));
        else if (Objects.equals(args[0], "bp"))
            System.out.printf("ROTATIONS: %d", breakpointReversalSort(unsorted, false));
        else if (Objects.equals(args[0], "custom"))
            System.out.printf("ROTATIONS: %d", breakpointReversalSort(unsorted, true));
        else if (Objects.equals(args[0], "test")) {
            long avgTime, start, end;
            int iterations = 1000;
            for (int opt = 0; opt < 3; opt++) {
                avgTime = 0;
                for (int i = 0; i < iterations; i++) {
                    start = System.nanoTime();
                    if (opt == 0) simpleReversalSort(unsorted, sorted, max);
                    else breakpointReversalSort(unsorted, opt != 1);
                    end = System.nanoTime();
                    avgTime += (end - start);

                    unsorted = init(args[1]);
                }
                System.out.println((double) TimeUnit.MILLISECONDS.convert(avgTime, TimeUnit.NANOSECONDS) / iterations);
            }
        } else throw new Exception("Invalid algorithm argument");
    }

    private static List<Integer> init(String filename) throws IOException {
        List<Integer> unsorted = readFile(filename);
        int max = Collections.max(unsorted) + 1;
        unsorted.add(0, 0);
        unsorted.add(max);
        return unsorted;
    }
}
