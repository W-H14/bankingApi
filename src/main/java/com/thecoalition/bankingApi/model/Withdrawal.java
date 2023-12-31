package com.thecoalition.bankingApi.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Withdrawal {
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotEmpty
    @Column(name = "withdrawal_id")
    private Long id;
    @Column(name = "withdrawalType")
    @NotEmpty
    private String type;
    @Column(name = "transactionDate")
    @NotEmpty
    private String transaction_date;
    @Column(name = "status")
    @NotEmpty
    private String status;
    @Column(name = "payerId")
    @NotEmpty
    private Long payer_id;
    @Column(name = "medium")
    @NotEmpty
    private String medium;
    @Column(name = "amount")
    @NotEmpty
    private Double amount;
    @Column(name = "description")
    @NotEmpty
    private String description;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getPayer_id() {
        return payer_id;
    }

    public void setPayer_id(Long payer_id) {
        this.payer_id = payer_id;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Withdrawal(Long id, String type, String transaction_date, String status, Long payer_id, String medium, Double amount, String description) {
        this.id = id;
        this.type = type;
        this.transaction_date = transaction_date;
        this.status = status;
        this.payer_id = payer_id;
        this.medium = medium;
        this.amount = amount;
        this.description = description;
    }

    public Withdrawal() {
    }

    @Override
    public String toString() {
        return "Withdrawal{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", transaction_date='" + transaction_date + '\'' +
                ", status='" + status + '\'' +
                ", payer_id=" + payer_id +
                ", medium='" + medium + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }
}
