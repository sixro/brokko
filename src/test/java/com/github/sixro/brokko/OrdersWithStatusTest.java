package com.github.sixro.brokko;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class OrdersWithStatusTest {

    @Test public void executed() {
        Orders orders = new Orders.WithStatus(Order.Status.EXECUTED,
            new ListOrders(
                Arrays.asList(
                    new AnemicOrder("ENI.MI", Order.Status.EXECUTED),
                    new AnemicOrder("ENEL.MI", Order.Status.REFUSED)
                )
            )
        );
        Assert.assertEquals("ENI.MI", orders.iterator().next().symbol());
    }

    @Test public void none() {
        Orders orders = new Orders.WithStatus(Order.Status.EXECUTED,
            new ListOrders(
                Arrays.asList(
                    new AnemicOrder("ENI.MI", Order.Status.PENDING),
                    new AnemicOrder("ENEL.MI", Order.Status.REFUSED)
                )
            )
        );
        Assert.assertFalse(orders.iterator().hasNext());
    }

}
