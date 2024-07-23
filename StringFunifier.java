package main;

import java.util.*;
import java.util.concurrent.*;

public class StringFunifier {
    private String inputString;
    private List<Integer> startIndexes;
    private List<Integer> endIndexes;
    private List<StringOperations> operations;
    public ExecutorService executor;

    public StringFunifier(String inputString, List<Integer> startIndexes, List<Integer> endIndexes, List<StringOperations> operations) {
        this.inputString = inputString;
        this.startIndexes = startIndexes;
        this.endIndexes = endIndexes;
        this.operations = operations;
        this.executor = Executors.newFixedThreadPool(5);
    }

    public String getFunnyString() {
        StringBuilder result = new StringBuilder();
        int previousEnd = 0;

        List<Future<String>> futures = new ArrayList<>();

        for (int i = 0; i < startIndexes.size(); i++) {
            int start = startIndexes.get(i);
            int end = endIndexes.get(i);
            StringOperations operation = operations.get(i);

            result.append(inputString, previousEnd, start);


            String substring = inputString.substring(start, end + 1);


            Callable<String> task = () -> operation.apply(substring);
            futures.add(executor.submit(task));

            previousEnd = end + 1;
        }


        result.append(inputString, previousEnd, inputString.length());

        for (Future<String> future : futures) {
            try {
                result.append(future.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();

        return result.toString();
    }



}
