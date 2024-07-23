package main;

public abstract class Decerator implements StringOperations {
    StringOperations operations;

    @Override
    public String apply(String operation) {
        return this.operations.apply(operation);
    }

    public Decerator(StringOperations operations) {
        this.operations = operations;
    }
}
