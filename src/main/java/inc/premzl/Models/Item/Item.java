package inc.premzl.Models.Item;

public class Item implements Comparable<Item> {
    private int value;
    private int strip;
    private String order;

    public Item(int value, int strip, String order) {
        this.value = value;
        this.strip = strip;
        this.order = order;
    }

    public int getStrip() {
        return strip;
    }

    public void setStrip(int strip) {
        this.strip = strip;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
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
