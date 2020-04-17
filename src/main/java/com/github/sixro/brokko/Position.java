package com.github.sixro.brokko;

/**
 * Represents a position in the market.
 *
 * @author <a href="mailto:me@sixro.net" >Sixro</a>
 * @since 1.0
 */
public interface Position {

    /**
     * Close the position.
     */
    void close();

    /**
     * Returns the symbol on which this position is open.
     *
     * @return the symbol on which this position is open
     */
    String symbol();

}
