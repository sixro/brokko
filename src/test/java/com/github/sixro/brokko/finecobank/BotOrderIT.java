package com.github.sixro.brokko.finecobank;

import com.github.sixro.brokko.Order;
import com.github.sixro.brokko.Orders;
import com.github.sixro.brokko.finecobank.credentials.SystemEnvCredentials;
import com.github.sixro.brokko.util.selenium.SeleniumBot;
import org.junit.AfterClass;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class BotOrderIT {

    public static final WebDriver WEB_DRIVER = WebDriverFactory.chrome();

    @Before
    public void setup() {
        String userEnv = System.getenv("FB_USER");
        String pwdEnv = System.getenv("FB_PASSWORD");
        Assume.assumeNotNull(userEnv);
        Assume.assumeNotNull(pwdEnv);

        LoggedIn loggedIn = new BotLoggedIn(
            new SeleniumBot(WEB_DRIVER),
            new SystemEnvCredentials("FB_USER", "FB_PASSWORD")
        );
        loggedIn.ensure();
    }

    @Test public void symbol() {
        Iterator<Order> iterator = new BotOrders(WEB_DRIVER).iterator();
        Assume.assumeTrue(iterator.hasNext());

        Order order = iterator.next();
        assertFalse(order.symbol().isEmpty());
    }

    @Test public void executed() {
        Orders.WithStatus orders =
            new Orders.WithStatus(Order.Status.EXECUTED,
                new BotOrders(WEB_DRIVER));
        Iterator<Order> iterator = orders.iterator();
        Assume.assumeTrue(iterator.hasNext());

        Order order = iterator.next();
        assertEquals(Order.Status.EXECUTED, order.status());
    }

    @Test public void refused() {
        Orders.WithStatus orders =
            new Orders.WithStatus(Order.Status.REFUSED,
                new BotOrders(WEB_DRIVER));
        Iterator<Order> iterator = orders.iterator();
        Assume.assumeTrue(iterator.hasNext());

        Order order = iterator.next();
        assertEquals(Order.Status.REFUSED, order.status());
    }

    @Test public void pending() {
        Orders.WithStatus orders =
            new Orders.WithStatus(Order.Status.PENDING,
                new BotOrders(WEB_DRIVER));
        Iterator<Order> iterator = orders.iterator();
        Assume.assumeTrue(iterator.hasNext());

        Order order = iterator.next();
        assertEquals(Order.Status.PENDING, order.status());
    }

    @AfterClass
    public static void close() {
        WEB_DRIVER.close();
    }

}