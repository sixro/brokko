package com.github.sixro.brokko;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class OrdersExecutedTest {

    @Test public void executed() {
        Orders orders = new Orders.Executed(
            new ListOrders(
                Arrays.asList(
                    AnemicOrder.executed("ENI.MI"),
                    AnemicOrder.refused("ENEL.MI")
                )
            )
        );
        Assert.assertEquals("ENI.MI", orders.iterator().next().symbol());
    }

    @Test public void none() {
        Orders orders = new Orders.Executed(
            new ListOrders(
                Arrays.asList(
                    AnemicOrder.pending("ENI.MI"),
                    AnemicOrder.refused("ENEL.MI")
                )
            )
        );
        Assert.assertFalse(orders.iterator().hasNext());
    }

}
