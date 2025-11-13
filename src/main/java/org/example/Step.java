package org.example;

public class Step {
    private final int source;
    private final int destination;

    public Step(int source, int destination) {
        this.source = source;
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "Step{" +
                "source=" + source +
                ", destination=" + destination +
                '}';
    }
}
