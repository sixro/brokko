package com.github.sixro.brokko;

public class AnemicOrder implements Order {

    private final String symbol;
    private final Status status;

    public AnemicOrder(String symbol, Status status) {
        this.symbol = symbol;
        this.status = status;
    }

    @Override
    public String symbol() {
        return symbol;
    }

    @Override
    public Status status() {
        return status;
    }

    @Override
    public void cancel() {
        throw new UnsupportedOperationException("come on boy! I'm anemic! what did you expect?");
    }

}
