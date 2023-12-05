package com.thecoalition.bankingApi.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class AccountActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_id")
    private Long id;

    @Column(name = "activity_date")
    private Date activityDate;

    @Column(name = "amount")
    private Double amount;
    @ManyToOne
    @JoinColumn(name = "withdrawal_id")
    private Withdrawal withdrawal;

    @ManyToOne
    @JoinColumn(name = "deposit_id")
    private Deposit deposit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
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

    public AccountActivity() {
    }


    @Override
    public String toString() {
        return "AccountActivity{" +
                "id=" + id +
                ", activityDate=" + activityDate +
                ", amount=" + amount +
                '}';
    }
}

