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
        assertTrue(BankAccount.isEmailValid( "a@b.com"));
        assertFalse( BankAccount.isEmailValid(""));
        assertFalse(BankAccount.isEmailValid( "a@"));
        assertFalse(BankAccount.isEmailValid( "a@b@c.com"));
        assertFalse(BankAccount.isEmailValid( "@a.com"));
        assertTrue(BankAccount.isEmailValid( "a.b@c.com"));
        assertFalse(BankAccount.isEmailValid( "a@.com"));
        assertFalse(BankAccount.isEmailValid( "a@b"));
    }

    @Test
    void isAmountValidTest(){
        assertTrue(BankAccount.isAmountValid(1));
        assertTrue(BankAccount.isAmountValid(1.1));
        assertTrue(BankAccount.isAmountValid(1.11));
        assertFalse(BankAccount.isAmountValid(1.111));
        assertFalse(BankAccount.isAmountValid(-1));
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