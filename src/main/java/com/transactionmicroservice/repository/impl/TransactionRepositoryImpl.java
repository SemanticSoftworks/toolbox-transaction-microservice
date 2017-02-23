package com.transactionmicroservice.repository.impl;

import com.transactionmicroservice.domain.Transaction;
import com.transactionmicroservice.repository.TransactionCustomRepository;
import com.transactionmicroservice.service.Impl.TransactionServiceImpl;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by dani on 2017-02-23.
 */
public class TransactionRepositoryImpl implements TransactionCustomRepository {

    private static final Logger logger = LoggerFactory.getLogger(TransactionRepositoryImpl.class);

    @Autowired
    private SessionFactory factory;

    @Override
    public List<Transaction> getTransactions(Long userId, Long start, Long end){
        Session session = factory.getCurrentSession();

        Query q = session.createQuery("SELECT t FROM com.transactionmicroservice.domain.Transaction t WHERE t.userId=:userId");
        q.setParameter("userId", userId);
        q.setFirstResult(start.intValue());
        q.setMaxResults(end.intValue());
        List<Transaction> transactionList = q.list();

        logger.info("transRepo size: "+transactionList.size());
        return transactionList;
    }

}
