package com.transactionmicroservice.controller;

import com.transactionmicroservice.domain.Transaction;
import com.transactionmicroservice.model.TransactionDTO;
import com.transactionmicroservice.model.TransactionListingDTO;
import com.transactionmicroservice.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by dani on 2017-02-23.
 */
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    private TransactionService transactionService;

    // ÄNDRAT:
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<TransactionDTO> getTransaction(@PathVariable Long id){

        Transaction transaction = transactionService.findByTransactionId(id);
        TransactionDTO transactionDTO = new TransactionDTO();

        if (transaction != null) {
            transactionDTO.setTransactionId(transaction.getTransactionId());
            transactionDTO.setDescription(transaction.getDescription());
            transactionDTO.setSum(transaction.getSum());
            transactionDTO.setDate(transaction.getDate() != null ? transaction.getDate().getTime().toString() : null);
        }
        return new ResponseEntity<>(transactionDTO, HttpStatus.OK);
    }

    // ÄNDRAT:
    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = {"application/json"})
    public ResponseEntity<TransactionDTO> addTransaction(@RequestBody TransactionDTO incomingTransaction) {

        Transaction transaction = new Transaction();
        transaction.setDate(Calendar.getInstance());
        transaction.setDescription(incomingTransaction.getDescription());
        transaction.setSum(incomingTransaction.getSum());
        transaction.setUserId(incomingTransaction.getUserId());

        transaction = transactionService.addTransaction(transaction);
        if (transaction != null) {
            TransactionDTO transactionDTO = new TransactionDTO();
            transactionDTO.setTransactionId(transaction.getTransactionId());
            transactionDTO.setDate(transaction.getDate() != null ? transaction.getDate().getTime().toString() : null);
            transactionDTO.setSum(transaction.getSum());
            return new ResponseEntity<>(transactionDTO, HttpStatus.OK);
        }

        return new ResponseEntity<>(new TransactionDTO(), HttpStatus.BAD_REQUEST);
    }

    //ÄNDRAT:*
    @RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
    public ResponseEntity<TransactionListingDTO> getTransactions(@PathVariable Long id ,@RequestParam Long startPosition, @RequestParam Long endPosition){
        List<TransactionDTO> transactionDTOList = new ArrayList<>();
        TransactionListingDTO transactionListingDTO = new TransactionListingDTO();

        List<Transaction> transactionList = transactionService.findTransactions(id, startPosition, endPosition);

        for(Transaction t : transactionList){
            TransactionDTO transactionDTO = new TransactionDTO();
            transactionDTO.setTransactionId(t.getTransactionId());
            transactionDTO.setSum(t.getSum());
            transactionDTO.setDate(t.getDate() != null? t.getDate().getTime().toString() : null);
            transactionDTO.setDescription(t.getDescription());
            transactionDTOList.add(transactionDTO);
        }
        transactionListingDTO.setTransactionDTOList(transactionDTOList);
        logger.info("List length: "+transactionListingDTO.getTransactionDTOList().size());
        return new ResponseEntity<>(transactionListingDTO, HttpStatus.OK);
    }

    // ADMIN METODER
    @RequestMapping(value="/admin/update" , method = RequestMethod.PUT, consumes = {"application/json"})
    public ResponseEntity<TransactionDTO> updateTransaction(@RequestBody TransactionDTO incomingTransaction){
        TransactionDTO transactionDTO = new TransactionDTO();

        Transaction transaction = transactionService.findByTransactionId(incomingTransaction.getTransactionId());
        transaction.setSum(incomingTransaction.getSum());
        transaction.setDescription(incomingTransaction.getDescription());

        transaction = transactionService.addTransaction(transaction);
        if (transaction != null) {
            transactionDTO.setDate(transaction.getDate() != null ? transaction.getDate().getTime().toString() : null);
            transactionDTO.setDescription(transaction.getDescription());
            transactionDTO.setTransactionId(transaction.getTransactionId());
            transactionDTO.setSum(transaction.getSum());

            return new ResponseEntity<>(transactionDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(transactionDTO, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value="/admin/{id}" , method = RequestMethod.DELETE)
    public ResponseEntity<TransactionDTO> deleteTransaction(@PathVariable Long id){
        TransactionDTO transactionDTO = new TransactionDTO();

        Transaction transaction = transactionService.deleteTransactionById(id);
        if (transaction != null) {
            transactionDTO.setTransactionId(transaction.getTransactionId());
            transactionDTO.setSum(transaction.getSum());
            transactionDTO.setDescription(transaction.getDescription());
            transactionDTO.setDate(transaction.getDate() != null ? transaction.getDate().getTime().toString() : null);

            return new ResponseEntity<>(transactionDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(transactionDTO, HttpStatus.BAD_REQUEST);
    }
}