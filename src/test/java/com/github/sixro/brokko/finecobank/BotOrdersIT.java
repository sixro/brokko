package com.github.sixro.brokko.finecobank;

import com.github.sixro.brokko.bot.selenium.SeleniumBot;
import com.github.sixro.brokko.finecobank.credentials.SystemEnvCredentials;
import org.junit.AfterClass;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

public class BotOrdersIT {

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

    @Test
    public void iterable() {
        BotOrders orders = new BotOrders(WEB_DRIVER);
        assertTrue(orders.iterator().hasNext());
    }

    @AfterClass
    public static void close() {
        WEB_DRIVER.close();
    }

}
