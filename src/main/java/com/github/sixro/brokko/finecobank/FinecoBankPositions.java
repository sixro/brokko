package com.github.sixro.brokko.finecobank;

import com.github.sixro.brokko.Position;
import com.github.sixro.brokko.Positions;

import java.util.Iterator;

/**
 * Represents positions on
 * <a href="https://finecobank.com">FinecoBank S.p.a.</a>.
 *
 * @author <a href="mailto:me@sixro.net" >Sixro</a>
 * @since 1.0
 */
public final class FinecoBankPositions implements Positions {

    private final LoggedIn loggedIn;
    private final Positions delegate;

    public FinecoBankPositions(LoggedIn loggedIn, Positions delegate) {

        this.loggedIn = loggedIn;
        this.delegate = delegate;
    }

    @Override
    public Iterator<Position> iterator() {
        loggedIn.ensure();
        return delegate.iterator();
    }

}
