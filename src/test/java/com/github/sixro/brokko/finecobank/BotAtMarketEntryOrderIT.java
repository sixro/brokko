package com.github.sixro.brokko.finecobank;

import com.github.sixro.brokko.finecobank.credentials.SystemEnvCredentials;
import com.github.sixro.brokko.finecobank.pincode.SystemEnvPinCode;
import com.github.sixro.brokko.util.selenium.SeleniumBot;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class BotAtMarketEntryOrderIT {

    public static final WebDriver WEB_DRIVER = WebDriverFactory.chrome();

    @Before
    public void setup() {
        String userEnv = System.getenv("FB_USER");
        String pwdEnv = System.getenv("FB_PASSWORD");
        String pinCode = System.getenv("FB_PIN_CODE");
        Assume.assumeNotNull(userEnv, pwdEnv, pinCode);

        LoggedIn loggedIn = new BotLoggedIn(
            new SeleniumBot(WEB_DRIVER),
            new SystemEnvCredentials("FB_USER", "FB_PASSWORD")
        );
        loggedIn.ensure();
    }

    @Ignore("Re-enable when the cancel operation will be available")
    @Test public void enter() {
        BotAtMarketEntryOrder eorder = new BotAtMarketEntryOrder(
            new SeleniumBot(WEB_DRIVER),
            "FR0010010827",
            "AFF",
            1,
            new SystemEnvPinCode("FB_PIN_CODE")
        );
        eorder.enter();
    }

}