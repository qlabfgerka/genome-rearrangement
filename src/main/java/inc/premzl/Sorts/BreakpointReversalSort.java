package inc.premzl.Sorts;

import inc.premzl.Models.Item.Item;
import inc.premzl.Models.Sequence.Sequence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class BreakpointReversalSort {
    public static void breakpointReversalSort(List<Integer> unsorted) {
        List<Sequence> sequences = getSequences(unsorted);

        System.out.println("BREAKPOINTS: " + (sequences.size() - 1));
        System.out.println(minInDescending(sequences));

        for (Sequence sequence : sequences) {
            for (Item item : sequence.getItems()) {
                System.out.print(item.getValue() + " ");
            }
            System.out.print("(" + sequence.getOrder() + ") | ");
        }
        System.out.println();
    }

    public static List<Sequence> getSequences(List<Integer> list) {
        List<Sequence> sequences = new ArrayList<>();
        Sequence sequence = new Sequence();
        List<Item> items = new ArrayList<>();

        list.add(Integer.MAX_VALUE);

        for (int i = 1; i < list.size(); i++) {
            items.add(new Item(i - 1, list.get(i - 1)));
            if (list.get(i - 1) == list.get(i) + 1) {
                sequence.setOrder("desc");
            } else if (list.get(i - 1) == list.get(i) - 1) {
                sequence.setOrder("asc");
            } else {
                sequence.setItems(items);
                sequences.add(sequence);
                sequence = new Sequence();
                items = new ArrayList<>();
            }
        }

        return sequences;
    }

    public static int minInDescending(List<Sequence> sequences) {
        int min = Integer.MAX_VALUE;

        for (Sequence sequence : sequences) {
            if (!Objects.equals(sequence.getOrder(), "desc")) continue;

            min = Math.min(Collections.min(sequence.getItems()).getValue(), min);
        }

        return min;
    }
}
