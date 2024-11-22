package train.taxcalculation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class TestTaxCalculation {

    @Test
    void taxCalculation() {
        //Arrange
        String[] testString = {"-10", "0", "20", "100", "200", "1000", "6000", "20000", "0.5"};
        //Act
        TaxCalculation testTaxCalculation = new TaxCalculation();
        //Assert
        String[] testResult = {"2.00", "10.00", "40.00", "200.00", "1800.00", "0.05"};
        System.out.println("TestResult   :" + Arrays.toString(testResult));
        System.out.println("ActualResult :" + Arrays.toString(testTaxCalculation.tax(testString)));
        Assertions.assertArrayEquals(testTaxCalculation.tax(testString), testResult);
    }
}
