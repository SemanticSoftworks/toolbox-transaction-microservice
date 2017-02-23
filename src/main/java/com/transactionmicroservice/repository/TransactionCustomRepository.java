package com.transactionmicroservice.repository;

import com.transactionmicroservice.domain.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dani on 2017-02-23.
 */
@Repository
public interface TransactionCustomRepository{
    List<Transaction> getTransactions(Long userId, Long start, Long end);
}
