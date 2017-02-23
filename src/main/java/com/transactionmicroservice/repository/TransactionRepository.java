package com.transactionmicroservice.repository;

import com.transactionmicroservice.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by dani on 2017-02-23.
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction findByTransactionId(Long transactionId);
}