package inc.premzl.Lists;

import java.util.List;
import java.util.Objects;

public class ListOperations {
    public static boolean listEquals(List<Integer> first, List<Integer> second) {
        if (first.size() != second.size()) return false;
        for (int i = 0; i < first.size(); i++)
            if (!Objects.equals(first.get(i), second.get(i))) return false;
        return true;
    }

    public static void printList(List<Integer> list) {
        for (Integer item : list) {
            System.out.print(item + " ");
        }
        System.out.println();
    }
}
