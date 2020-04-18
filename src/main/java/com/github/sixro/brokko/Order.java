package com.github.sixro.brokko;

/**
 * Represents an order.
 *
 * @author <a href="mailto:me@sixro.net" >Sixro</a>
 * @since 1.0
 */
public interface Order {

    /**
     * Represents the status of an order.
     */
    enum Status {
        /**
         * The order has not been processed yet.
         */
        PENDING,
        /**
         * The order has been executed.
         */
        EXECUTED,
        /**
         * The order has been refused.
         */
        REFUSED,
        /**
         * The order has been cancelled.
         *
         * @see #cancel()
         */
        CANCELLED
    }

    /**
     * Cancel this order.
     */
    void cancel();

    /**
     * Returns the symbol to which this order is related.
     *
     * @return a symbol
     */
    String symbol();

    /**
     * Returns the status of this order.
     *
     * @return the status
     */
    Status status();

}
