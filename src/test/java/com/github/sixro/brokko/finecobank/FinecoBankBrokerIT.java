package com.github.sixro.brokko.finecobank;

import com.github.sixro.brokko.finecobank.credentials.SystemEnvCredentials;
import com.github.sixro.brokko.webuser.selenium.SeleniumWebUser;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FinecoBankBrokerIT {

    @Before
    public void setup() {
        String userEnv = System.getenv("FB_USER");
        String pwdEnv = System.getenv("FB_PASSWORD");
        Assume.assumeNotNull(userEnv);
        Assume.assumeNotNull(pwdEnv);
    }

    @Test
    public void able_to_logout_after_a_login() {
        try (FinecoBankBroker fb = newFinecoBankBroker()) {
            Assert.assertFalse(fb.loggedIn());

            fb.login();
            Assert.assertTrue(fb.loggedIn());

            fb.logout();
            Assert.assertFalse(fb.loggedIn());
        }
    }

    private FinecoBankBroker newFinecoBankBroker() {
        Credentials credentials = new SystemEnvCredentials("FB_USER", "FB_PASSWORD");
        WebDriverManager.chromedriver().setup();
        ChromeDriver chromeDriver = new ChromeDriver(newChromeOptions(true));
        return new FinecoBankBroker(credentials, new SeleniumWebUser(chromeDriver, new WebDriverWait(chromeDriver, 10)));
    }

    private static ChromeOptions newChromeOptions(boolean showUI) {
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(showUI);
        return options;
    }

}
