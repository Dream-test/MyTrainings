import java.math.BigDecimal;

public class Main {
    static BigDecimal amount = new BigDecimal("1250.45");

    //static float amount = 1250.45F;
    static String cardOwner = "Wilkinson";


    public static void main(String[] args) {
        CardAccount currentAccount = new CardAccount(cardOwner, amount);
        System.out.println(currentAccount.toString());
        System.out.println("--------------------------------------------------------");
        currentAccount = paidAmountByCard(currentAccount, "Wilkinson", new BigDecimal("1200"));
        System.out.println(currentAccount.toString());
        System.out.println("--------------------------------------------------------");
        currentAccount = paidAmountByCard(currentAccount, "Wilkinson", new BigDecimal("100"));
        System.out.println(currentAccount.toString());
        System.out.println("--------------------------------------------------------");
        currentAccount = paidAmountByCard(currentAccount, "Peterson", new BigDecimal("40"));
        System.out.println(currentAccount.toString());



    }

    public static CardAccount paidAmountByCard(CardAccount currentAccount, String cardOwner, BigDecimal paidAmount) {
        try {
            System.out.println("Your account balance: " + currentAccount.paidByCard(cardOwner, paidAmount));
            System.out.println("Payment successful");
            return currentAccount;
        } catch (InsufficientAmountException error) {
            System.out.println("Sorry, you haven't enough amount on card");
        } catch (AccessDeniedException error) {
            System.out.println("Access denied, wrong card owner name");
        } return currentAccount;
    }
}


