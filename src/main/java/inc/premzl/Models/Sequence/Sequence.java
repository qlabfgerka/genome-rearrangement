package inc.premzl.Models.Sequence;

import inc.premzl.Models.Item.Item;

import java.util.ArrayList;
import java.util.List;

public class Sequence {
    private List<Item> items;
    private String order;

    public Sequence() {
        items = new ArrayList<>();
        order = "none";
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
