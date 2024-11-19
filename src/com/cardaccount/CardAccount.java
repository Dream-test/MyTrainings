
package com.cardaccount;
import java.math.BigDecimal;

public class CardAccount {
    private String cardOwner;
    private BigDecimal cardAmount;



    public CardAccount(String cardOwnerName, BigDecimal cardAmount) {
        if (cardOwnerName == null || cardAmount == null || cardOwnerName.isEmpty() || cardAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Invalid CardAccount data");
        } else {
            this.cardOwner = cardOwnerName;
            this.cardAmount = cardAmount;
        }
    }

    public BigDecimal paidByCard(String cardOwnerName, BigDecimal paidAmount) throws AccessDeniedException, InsufficientAmountException {
        if (!ownerVerification(cardOwnerName)) {
            throw new AccessDeniedException("Access denied by owner verification");
        } else if (!checkCardAmount(paidAmount)) {
            throw new InsufficientAmountException("Sorry, can not enough amount on card");
        } return cardAmount = cardAmount.subtract(paidAmount);
    }

    private boolean ownerVerification(String cardOwnerName) {
        return cardOwnerName.equals(cardOwner);
    }

    private boolean checkCardAmount(BigDecimal paidAmount) {
        return paidAmount.compareTo(cardAmount) < 0;
    }

    public String getCardOwner() {
        return cardOwner;
    }

    public BigDecimal getCardAmount() {
        return cardAmount;
    }

    @Override
    public String toString() {
        return "CardAccount{" +
                "cardOwner='" + cardOwner + '\'' +
                ", cardAmount=" + cardAmount.toString() +
                '}';
    }
}
