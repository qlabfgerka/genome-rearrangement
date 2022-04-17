package inc.premzl.Models.Item;

public class Item implements Comparable<Item> {
    private int index;
    private int value;

    public Item(int index, int value) {
        this.index = index;
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(Item o) {
        return Integer.compare(this.value, o.value);
    }
}
