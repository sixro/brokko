package com.github.sixro.brokko.finecobank.credentials;

import com.github.sixro.brokko.finecobank.Credentials;

import java.nio.charset.StandardCharsets;

/**
 * Represents credentials found in environment variables.
 *
 * @author <a href="mailto:me@sixro.net" >Sixro</a>
 * @since 1.0
 */
public final class SystemEnvCredentials implements Credentials {

    private final String userEnvVariable;
    private final String pwdEnvVariable;

    /**
     * Create credentials based on the specified environment variable.
     *
     * @param userEnvVariable an environment variable for user
     * @param pwdEnvVariable an environment variable for password
     */
    public SystemEnvCredentials(String userEnvVariable, String pwdEnvVariable) {
        this.userEnvVariable = userEnvVariable;
        this.pwdEnvVariable = pwdEnvVariable;
    }

    @SuppressWarnings("PMD.LawOfDemeter")
    @Override
    public String username() {
        return System.getenv(userEnvVariable);
    }

    @SuppressWarnings("PMD.LawOfDemeter")
    @Override
    public byte[] password() {
        String text = System.getenv(pwdEnvVariable);
        return text.getBytes(StandardCharsets.UTF_8);
    }
}
