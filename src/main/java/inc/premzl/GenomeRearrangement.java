package inc.premzl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import static inc.premzl.Files.FileOperations.readFile;
import static inc.premzl.Sorts.SimpleReversalSort.simpleReversalSort;

public class GenomeRearrangement {
    public static void main(String[] args) throws Exception {
        if (args.length != 2) throw new Exception("Invalid arguments size");

        List<Integer> unsorted = readFile(args[1]);
        int max = Collections.max(unsorted) + 1;
        unsorted.add(0, 0);
        unsorted.add(max);
        List<Integer> sorted = new ArrayList<>(new ArrayList<>(IntStream.rangeClosed(0, max).boxed().toList()));

        if (Objects.equals(args[0], "simple"))
            simpleReversalSort(unsorted, sorted, max);
        //else if (args[0] == "bp")
        //
    }
}
