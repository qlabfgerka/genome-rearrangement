package inc.premzl.Lists;

import inc.premzl.Models.Item.Item;

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

    public static void printSequences(List<Item> list) {
        for (int i = 0; i < list.size(); i++) {
            if (i > 0 && list.get(i).getStrip() != list.get(i - 1).getStrip())
                System.out.print("(" + list.get(i - 1).getOrder() + ") | ");
            System.out.print(list.get(i).getValue() + " ");
        }
        System.out.println();
    }
}
