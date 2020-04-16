package com.github.sixro.brokko;

import java.util.Iterator;
import java.util.List;

public class ListOrders implements Orders {

    private final List<Order> list;

    public ListOrders(List<Order> list) {
        this.list = list;
    }

    @Override
    public Iterator<Order> iterator() {
        return list.iterator();
    }

}
