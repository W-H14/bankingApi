package com.thecoalition.bankingApi.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class AccountActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    private Double amount;

    private LocalDateTime timestamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public AccountActivity() {
    }

    public AccountActivity(Long id, Account account, Double amount, LocalDateTime timestamp) {
        this.id = id;
        this.account = account;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "AccountActivity{" +
                "id=" + id +
                ", account=" + account +
                ", amount=" + amount +
                ", timestamp=" + timestamp +
                '}';
    }
}
