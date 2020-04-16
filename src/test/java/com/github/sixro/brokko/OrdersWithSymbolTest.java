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
                    AnemicOrder.executed("ENI.MI"),
                    AnemicOrder.refused("ENEL.MI")
                )
            )
        );
        Assert.assertTrue(orders.iterator().next().executed());
    }

}
