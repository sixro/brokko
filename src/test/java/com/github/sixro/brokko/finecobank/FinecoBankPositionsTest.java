package com.github.sixro.brokko.finecobank;

import com.github.sixro.brokko.Orders;
import com.github.sixro.brokko.Positions;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class FinecoBankPositionsTest {

    @Rule
    public JUnitRuleMockery ctx = new JUnitRuleMockery();

    @Test
    public void happy_path() {
        LoggedIn loggedIn = ctx.mock(LoggedIn.class);
        Positions delegate = ctx.mock(Positions.class);

        ctx.checking(new Expectations() {{
            oneOf(loggedIn).ensure();
            oneOf(delegate).iterator();
        }});

        FinecoBankPositions p = new FinecoBankPositions(loggedIn, delegate);
        p.iterator();
    }

}