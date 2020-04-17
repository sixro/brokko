package com.github.sixro.brokko.finecobank;

import com.github.sixro.brokko.Position;
import com.github.sixro.brokko.bot.selenium.SeleniumBot;
import com.github.sixro.brokko.finecobank.credentials.SystemEnvCredentials;
import org.junit.AfterClass;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.Iterator;

import static org.junit.Assert.assertFalse;

public class BotPositionIT {

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
    public void symbol() {
        Iterator<Position> iterator = new BotPositions(WEB_DRIVER).iterator();
        Assume.assumeTrue(iterator.hasNext());

        Position position = iterator.next();
        assertFalse(position.symbol().isEmpty());
    }

    @AfterClass
    public static void close() {
        WEB_DRIVER.close();
    }

}