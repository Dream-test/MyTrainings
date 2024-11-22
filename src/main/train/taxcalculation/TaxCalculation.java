package train.taxcalculation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public class TaxCalculation {
    private final BigDecimal firstRange = new BigDecimal("100");
    private final BigDecimal firstRangeTax = new BigDecimal("0.1");
    private final BigDecimal secondRange = new BigDecimal("1000");
    private final BigDecimal secondRangeTax = new BigDecimal("0.2");
    private final BigDecimal thirdRange = new BigDecimal("10000");
    private final BigDecimal thirdRangeTax = new BigDecimal("0.3");

    public String[] tax(String[] prices) {
        BigDecimal[] pricesForCalculation = new BigDecimal[prices.length];
        int index = 0;
        for (String price : prices ) {
            if (!(price == null) && price.matches("[0-9]+\\.?[0-9]*")) {
                pricesForCalculation[index++] = new BigDecimal(price);
            }
        }

        BigDecimal[] taxes = new BigDecimal[index];
        index = 0;
        for (BigDecimal priceForCalculation : pricesForCalculation) {
            if (priceForCalculation == null) {
                continue;
            } else if (priceForCalculation.compareTo(BigDecimal.ZERO) < 0 || priceForCalculation.equals(BigDecimal.ZERO)) {
                continue;
            } else if (priceForCalculation.compareTo(firstRange) <= 0) {
                taxes[index++] = priceForCalculation.multiply(firstRangeTax).setScale(2, RoundingMode.HALF_UP);
            } else if (priceForCalculation.compareTo(secondRange) <= 0) {
                taxes[index++] = priceForCalculation.multiply(secondRangeTax).setScale(2, RoundingMode.HALF_UP);
            } else if (priceForCalculation.compareTo(thirdRange) <= 0) {
                taxes[index++] = priceForCalculation.multiply(thirdRangeTax).setScale(2, RoundingMode.HALF_UP);
            }
        }

        String[] tax = new String[index];
        index = 0;
        for (BigDecimal priceTax : taxes ) {
            if (priceTax == null){
                continue;
            }
            tax[index++] = priceTax.toString();
        }
        return tax;
    }
}
