package cardaccount;

import java.math.BigDecimal;

public class Main {
    static BigDecimal amount = new BigDecimal("1250.45");
    static String cardOwner = "Wilkinson";


    public static void main(String[] args) {
        CardAccount currentAccount = new CardAccount(cardOwner, amount);
        System.out.println(currentAccount);
        System.out.println("--------------------------------------------------------");
        currentAccount = paidAmountByCard(currentAccount, "Wilkinson", new BigDecimal("1200"));
        System.out.println(currentAccount);
        System.out.println("--------------------------------------------------------");
        currentAccount = paidAmountByCard(currentAccount, "Wilkinson", new BigDecimal("100"));
        System.out.println(currentAccount);
        System.out.println("--------------------------------------------------------");
        currentAccount = paidAmountByCard(currentAccount, "Peterson", new BigDecimal("40"));
        System.out.println(currentAccount);



    }

    public static CardAccount paidAmountByCard(CardAccount currentAccount, String cardOwner, BigDecimal paidAmount) {
        try {
            System.out.println("Your account balance: " + currentAccount.paidByCard(cardOwner, paidAmount));
            System.out.println("Payment successful");
            return currentAccount;
        } catch (InsufficientAmountException error) {
            System.out.println(error.getMessage());
        } catch (AccessDeniedException error) {
            System.out.println(error.getMessage());
        } return currentAccount;
    }
}


