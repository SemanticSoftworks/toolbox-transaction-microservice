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
    Long getPermission(String username, String password);
    Long getAdminPermission(String username, String password);

    //admin
    Transaction deleteTransactionById(Long id);
}
