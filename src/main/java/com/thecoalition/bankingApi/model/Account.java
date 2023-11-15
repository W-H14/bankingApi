package com.thecoalition.bankingApi.model;

import javax.persistence.*;
import javax.persistence.*;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;


    @Entity
    public class Account {

        @Id
        @GeneratedValue(strategy = GenerationType.TABLE)
        @Column(name = "ACCOUNT_ID")
        private long accountId;


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
        private Costumer costumer;



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

        public Costumer getCostumer() {
            return costumer;
        }

        public void setCostumer(Costumer costumer) {
            this.costumer = costumer;
        }
}
