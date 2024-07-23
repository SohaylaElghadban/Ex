package main.Operations;

import main.Decerator;
import main.StringOperations;

public class ReverseOperation extends Decerator {
    public ReverseOperation(StringOperations operations) {
        super(operations);
    }

    @Override
    public String apply(String operation) {
        operation = super.apply(operation);
        return new StringBuilder(operation).reverse().toString();

    }
}
