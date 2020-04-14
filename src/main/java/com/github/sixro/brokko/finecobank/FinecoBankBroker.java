package com.github.sixro.brokko.finecobank;

import com.github.sixro.brokko.Broker;
import com.github.sixro.brokko.EntryOrder;
import com.github.sixro.brokko.Orders;
import com.github.sixro.brokko.Positions;
import com.github.sixro.brokko.webuser.WebUser;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import java.io.UnsupportedEncodingException;

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

        webUser.insert(credentials.username(), LOGIN_USERNAME);

        try {
            String pwd = new String(credentials.password(), "UTF-8");
            webUser.insert(pwd, LOGIN_PASSWORD);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 not supported", e);
        }

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

}
