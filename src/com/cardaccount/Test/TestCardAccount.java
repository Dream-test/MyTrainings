 package com.cardaccount.Test;
import com.cardaccount.AccessDeniedException;
import com.cardaccount.CardAccount;
import com.cardaccount.InsufficientAmountException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

 public class TestCardAccount {

     @Test
     public void crateCardAccount_withTen() {
         CardAccount cardAccount = new CardAccount("Paolina", new BigDecimal("10"));
         String accountOwner = cardAccount.getCardOwner();
         Assertions.assertEquals("Paolina", accountOwner);
         BigDecimal currentAmount = cardAccount.getCardAmount();
         Assertions.assertEquals(new BigDecimal("10"), currentAmount);
     }

     @Test
     public void paidByCardThrows_InsufficientAmountException() {
         CardAccount cardAccount = new CardAccount("Paolina", new BigDecimal("100"));
         InsufficientAmountException exception = assertThrows(InsufficientAmountException.class, () -> cardAccount.paidByCard("Paolina", new BigDecimal("200")));
         assertEquals("Sorry, can not enough amount on card", exception.getMessage());
     }

     @Test
     public void paidByCardThrows_AccessDeniedException() {
         CardAccount cardAccount = new CardAccount("Paolina", new BigDecimal("100"));
         AccessDeniedException exception = assertThrows(AccessDeniedException.class, () -> cardAccount.paidByCard("Paola", new BigDecimal("50")));
         assertEquals("Access denied by owner verification", exception.getMessage());
     }
 }
