package org.example.service;

import java.util.*;

public class BankService {

    private Map<String, BankAccount> accounts = new HashMap<>();
    private List<Transaction> transactionHistory = new ArrayList<>();

    public void addAccount(BankAccount account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public BankAccount getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public void transfer(String fromAccountNumber, String toAccountNumber, double amount) throws InsufficientBalanceException {
        BankAccount from = accounts.get(fromAccountNumber);
        BankAccount to = accounts.get(toAccountNumber);

        if (from == null || to == null) {
            throw new IllegalArgumentException("One or both accounts not found");
        }

        from.withdraw(amount);
        to.deposit(amount);

        transactionHistory.add(new Transaction(fromAccountNumber, toAccountNumber, amount));
    }

    public List<Transaction> getTransactionHistory() {
        return Collections.unmodifiableList(transactionHistory);
    }
}
