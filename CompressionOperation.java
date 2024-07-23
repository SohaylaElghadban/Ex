package main.Operations;

import main.Decerator;
import main.StringOperations;

public class CompressionOperation extends Decerator {
    public CompressionOperation(StringOperations operations) {
        super(operations);
    }

    @Override
    public String apply(String operation) {
        operation = super.apply(operation);
        StringBuilder compressed = new StringBuilder();
        int count = 1;

        for (int i = 1; i <  operation.length(); i++) {
            if ( operation.charAt(i) ==  operation.charAt(i - 1)) {
                count++;
            } else {
                compressed.append( operation.charAt(i - 1)).append(count);
                count = 1;
            }
        }
        compressed.append( operation.charAt( operation.length() - 1)).append(count);
        return compressed.toString();
    }
}


