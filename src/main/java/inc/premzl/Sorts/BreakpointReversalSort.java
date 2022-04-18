package inc.premzl.Sorts;

import inc.premzl.Models.Item.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static inc.premzl.Lists.ListOperations.printSequences;

public class BreakpointReversalSort {
    public static int breakpointReversalSort(List<Integer> unsorted, boolean custom) {
        List<Item> sequences = getSequences(unsorted);
        int min, prev, rotations = 0, breakpoints = getBreakpoints((sequences));

        while (breakpoints > 0) {
            if (!custom) min = minInDescending(sequences);
            else min = maxInDescending(sequences);
            if (min != -1) {
                prev = findInSequence(sequences, custom ? min + 1 : min - 1);
                min = findInSequence(sequences, min);
                if (!custom)
                    Collections.reverse(min > prev ? sequences.subList(prev + 1, min + 1) : sequences.subList(min + 1, prev + 1));
                else
                    Collections.reverse(min > prev ? sequences.subList(prev, min) : sequences.subList(min, prev));
                breakpoints -= updateIndexes(sequences, Math.max(min, prev), Math.min(min, prev), custom);

            } else breakpoints -= flipFirst(sequences, custom);
            ++rotations;
        }

        printSequences(sequences);
        return rotations;
    }

    public static List<Item> getSequences(List<Integer> list) {
        List<Item> items = new ArrayList<>();
        Item item;
        int stripIndex = 0;

        list.add(Integer.MAX_VALUE);

        for (int i = 0; i < list.size() - 1; i++) {
            item = new Item(list.get(i), stripIndex, "none");
            if (i > 0) {
                if (list.get(i) == list.get(i - 1) + 1 || list.get(i) == list.get(i - 1) - 1) {
                    items.get(i - 1).setOrder(list.get(i) == list.get(i - 1) + 1 ? "asc" : "desc");
                    item.setOrder(list.get(i) == list.get(i - 1) + 1 ? "asc" : "desc");
                    stripIndex = items.get(i - 1).getStrip();
                    item.setStrip(stripIndex);
                }
            }
            stripIndex++;
            items.add(item);
        }

        return items;
    }

    public static int minInDescending(List<Item> sequences) {
        int min = Integer.MAX_VALUE;

        List<Item> filtered = sequences.stream().filter(sequence -> Objects.equals(sequence.getOrder(),
                "desc")).toList();

        min = Math.min(filtered.size() != 0 ?
                Collections.min(filtered).getValue() : Integer.MAX_VALUE, min);

        return min != Integer.MAX_VALUE ? min : -1;
    }

    public static int maxInDescending(List<Item> sequences) {
        int max = Integer.MIN_VALUE;

        List<Item> filtered = sequences.stream().filter(sequence -> Objects.equals(sequence.getOrder(),
                "desc")).toList();

        max = Math.max(filtered.size() != 0 ?
                Collections.max(filtered).getValue() : Integer.MIN_VALUE, max);

        return max != Integer.MIN_VALUE ? max : -1;
    }

    public static int getBreakpoints(List<Item> sequences) {
        int breakpoints = 0;
        for (int i = 1; i < sequences.size(); i++)
            if (!Objects.equals(sequences.get(i).getStrip(), sequences.get(i - 1).getStrip())) breakpoints++;
        return breakpoints;
    }

    public static int findInSequence(List<Item> sequence, int value) {
        for (int i = 0; i < sequence.size(); i++)
            if (sequence.get(i).getValue() == value) return i;
        return -1;
    }

    public static int getFirstInStrip(List<Item> sequence, int strip) {
        for (int i = 0; i < sequence.size(); i++)
            if (sequence.get(i).getStrip() == strip) return i;
        return -1;
    }

    public static int getLastInStrip(List<Item> sequence, int strip) {
        for (int i = sequence.size() - 1; i >= 0; i--)
            if (sequence.get(i).getStrip() == strip) return i;
        return -1;
    }

    public static int flipFirst(List<Item> sequence, boolean custom) {
        int start = -1, end = -1, removed = 0;
        for (int i = 0; i < sequence.size(); i++) {
            if (sequence.get(i).getStrip() == sequence.get(findInSequence(sequence, 0)).getStrip()) continue;
            if (Objects.equals(sequence.get(i).getOrder(), "asc") && start == -1) start = i;
            if (start != -1 && sequence.get(i).getStrip() != sequence.get(start).getStrip()) {
                end = i;
                break;
            }
        }

        if (start == -1 || end == -1) {
            for (int i = 0; i < sequence.size(); i++) {
                if (sequence.get(i).getStrip() == sequence.get(findInSequence(sequence, 0)).getStrip()) continue;
                if (Objects.equals(sequence.get(i).getOrder(), "none") && start == -1) {
                    start = i;
                    end = findInSequence(sequence, sequence.get(start).getValue() + 1);
                    break;
                }
            }
        }

        if (sequence.get(start).getValue() == sequence.get(end).getValue() + 1 ||
                sequence.get(start).getValue() == sequence.get(end).getValue() - 1) removed++;

        if (sequence.get(start - 1).getValue() == sequence.get(end - 1).getValue() + 1 ||
                sequence.get(start - 1).getValue() == sequence.get(end - 1).getValue() - 1) removed++;

        Collections.reverse(sequence.subList(start, end));

        updateIndexes(sequence, end, start, custom);

        return removed;
    }

    public static int updateIndexes(List<Item> sequence, int min, int prev, boolean custom) {
        if (min + 1 >= sequence.size()) min--;
        int start = getFirstInStrip(sequence, sequence.get(prev).getStrip()),
                end = getLastInStrip(sequence, sequence.get(min + 1).getStrip());
        int removed = 1;

        for (int i = start; i <= end; i++) {
            if (i != 0 && (sequence.get(i).getValue() == sequence.get(i - 1).getValue() + 1 ||
                    sequence.get(i).getValue() == sequence.get(i - 1).getValue() - 1)) {
                sequence.get(i).setOrder(sequence.get(i).getValue() == sequence.get(i - 1).getValue() + 1 ? "asc" : "desc");
                sequence.get(i - 1).setOrder(sequence.get(i).getValue() == sequence.get(i - 1).getValue() + 1 ? "asc" : "desc");
                sequence.get(i).setStrip(sequence.get(i - 1).getStrip());
                if (!custom && (sequence.get(Math.max(min, prev)).getValue() == sequence.get(Math.max(min, prev) + 1).getValue() + 1 ||
                        sequence.get(Math.max(min, prev)).getValue() == sequence.get(Math.max(min, prev) + 1).getValue() - 1) &&
                        removed != 2)
                    removed = 2;
                else if (custom && (sequence.get(Math.min(min, prev) - 1).getValue() == sequence.get(Math.min(min, prev)).getValue() + 1 ||
                        sequence.get(Math.min(min, prev) - 1).getValue() == sequence.get(Math.min(min, prev)).getValue() - 1) &&
                        removed != 2)
                    removed = 2;
            }
        }

        return removed;
    }
}
