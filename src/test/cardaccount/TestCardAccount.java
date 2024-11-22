package cardaccount;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

 public class TestCardAccount {

     @Test
     public void crateCardAccount_withTen() {
         //Arrange
         CardAccount cardAccount = new CardAccount("Paolina", new BigDecimal("10"));
         //Act
         String accountOwner = cardAccount.getCardOwner();
         BigDecimal currentAmount = cardAccount.getCardAmount();
         //Assert
         Assertions.assertEquals("Paolina", accountOwner);
         Assertions.assertEquals(new BigDecimal("10"), currentAmount);
     }

     @Test
     public void crateCardAccount_withEmptyName() {
         //Arrange
         String name = "";
         //Act
         Executable action = () -> new CardAccount(name, new BigDecimal("10"));
         //Assert
         RuntimeException exception = assertThrows(RuntimeException.class,action);
         assertEquals("Invalid CardAccount data", exception.getMessage());
     }

     @Test
     public void crateCardAccount_withNullName() {
         //Arrange
         String name = null;
         //Act
         Executable action = () -> new CardAccount(name, new BigDecimal("10"));
         //Assert
         RuntimeException exception = assertThrows(RuntimeException.class,action);
         assertEquals("Invalid CardAccount data", exception.getMessage());
     }

     @Test
     public void crateCardAccount_withNullCardAmount() {
         //Arrange
         BigDecimal amount = null;
         //Act
         Executable action = () -> new CardAccount("Paolina", amount);
         //Assert
         RuntimeException exception = assertThrows(RuntimeException.class,action);
         assertEquals("Invalid CardAccount data", exception.getMessage());
     }

     @Test
     public void crateCardAccount_withNegativeCardAmount() {
         //Arrange
         BigDecimal amount = new BigDecimal("-10");
         //Act
         Executable action = () -> new CardAccount("Paolina", amount);
         //Assert
         RuntimeException exception = assertThrows(RuntimeException.class, action);
         assertEquals("Invalid CardAccount data", exception.getMessage());
     }

     @Test
     public void paidByCardThrows_InsufficientAmountException() {
         //Arrange
         CardAccount cardAccount = new CardAccount("Paolina", new BigDecimal("100"));
         //Act
         Executable action = () -> cardAccount.paidByCard("Paolina", new BigDecimal("200"));
         //Assert
         InsufficientAmountException exception = assertThrows(InsufficientAmountException.class, action);
         assertEquals("Sorry, can not enough amount on card", exception.getMessage());
     }

     @Test
     public void paidByCardThrows_AccessDeniedException() {
         //Arrange
         CardAccount cardAccount = new CardAccount("Paolina", new BigDecimal("100"));
         //Act
         Executable action = () -> cardAccount.paidByCard("Paola", new BigDecimal("50"));
         //Assert
         AccessDeniedException exception = assertThrows(AccessDeniedException.class, action);
         assertEquals("Access denied by owner verification", exception.getMessage());
     }
 }
