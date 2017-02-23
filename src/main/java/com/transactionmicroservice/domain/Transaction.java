package com.transactionmicroservice.domain;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Created by dani on 2017-02-23.
 */
@Entity(name = "Transaction")
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionId;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar date;

    private String description;

    private int sum;

    private Long userId;

    public Long  getTransactionId() { return transactionId; }

    public void setTransactionId(Long  transactionId) { this.transactionId = transactionId; }

    public Calendar getDate() { return date; }

    public void setDate(Calendar date) { this.date = date; }

    public String getDescription() { return description; }

    public void setDescription(String desciption) { this.description = desciption; }

    public int getSum() { return sum; }

    public void setSum(int sum) { this.sum = sum; }

    public Long getUserId() { return userId; }

    public void setUserId(Long userId) { this.userId = userId; }
}