package com.transactionmicroservice.model;

import java.util.List;

/**
 * Created by dani on 2017-02-23.
 */
public class TransactionListingDTO {

    private List<TransactionDTO> transactionDTOList;

    public List<TransactionDTO> getTransactionDTOList() { return transactionDTOList; }

    public void setTransactionDTOList(List<TransactionDTO> transactionDTOList) { this.transactionDTOList = transactionDTOList; }
}
