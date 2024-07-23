package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import main.Operations.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the string:");
        String boringString = scanner.nextLine();

        System.out.println("Enter the number of operations:");
        int numOperations = Integer.parseInt(scanner.nextLine());
        List<Integer> startIndexes = new ArrayList<>();
        List<Integer> endIndexes = new ArrayList<>();

        List<StringOperations> operations = new ArrayList<>(List.of(
                new LowerCaseOperation(new ReverseOperation(new CompressionOperation(new NothingOperation()))),
                new UpperCaseOperation(new NothingOperation()),
                new SortOperation(new UpperCaseOperation(new CompressionOperation(new NothingOperation()))),
                new CompressionOperation(new NothingOperation()),
                new ReverseOperation(new UpperCaseOperation(new CompressionOperation(new NothingOperation())))
        ));


        for (int i = 0; i < numOperations; i++) {
            System.out.println("Enter start index for operation " + (i + 1) + ":");
            int startIndex = Integer.parseInt(scanner.nextLine());
            startIndexes.add(startIndex);

            System.out.println("Enter end index for operation " + (i + 1) + ":");
            int endIndex = Integer.parseInt(scanner.nextLine());
            endIndexes.add(endIndex);

            System.out.println("Enter operations for operation " + (i + 1) + " (comma-separated, e.g., REVERSE, UPPERCASE):");
            String[] ops = scanner.nextLine().toUpperCase().split(",");
            List<StringOperations> compositeOps = new ArrayList<>();
            StringOperations stringOperation = new NothingOperation();
            for (String op : ops) {
                switch (op.trim()) {
                    case "REVERSE" -> stringOperation = new ReverseOperation(stringOperation);
                    case "UPPERCASE" -> stringOperation = new UpperCaseOperation(stringOperation);
                    case "LOWERCASE" -> compositeOps.add(new LowerCaseOperation(stringOperation));
                    case "SORT" -> compositeOps.add(new SortOperation(stringOperation));
                    case "COMPRESSION" -> compositeOps.add(new CompressionOperation(stringOperation));

                }
            }

        }

        StringFunifier funifier = new StringFunifier(boringString, startIndexes, endIndexes, operations);


        long startTimeTotal = System.currentTimeMillis();

        String funnyString = funifier.getFunnyString();


        long endTimeTotal = System.currentTimeMillis();
        long totalExecutionTime = endTimeTotal - startTimeTotal;
       // System.out.println(startTimeTotal);
        System.out.println("Funny String: " + funnyString);
       // System.out.println(endTimeTotal);
        System.out.println("Total Execution Time: " + totalExecutionTime + " milliseconds");
    }
}
