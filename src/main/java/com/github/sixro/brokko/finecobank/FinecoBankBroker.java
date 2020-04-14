package com.github.sixro.brokko.finecobank;

import com.github.sixro.brokko.Broker;
import com.github.sixro.brokko.EntryOrder;
import com.github.sixro.brokko.Orders;
import com.github.sixro.brokko.Positions;
import com.github.sixro.brokko.webuser.WebUser;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents <a href="https://finecobank.com">FinecoBank S.p.a.</a> broker.
 *
 * @author <a href="mailto:me@sixro.net" >Sixro</a>
 * @since 1.0
 */
public final class FinecoBankBroker implements Broker, AutoCloseable {

    private static final String LOGIN_PAGE =
            "https://finecobank.com/it/online/login/";
    private static final By LOGIN_USERNAME = By.xpath("//*[@id=\"LOGIN\"]");
    private static final By LOGIN_PASSWORD = By.xpath("//*[@id=\"PASSWD\"]");
    private static final By LOGIN_BUTTON =
            By.xpath("//*[@id=\"login\"]/button");
    private static final By LOGOUT_BUTTON =
            By.xpath("//a[contains(@href, '/logout')]");

    private final Credentials credentials;
    private final WebUser webUser;

    /**
     * Create an instance using the specified credentials.
     *
     * @param credentials credentials to use
     * @param webUser a web user
     */
    public FinecoBankBroker(Credentials credentials, WebUser webUser) {
        this.credentials = credentials;
        this.webUser = webUser;
    }

    @Override
    public EntryOrder entryOrder() {
        return new DefaultEntryOrder();
    }

    @Override
    public Orders orders() {
        return null;
    }

    @Override
    public Positions positions() {
        return null;
    }

    void login() {
        webUser.open(LOGIN_PAGE);
        webUser.waitFor(LOGIN_BUTTON);

        Map<By, String> inputs = new HashMap<>();
        inputs.put(LOGIN_USERNAME, credentials.username());
        inputs.put(LOGIN_PASSWORD, asString(credentials.password()));
        webUser.fill(inputs);

        webUser.click(LOGIN_BUTTON);
        webUser.waitFor(LOGOUT_BUTTON);
    }

    boolean loggedIn() {
        try {
            return webUser.see(LOGOUT_BUTTON);
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    void logout() {
        webUser.click(LOGOUT_BUTTON);
    }

    @Override
    public void close() {
        webUser.leave();
    }

    private String asString(byte[] bytes) {
        try {
            return new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 not supported", e);
        }
    }

}
