package com.github.sixro.brokko.finecobank.credentials;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class SystemEnvCredentialsIT {

    @Before
    public void setup() {
        String envUser = System.getenv("ENV_USER");
        String envPwd = System.getenv("ENV_PASSWORD");
        Assume.assumeNotNull(envUser);
        Assume.assumeNotNull(envPwd);
    }

    @Test
    public void provide_credentials() {
        SystemEnvCredentials credentials =
            new SystemEnvCredentials("ENV_USER", "ENV_PASSWORD");
        assertEquals("user", credentials.username());
        assertArrayEquals(asBytes("passwd"), credentials.password());
    }

    private byte[] asBytes(String text) {
        return text.getBytes(StandardCharsets.UTF_8);
    }

}