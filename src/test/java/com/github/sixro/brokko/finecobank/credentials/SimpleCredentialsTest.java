package com.github.sixro.brokko.finecobank.credentials;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleCredentialsTest {

    @Test
    public void immutable() {
        byte[] pwd = new byte[]{ 1 };
        SimpleCredentials c = new SimpleCredentials("usr", pwd);

        pwd[0] = 2;

        assertEquals(1, c.password()[0]);
        assertEquals("usr", c.username());
    }

}