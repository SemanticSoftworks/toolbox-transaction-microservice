package com.transactionmicroservice.service.Impl;

import com.transactionmicroservice.domain.Transaction;
import com.transactionmicroservice.repository.TransactionCustomRepository;
import com.transactionmicroservice.repository.TransactionRepository;
import com.transactionmicroservice.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dani on 2017-02-23.
 */
@Transactional
@Service
public class TransactionServiceImpl implements TransactionService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionCustomRepository transactionCustomRepository;

    @Override
    public Transaction findByTransactionId(Long id) {
        return transactionRepository.findByTransactionId(id);
    }

    @Override
    public Transaction addTransaction(Transaction newTransaction) {
        if(newTransaction != null)
            return transactionRepository.save(newTransaction);
        return null;
    }

    @Override
    public List<Transaction> findTransactions(Long userId, Long startPosition, Long endPosition) {
        return transactionCustomRepository.getTransactions(userId,startPosition, endPosition);
    }

    @Override
    public Long getPermission(String username, String password) {
        Long userId=0L;
        Map<String, String> params = new HashMap<String, String>();
        try {
            String url = "http://localhost:8091/user/?username="+username+"&"+"password="+password;
            RestTemplate restTemplate = new RestTemplate();

            userId = (restTemplate.getForObject(url, Long.class , params));
        } catch(HttpClientErrorException e ){ logger.info("CATCH bad request getPermission!"); return null; }

        return userId;
    }

    @Override
    public Long getAdminPermission(String username, String password) {
        Long userId=0L;
        Map<String, String> params = new HashMap<String, String>();
        try {
            String url = "http://localhost:8091/user/admin/permission?username="+username+"&"+"password="+password;
            RestTemplate restTemplate = new RestTemplate();
            userId = (restTemplate.getForObject(url, Long.class , params));
        } catch(HttpClientErrorException e ){ logger.info("CATCH bad request getPermission!"); return null; }

        return userId;
    }

    @Override
    public Transaction deleteTransactionById(Long id) {
        Transaction transaction = transactionRepository.findByTransactionId(id);

        if(transaction != null){
            transactionRepository.delete(transaction);
        }
        return transaction;
    }
}
