package com.library.repository;

import com.library.exception.InvalidOperationException;
import com.library.model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {

    private List<Transaction> transactions;

    public TransactionRepository() {
        // initialize storage
        transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        // store transaction
        transactions.add(transaction);
        System.out.println("Transaction successful");
    }

    public Transaction findActiveTransaction(String bookId) {
        for (Transaction t: transactions){
            if(t.getBookId().equals(bookId) && t.isActive()){
                return t;
            }
        }
        throw new InvalidOperationException("No active Transaction for this book id: " + bookId);
    }

    public List<Transaction> getTransactions(){
        return new ArrayList<>(transactions);
    }
}
