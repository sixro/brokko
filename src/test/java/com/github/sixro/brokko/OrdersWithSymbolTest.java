package com.github.sixro.brokko;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class OrdersWithSymbolTest {

    @Test public void with_symbol() {
        Orders orders = new Orders.WithSymbol(
            "ENI.MI",
            new ListOrders(
                Arrays.asList(
                    new AnemicOrder("ENI.MI", Order.Status.EXECUTED),
                    new AnemicOrder("ENEL.MI", Order.Status.REFUSED)
                )
            )
        );
        Assert.assertEquals(Order.Status.EXECUTED,
            orders.iterator().next().status());
    }

}
