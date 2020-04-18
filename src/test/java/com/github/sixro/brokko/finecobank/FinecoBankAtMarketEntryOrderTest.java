package com.github.sixro.brokko.finecobank;

import com.github.sixro.brokko.EntryOrder;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class FinecoBankAtMarketEntryOrderTest {

    @Rule
    public JUnitRuleMockery ctx = new JUnitRuleMockery();

    @Test
    public void happy_path() {
        LoggedIn loggedIn = ctx.mock(LoggedIn.class);
        EntryOrder delegate = ctx.mock(EntryOrder.class);

        ctx.checking(new Expectations() {{
            oneOf(loggedIn).ensure();
            oneOf(delegate).enter();
        }});

        FinecoBankAtMarketEntryOrder eo = new FinecoBankAtMarketEntryOrder(loggedIn, delegate);
        eo.enter();
    }

}