package com.thecoalition.bankingApi.model;

import com.thecoalition.bankingApi.utility.Type;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "ACCOUNT_ID")
    private long accountId;

    @Enumerated(EnumType.STRING)
    @Column(name = "ACCOUNT_TYPE")
    @NotEmpty
    private Type type;


    private String nickname;

    @Column(name = "REWARDS")
    @NotEmpty
    private int rewardPoints;

    @Column(name = "BALANCE")
    @NotEmpty
    private double balance;



    @ManyToOne
    @NotEmpty
    @JoinColumn(name = "COSTUMER_ID")
    private Customer costumer;



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

    public Customer getCostumer() {
        return costumer;
    }

    public void setCostumer(Customer costumer) {
        this.costumer = costumer;
    }

    public long getAccountId() {
        return accountId;
    }
}
