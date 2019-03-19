package com.tiki.test;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class SolutionTest {

    @Test
    public void test__isNumeric__shouldReturnTrue__whenStringIsNumber() {
        String testString = "1";
        boolean result = Solution.isNumeric(testString);

        assertTrue("method should return true for correct numeric string", result);
    }

    @Test
    public void test__isNumeric__shouldReturnTrue__whenStringIsNotNumber() {
        String testString = "a";
        boolean result = Solution.isNumeric(testString);

        assertFalse("method should return false for incorrect numberic string", result);
    }

    @Test
    public void test__isFormula__shouldReturnTrue__whenStringContainFormulaString() {
        String testString = "1 * 1";
        boolean result = Solution.isFormula(testString);

        assertTrue("method should return true for correct formula string", result);
    }

    @Test
    public void test__isFormula__shouldReturnTrue__whenStringIsNotContainIFormulaString() {
        String testString = "11";
        boolean result = Solution.isFormula(testString);

        assertFalse("method should return false for incorrect formula string", result);
    }

    @Test
    public void test__getCalculationFormula__shouldReturnFormulaString() {
        Map<String, String> cellMaps = new HashMap<>();
        cellMaps.put("A1", "5");
        cellMaps.put("A2", "A1 5 *");

        String result = Solution.getCalculationFormula(cellMaps, cellMaps.get("A2").split(" "));

        assertEquals("method should return string value 5 5 *", "5 5 *", result);
    }


    @Test
    public void test__getCircularDependency__shouldReturnArrayForCircularDependency() {
        Map<String, String> cellMaps = new HashMap<>();
        cellMaps.put("A1", "A2 2 +");
        cellMaps.put("A2", "A1 5 *");

        String[] result = Solution.getCircularDependency(cellMaps, "A2", cellMaps.get("A2").split(" "));

        assertEquals("method should return array contain first value is A2", "A2", result[0]);
        assertEquals("method should return array contain second value is A1", "A1", result[1]);
    }

    @Test
    public void test__getCircularDependency__shouldReturnEmptyArrayForCircularDependency__whenDoesNotHasCircularDependency() {
        Map<String, String> cellMaps = new HashMap<>();
        cellMaps.put("A1", "A3 2 +");
        cellMaps.put("A2", "A1 5 *");
        cellMaps.put("A3", "5");

        String[] result = Solution.getCircularDependency(cellMaps, "A2", cellMaps.get("A2").split(" "));

        assertEquals("method should return empty array", 0, result.length);
    }


    @Test
    public void test_calculate__shouldReturnCorrectResultForFormula() {
        String[] arrayTest = new String[]{"5", "5", "*", "6", "+", "2", "-", "2", "/"};

        double result = Solution.calculate(arrayTest);

        assertTrue("method should return 14.5", Math.abs(result - 14.5) == 0);
    }
}
