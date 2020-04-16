package com.github.sixro.brokko;

public class AnemicOrder implements Order {

    private final String symbol;
    private final boolean pending;
    private final boolean executed;
    private final boolean refused;

    private AnemicOrder(String symbol, boolean pending, boolean executed, boolean refused) {
        this.symbol = symbol;
        this.pending = pending;
        this.executed = executed;
        this.refused = refused;
    }

    public static AnemicOrder executed(String symbol) {
        return new AnemicOrder(symbol, false, true, false);
    }

    public static AnemicOrder pending(String symbol) {
        return new AnemicOrder(symbol, true, false, false);
    }

    public static AnemicOrder refused(String symbol) {
        return new AnemicOrder(symbol, false, false, true);
    }

    @Override
    public String symbol() {
        return symbol;
    }

    @Override
    public void cancel() {
        throw new UnsupportedOperationException("come on boy! I'm anemic! what did you expect?");
    }

    @Override
    public boolean pending() {
        return pending;
    }

    @Override
    public boolean executed() {
        return executed;
    }

    @Override
    public boolean refused() {
        return refused;
    }
}
