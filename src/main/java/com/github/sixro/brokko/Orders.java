package com.github.sixro.brokko;

import java.util.Iterator;
import java.util.stream.StreamSupport;

/**
 * Represents multiple orders.
 *
 * @author <a href="mailto:me@sixro.net" >Sixro</a>
 * @since 1.0
 */
public interface Orders extends Iterable<Order> {

    /**
     * Represents a smart class able to filter orders matching specified symbol.
     *
     * @author <a href="mailto:me@sixro.net" >Sixro</a>
     * @since 1.0
     */
    @SuppressWarnings("PMD.LawOfDemeter")
    class WithSymbol implements Orders {

        private final String symbol;
        private final Orders delegate;

        public WithSymbol(String symbol, Orders delegate) {
            this.symbol = symbol;
            this.delegate = delegate;
        }

        @Override
        public Iterator<Order> iterator() {
            return StreamSupport.stream(delegate.spliterator(), false)
                .filter(o -> symbol.equalsIgnoreCase(o.symbol()))
                .iterator();
        }
    }

    /**
     * Represents a smart class able to filter executed orders.
     *
     * @author <a href="mailto:me@sixro.net" >Sixro</a>
     * @since 1.0
     */
    @SuppressWarnings("PMD.LawOfDemeter")
    class Executed implements Orders {

        private final Orders delegate;

        public Executed(Orders delegate) {
            this.delegate = delegate;
        }

        @Override
        public Iterator<Order> iterator() {
            return StreamSupport.stream(delegate.spliterator(), false)
                .filter(Order::executed)
                .iterator();
        }
    }
}
