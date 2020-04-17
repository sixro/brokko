package com.github.sixro.brokko.finecobank;

import com.github.sixro.brokko.Position;
import com.github.sixro.brokko.Positions;
import com.github.sixro.brokko.bot.selenium.SeleniumBot;
import org.openqa.selenium.WebDriver;

import java.util.Iterator;

/**
 * Represents positions on
 * <a href="https://finecobank.com">FinecoBank S.p.a.</a>.
 *
 * @author <a href="mailto:me@sixro.net" >Sixro</a>
 * @since 1.0
 */
public final class FinecoBankPositions implements Positions {

    private final LoggedIn loggedIn;
    private final Positions delegate;

    /**
     * Create this object using specified web driver and credentials.
     *
     * @param webDriver a web driver
     * @param credentials some credentials
     */
    public FinecoBankPositions(WebDriver webDriver, Credentials credentials) {
        this(
            new BotLoggedIn(
                new SeleniumBot(webDriver),
                credentials
            ),
            new BotPositions(webDriver)
        );
    }

    FinecoBankPositions(LoggedIn loggedIn, Positions delegate) {

        this.loggedIn = loggedIn;
        this.delegate = delegate;
    }

    @Override
    public Iterator<Position> iterator() {
        loggedIn.ensure();
        return delegate.iterator();
    }

}
