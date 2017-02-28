package com.transactionmicroservice.service;

import com.transactionmicroservice.domain.Transaction;

import java.util.List;

/**
 * Created by dani on 2017-02-23.
 */
public interface TransactionService {
    Transaction findByTransactionId(Long id);
    Transaction addTransaction(Transaction newTransaction);
    List<Transaction> findTransactions(Long userId, Long startPosition, Long endPosition);

    //admin
    Transaction deleteTransactionById(Long id);
}
