package com.github.sixro.brokko.finecobank;

import com.github.sixro.brokko.EntryOrder;
import com.github.sixro.brokko.util.Bot;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Collections;

class BotAtMarketEntryOrder implements EntryOrder {

    private static final String ENTER_TPL_URL =
        "https://finecobank" + ".com" + "/mercati-e-trading/ordini/azioni" +
            "/inserimento-ordine?instrId=%s" + "&venueSystem=%s";
    private static final By ENTER_ORDER_FORM = By.xpath("//form[contains" +
        "(@class, 'order-confirm')]");
    private static final By ENTER_ORDER_CONTINUE =
        By.xpath("//form[contains" + "(@class, 'order-confirm')" +
            "]//input[@type='submit']");
    private static final By ENTER_ORDER_PIN = By.xpath("//input[@id='PIN']");
    private static final By ENTER_ORDER_PIN_CONFIRM = By.xpath("//div[@id" +
        "='row-pin']//input[@type='submit']");
    private static final By ENTER_ORDER_QUANTITY = By.xpath(
    "//input[@id='orderSize']");
    private static final int ENOUGH_PIXELS_TO_SEE_CONTINUE_BUTTON = 250;

    private final Bot bot;
    private final String symbol;
    private final String market;
    private final int quantity;
    private final PinCode pinCode;

    BotAtMarketEntryOrder(Bot bot, String symbol, String market,
                                 int quantity, PinCode pinCode) {
        this.bot = bot;
        this.symbol = symbol;
        this.market = market;
        this.quantity = quantity;
        this.pinCode = pinCode;
    }

    @Override
    public void enter() {
        String url = String.format(ENTER_TPL_URL, symbol, market);
        bot.open(url,
            ExpectedConditions.visibilityOfElementLocated(ENTER_ORDER_FORM));

        bot.fill(Collections.singletonMap(ENTER_ORDER_QUANTITY,
            Integer.toString(quantity)));

        bot.scrollDown(ENOUGH_PIXELS_TO_SEE_CONTINUE_BUTTON);
        bot.click(ENTER_ORDER_CONTINUE);

        bot.waitFor(ENTER_ORDER_PIN);
        bot.fill(Collections.singletonMap(ENTER_ORDER_PIN, pinCode.code()));
        bot.click(ENTER_ORDER_PIN_CONFIRM);
    }

}
