package com.thecoalition.bankingApi.model;

import com.thecoalition.bankingApi.utility.Type;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private long accountId;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type")
    @NotEmpty
    private Type type;

    private String nickname;

    @Column(name = "rewards")
    @NotEmpty
    private int rewardPoints;

    @Column(name = "balance")
    @NotEmpty
    private double balance;

    @ManyToOne
    @NotEmpty
    @JoinColumn(name = "customer_id")
    private Customer customer;

    // ... rest of the class

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public long getAccountId() {
        return accountId;
    }
}

