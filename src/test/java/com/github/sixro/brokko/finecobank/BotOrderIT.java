package com.github.sixro.brokko.finecobank;

import com.github.sixro.brokko.Order;
import com.github.sixro.brokko.bot.selenium.SeleniumBot;
import com.github.sixro.brokko.finecobank.credentials.SystemEnvCredentials;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.Iterator;

import static org.junit.Assert.*;

public class BotOrderIT {

    public static final WebDriver WEB_DRIVER = WebDriverFactory.chrome();

    @Before
    public void setup() {
        String userEnv = System.getenv("FB_USER");
        String pwdEnv = System.getenv("FB_PASSWORD");
        Assume.assumeNotNull(userEnv);
        Assume.assumeNotNull(pwdEnv);

        LoggedIn loggedIn = new BotLoggedIn(new SeleniumBot(WEB_DRIVER), new SystemEnvCredentials("FB_USER", "FB_PASSWORD"));
        loggedIn.assure();
    }

    @Test public void symbol() {
        Iterator<Order> iterator = new BotOrders(WEB_DRIVER).iterator();
        Assume.assumeTrue(iterator.hasNext());

        Order order = iterator.next();
        assertTrue(! order.symbol().isEmpty());
    }

    @Test public void executed() {
        Iterator<Order> iterator = new BotOrders(WEB_DRIVER).iterator();
        Assume.assumeTrue(iterator.hasNext());

        Order order = iterator.next();
        assertTrue(order.executed());
    }

    @Test public void refused() {
        Iterator<Order> iterator = new BotOrders(WEB_DRIVER).iterator();
        Assume.assumeTrue(iterator.hasNext());

        Order order = iterator.next();
        assertFalse(order.refused());
    }

}