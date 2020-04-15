package com.github.sixro.brokko.finecobank;

import com.github.sixro.brokko.finecobank.credentials.SystemEnvCredentials;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertFalse;

public class FinecoBankBrokerIT {

    public static final WebDriver WEB_DRIVER = WebDriverFactory.chrome();

    @Test public void works_as_expected() {
        FinecoBankBroker b = new FinecoBankBroker(
            WEB_DRIVER,
            new SystemEnvCredentials("FB_USER", "FB_PASSWORD")
        );
        assertFalse(b.orders().iterator().next().symbol().isEmpty());
    }

    @AfterClass
    public static void close() {
        WEB_DRIVER.close();
    }

}