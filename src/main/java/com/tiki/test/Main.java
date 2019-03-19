package com.tiki.test;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import static com.tiki.test.Constants.EMPTY_SPACE;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numberOfCells = Integer.parseInt(in.nextLine());
        String previousValue;
        String currentValue = "";
        Map<String, String> cellsMap = new TreeMap<>();
        int totalRows = numberOfCells * 2;
        while (totalRows > 0) {
            previousValue = currentValue;
            currentValue = in.nextLine();

            if (Solution.isFormula(currentValue) || Solution.isNumeric(currentValue)) {
                cellsMap.put(previousValue, currentValue);
            }

            totalRows--;
        }

        for (Map.Entry<String, String> entry: cellsMap.entrySet()) {
            System.out.println(entry.getKey());
            if(Solution.isFormula(entry.getValue())) {
                String[] values = entry.getValue().split(" ");
                String[] circularDependencies = Solution.getCircularDependency(cellsMap,
                        entry.getKey(), values);
                if (circularDependencies.length > 0) {
                    String message = String.format("Circular dependency between %s and %s detected", circularDependencies[0], circularDependencies[1]);
                    System.out.println(message);
                    break;
                } else {
                    String formulaString = Solution.getCalculationFormula(cellsMap, values);
                    System.out.println(Solution.calculate(formulaString.split(EMPTY_SPACE)));
                }
            } else {
                System.out.println(entry.getValue());
            }
        }
    }



}
