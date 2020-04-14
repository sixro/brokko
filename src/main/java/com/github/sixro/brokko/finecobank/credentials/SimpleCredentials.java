package com.github.sixro.brokko.finecobank.credentials;

import com.github.sixro.brokko.finecobank.Credentials;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * Represents a simple implementation of credentials.
 *
 * @author <a href="mailto:me@sixro.net" >Sixro</a>
 * @since 1.0
 */
public final class SimpleCredentials implements Credentials {

    private final String username;
    private final byte[] password;

    /**
     * Create simple credentials using specified username and password.
     *
     * @param username the username
     * @param password the password to use specified as text
     */
    public SimpleCredentials(String username, String password) {
        this(username, password.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Create simple credentials using specified username and password.
     *
     * @param username the username
     * @param password the password
     */
    public SimpleCredentials(String username, byte[] password) {
        this.username = username;
        this.password = Arrays.copyOf(password, password.length);
    }

    @Override
    public String username() {
        return username;
    }

    @Override
    public byte[] password() {
        return Arrays.copyOf(password, password.length);
    }

}
