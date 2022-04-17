package inc.premzl.Sorts;

import java.util.Collections;
import java.util.List;

import static inc.premzl.Lists.ListOperations.listEquals;
import static inc.premzl.Lists.ListOperations.printList;

public class SimpleReversalSort {
    public static List<Integer> simpleReversalSort(List<Integer> unsorted,
                                                   List<Integer> sorted,
                                                   int max) throws Exception {
        int j;
        for (int i = 0; i <= max; i++) {
            j = unsorted.indexOf(i);
            if (i != j) {
                Collections.reverse(i > j ? unsorted.subList(j, i + 1) : unsorted.subList(i, j + 1));
                printList(unsorted);
            }
            if (listEquals(unsorted, sorted)) return unsorted;
        }

        throw new Exception("Unable to sort array!");
    }
}
