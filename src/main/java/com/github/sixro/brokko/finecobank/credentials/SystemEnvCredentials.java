package com.github.sixro.brokko.finecobank.credentials;

import com.github.sixro.brokko.finecobank.Credentials;

import java.nio.charset.StandardCharsets;

/**
 * Represents credentials found in a environment variable.
 *
 * <p>
 * The format is {@code <username>/<password>}.<br>
 * E.g.
 * </p>
 * <pre>
 *     FINECOBANK_CREDENTIALS=myuser/mypwd
 * </pre>
 *
 * @author <a href="mailto:me@sixro.net" >Sixro</a>
 * @since 1.0
 */
public final class SystemEnvCredentials implements Credentials {

    private final String environmentVariable;

    /**
     * Create credentials based on the specified environment variable.
     *
     * @param environmentVariable an environment variable
     */
    public SystemEnvCredentials(String environmentVariable) {
        this.environmentVariable = environmentVariable;
    }

    @SuppressWarnings("PMD.LawOfDemeter")
    @Override
    public String username() {
        String text = System.getenv(environmentVariable);
        return text.substring(0, text.indexOf("/"));
    }

    @SuppressWarnings("PMD.LawOfDemeter")
    @Override
    public byte[] password() {
        String text = System.getenv(environmentVariable);
        String pwdAsText = text.substring(text.indexOf("/") + 1);
        return pwdAsText.getBytes(StandardCharsets.UTF_8);
    }
}
