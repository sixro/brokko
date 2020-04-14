package com.github.sixro.brokko.finecobank;

import com.github.sixro.brokko.Broker;
import com.github.sixro.brokko.EntryOrder;
import com.github.sixro.brokko.Orders;
import com.github.sixro.brokko.Positions;

/**
 * Represents <a href="https://finecobank.com">FinecoBank S.p.a.</a> broker.
 *
 * @author <a href="mailto:me@sixro.net" >Sixro</a>
 * @since 1.0
 */
public final class FinecoBankBroker implements Broker {

    @Override
    public EntryOrder entryOrder() {
        return new DefaultEntryOrder();
    }

    @Override
    public Orders orders() {
        return null;
    }

    @Override
    public Positions positions() {
        return null;
    }

}
