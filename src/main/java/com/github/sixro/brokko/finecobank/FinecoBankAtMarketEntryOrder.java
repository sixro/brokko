package com.github.sixro.brokko.finecobank;

import com.github.sixro.brokko.EntryOrder;
import com.github.sixro.brokko.util.Bot;
import com.github.sixro.brokko.util.selenium.SeleniumBot;
import org.openqa.selenium.WebDriver;

/**
 * Represents an entry order able to operate on
 * <a href="https://finecobank.com">FinecoBank S.p.a.</a>.
 *
 * @author <a href="mailto:me@sixro.net" >Sixro</a>
 * @since 1.0
 */
public final class FinecoBankAtMarketEntryOrder implements EntryOrder {

    private final LoggedIn loggedIn;
    private final EntryOrder delegate;

    /**
     * Create this object using specified params.
     *
     * @param webDriver   a web driver
     * @param credentials some credentials
     * @param symbol      an ISIN code
     * @param market      a market (for examle {@code AFF})
     * @param quantity    quantity to buy
     * @param pinCode     a pin code
     */
    public FinecoBankAtMarketEntryOrder(WebDriver webDriver,
                                        Credentials credentials,
                                        String symbol, String market,
                                        int quantity, PinCode pinCode) {
        this(new SeleniumBot(webDriver), credentials, symbol, market,
            quantity, pinCode);
    }

    FinecoBankAtMarketEntryOrder(Bot bot, Credentials credentials,
                                 String symbol, String market, int quantity,
                                 PinCode pinCode) {
        this(new BotLoggedIn(bot, credentials),
            new BotAtMarketEntryOrder(bot, symbol, market, quantity, pinCode));
    }

    FinecoBankAtMarketEntryOrder(LoggedIn loggedIn, EntryOrder delegate) {
        this.loggedIn = loggedIn;
        this.delegate = delegate;
    }

    @Override
    public void enter() {
        loggedIn.ensure();
        delegate.enter();
    }

}
