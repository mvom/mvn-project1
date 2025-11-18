package org.example.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void testDeposit() {
        BankAccount account = new BankAccount("A001", 100);
        account.deposit(50);
        assertEquals(150, account.getBalance());
    }

    @Test
    void testWithdraw() throws InsufficientBalanceException {
        BankAccount account = new BankAccount("A001", 100);
        account.withdraw(40);
        assertEquals(60, account.getBalance());
    }

    @Test
    void testWithdrawInsufficientBalance() {
        BankAccount account = new BankAccount("A001", 50);
        assertThrows(InsufficientBalanceException.class, () -> account.withdraw(100));
    }
}
