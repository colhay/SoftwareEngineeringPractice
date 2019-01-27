package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals(200, bankAccount.getBalance());
    }

    @Test
    void withdrawTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        bankAccount.withdraw(100);
        assertEquals(100, bankAccount.getBalance());

        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.withdraw(-100));
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.withdraw(11.111));
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.withdraw(1000));
    }

    @Test
    void isEmailValidTest(){
        assertFalse(BankAccount.isEmailValid("ab.com")); // no @ symbol
        assertFalse(BankAccount.isEmailValid("a@@b.com")); // more than 1 @ symbol
        assertFalse(BankAccount.isEmailValid("@b.com")); // no characters before @ symbol
        assertFalse(BankAccount.isEmailValid("a@")); // no characters after @ symbol
        assertFalse(BankAccount.isEmailValid("a@bcom")); // no . after @ symbol
        assertFalse(BankAccount.isEmailValid("a@b..com")); // more than 1 . after @ symbol
        assertFalse(BankAccount.isEmailValid("a@.com")); // . is first character after @ symbol
        assertFalse(BankAccount.isEmailValid("a@b.")); // . is last character after @ symbol
        assertTrue(BankAccount.isEmailValid("a@b.com")); // valid test
    }

    @Test
    void isAmountValidTest(){
        assertFalse(BankAccount.isAmountValid(0));
        assertTrue(BankAccount.isAmountValid(1));
        assertTrue(BankAccount.isAmountValid(.11));
        assertFalse(BankAccount.isAmountValid(.111));
    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", 100.111));
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", -100));
    }

}