package com.github.sixro.brokko.finecobank;

import com.github.sixro.brokko.Orders;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class FinecoBankOrdersTest {

    @Rule public JUnitRuleMockery ctx = new JUnitRuleMockery();

    @Test public void happy_path() {
        LoggedIn loggedIn = ctx.mock(LoggedIn.class);
        Orders delegate = ctx.mock(Orders.class);

        ctx.checking(new Expectations() {{
            oneOf(loggedIn).assure();
            oneOf(delegate).iterator();
        }});

        FinecoBankOrders o = new FinecoBankOrders(loggedIn, delegate);
        o.iterator();
    }

}