package com.github.sixro.brokko.finecobank.credentials;

import com.github.sixro.brokko.finecobank.Credentials;
import com.github.sixro.brokko.finecobank.FinecoBankBroker;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;

public class SystemEnvCredentialsIT {

    public static final String TEST_ENV = "TEST_ENV";

    @Before
    public void setup() {
        String credentialsAsText = System.getenv(TEST_ENV);
        Assume.assumeNotNull(credentialsAsText);
    }

    @Test
    public void provide_username() {
        assertEquals("usr", new SystemEnvCredentials(TEST_ENV).username());
    }

    @Test
    public void provide_password() {
        assertArrayEquals(asBytes("pwd"), new SystemEnvCredentials(TEST_ENV).password());
    }

    private byte[] asBytes(String pwd) {
        return pwd.getBytes(StandardCharsets.UTF_8);
    }

}