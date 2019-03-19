package com.tiki.test;

import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.tiki.test.Constants.EMPTY_SPACE;

public final class Solution {
    private Solution() { }

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    public static boolean isFormula(String str) {
        Pattern p = Pattern.compile("[\\/\\*\\-\\+]");
        Matcher m = p.matcher(str);
        return m.find();
    }

    public static String getCalculationFormula(Map<String,String> cellMap, String[] values) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str: values) {
            if (!isFormula(str) && !isNumeric(str)) {
                String cellValue = cellMap.get(str.trim());
                stringBuilder.append(cellValue);
            } else {
                stringBuilder.append(str.trim());
            }
            stringBuilder.append(EMPTY_SPACE);
        }
        return stringBuilder.toString().trim();
    }

    public static String[] getCircularDependency(Map<String,String> cellMap, String key, String[] values) {
        for (String str: values) {
            if (!isFormula(str) && !isNumeric(str)) {
                String cellValue = cellMap.get(str.trim());
                if( cellValue.contains(key)) {
                    return new String[]{ key, str.trim()};
                }
            }
        }
        return new String[]{};
    }

    public static double calculate(String[] tokens) {
        double returnValue = 0;

        String operator = "+-*/";

        Stack<String> stack = new Stack<>();

        for (String t : tokens) {
            if(t.isEmpty()) continue;
            if (!operator.contains(t)) {
                stack.push(t);
            } else {
                double a = Double.valueOf(stack.pop());
                double b = Double.valueOf(stack.pop());
                int index = operator.indexOf(t);
                switch (index) {
                    case 0:
                        stack.push(String.valueOf(a + b));
                        break;
                    case 1:
                        stack.push(String.valueOf(b - a));
                        break;
                    case 2:
                        stack.push(String.valueOf(a * b));
                        break;
                    case 3:
                        stack.push(String.valueOf(b / a));
                        break;
                    default:
                       break;
                }
            }
        }

        returnValue = Double.valueOf(stack.pop());

        return returnValue;
    }
}
