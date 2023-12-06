package com.thecoalition.bankingApi.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "withdrawalId")
    private Withdrawal withdrawal;

    @OneToOne
    @JoinColumn(name = "depositId")
    private Deposit deposit;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Withdrawal getWithdrawal() {
        return withdrawal;
    }

    public void setWithdrawal(Withdrawal withdrawal) {
        this.withdrawal = withdrawal;
    }

    public Deposit getDeposit() {
        return deposit;
    }

    public void setDeposit(Deposit deposit) {
        this.deposit = deposit;
    }



    public Activity() {
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", withdrawal=" + withdrawal +
                ", deposit=" + deposit +
                '}';
    }
}

