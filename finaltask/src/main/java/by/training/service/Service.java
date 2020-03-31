package by.training.service;

import by.training.service.transaction.Transaction;

public abstract class Service {
    protected Transaction transaction;

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
