package com.github.sixro.brokko.finecobank;

/**
 * Represents credentials to use to log into an account in FinecoBank.
 *
 * @author <a href="mailto:me@sixro.net" >Sixro</a>
 * @since 1.0
 */
public interface Credentials {

    /**
     * Returns the username to use.
     *
     * @return a username
     */
    String username();

    /**
     * Returns the password to use.
     *
     * @return a password
     */
    byte[] password();

}
