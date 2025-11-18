package org.example.service;

import org.example.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class BankServiceTest {

    private BankService bankService;

    @BeforeEach
    void setup() {
        bankService = new BankService();
        bankService.addAccount(new BankAccount("A001", 200));
        bankService.addAccount(new BankAccount("A002", 50));
    }

    @Test
    void testTransfer() throws InsufficientBalanceException {
        bankService.transfer("A001", "A002", 100);

        assertEquals(100, bankService.getAccount("A001").getBalance());
        assertEquals(150, bankService.getAccount("A002").getBalance());

        List<Transaction> history = bankService.getTransactionHistory();
        assertEquals(1, history.size());
        assertEquals("A001", history.get(0).getFromAccount());
        assertEquals("A002", history.get(0).getToAccount());
        assertEquals(100, history.get(0).getAmount());
    }

    @Test
    void testTransferInsufficientBalance() {
        assertThrows(InsufficientBalanceException.class, () -> bankService.transfer("A002", "A001", 100));
    }

    @Test
    void testTransferInvalidAccount() {
        assertThrows(IllegalArgumentException.class, () -> bankService.transfer("A003", "A001", 50));
    }
}
