package com.github.sixro.brokko.finecobank.pincode;

import com.github.sixro.brokko.finecobank.PinCode;

/**
 * Represents a {@code PinCode} found in an environment variable.
 *
 * @author <a href="mailto:me@sixro.net" >Sixro</a>
 * @since 1.0
 */
public final class SystemEnvPinCode implements PinCode {

    private final String pinEnvVariable;

    /**
     * Create this object using specified environment variable.
     *
     * @param pinEnvVariable environment variable to read
     */
    public SystemEnvPinCode(String pinEnvVariable) {
        this.pinEnvVariable = pinEnvVariable;
    }

    @Override
    public String code() {
        return System.getenv(pinEnvVariable);
    }

}
