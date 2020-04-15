package com.github.sixro.brokko.finecobank;

import com.github.sixro.brokko.finecobank.credentials.SystemEnvCredentials;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class FinecoBankBrokerIT {

    @Test public void works_as_expected() {
        FinecoBankBroker b = new FinecoBankBroker(
            WebDriverFactory.chrome(),
            new SystemEnvCredentials("FB_USER", "FB_PASSWORD")
        );
        assertFalse(b.orders().iterator().next().symbol().isEmpty());
    }

}