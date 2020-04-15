package com.github.sixro.brokko.finecobank;

import com.github.sixro.brokko.bot.selenium.SeleniumBot;
import com.github.sixro.brokko.finecobank.credentials.SystemEnvCredentials;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class BotLoggedInIT {

    private static final WebDriver WEB_DRIVER = WebDriverFactory.chrome();

    private BotLoggedIn loggedIn;

    @Before
    public void setup() {
        String userEnv = System.getenv("FB_USER");
        String pwdEnv = System.getenv("FB_PASSWORD");
        Assume.assumeNotNull(userEnv);
        Assume.assumeNotNull(pwdEnv);

        loggedIn = new BotLoggedIn(
            new SeleniumBot(WEB_DRIVER),
            new SystemEnvCredentials("FB_USER", "FB_PASSWORD")
        );
    }

    @Test public void login() {
        loggedIn.assure();
    }

    @Test(expected = LoginFailureException.class)
    public void failure() {
        Credentials wrongCredentials =
            new SystemEnvCredentials("FB_USER", "FAILURE");

        BotLoggedIn loggedIn = new BotLoggedIn(
            new SeleniumBot(WEB_DRIVER),
                wrongCredentials
        );
        loggedIn.assure();
    }

}