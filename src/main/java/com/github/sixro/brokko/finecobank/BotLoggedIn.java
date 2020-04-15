package com.github.sixro.brokko.finecobank;

import com.github.sixro.brokko.bot.Bot;
import org.openqa.selenium.By;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a LoggedIn based on a bot.
 *
 * @author <a href="mailto:me@sixro.net" >Sixro</a>
 * @since 1.0
 */
final class BotLoggedIn implements LoggedIn {

    private static final String LOGIN_PAGE =
            "https://finecobank.com/it/online/login/";
    private static final By LOGIN_USERNAME = By.xpath("//*[@id=\"LOGIN\"]");
    private static final By LOGIN_PASSWORD = By.xpath("//*[@id=\"PASSWD\"]");
    private static final By LOGIN_BUTTON =
            By.xpath("//*[@id=\"login\"]/button");
    private static final By LOGOUT_BUTTON =
            By.xpath("//a[contains(@href, '/logout')]");

    private final Bot bot;
    private final Credentials credentials;

    /**
     * Create a BotLoggedIn based on specified bot and credentials.
     *
     * @param bot         a bot
     * @param credentials some credentials
     */
    BotLoggedIn(Bot bot, Credentials credentials) {
        this.bot = bot;
        this.credentials = credentials;
    }

    @Override
    public void assure() {
        try {
            if (bot.see(LOGOUT_BUTTON))
                return;

            bot.open(LOGIN_PAGE);
            bot.waitFor(LOGIN_BUTTON);

            bot.fill(credentialsForm());

            bot.click(LOGIN_BUTTON);

            bot.waitFor(LOGOUT_BUTTON);
        } catch (Exception e) {
            String msg = "Unable to login due to unexpected error";
            throw new LoginFailureException(msg, e);
        }

        if (!bot.see(LOGOUT_BUTTON)) {
            String msg = "Unable to login using specified credentials";
            throw new LoginFailureException(msg);
        }
    }

    private Map<By, String> credentialsForm() {
        Map<By, String> inputs = new HashMap<>();
        inputs.put(LOGIN_USERNAME, credentials.username());
        inputs.put(LOGIN_PASSWORD, asString(credentials.password()));
        return inputs;
    }

    private String asString(byte[] bytes) {
        try {
            return new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 not supported", e);
        }
    }

}
