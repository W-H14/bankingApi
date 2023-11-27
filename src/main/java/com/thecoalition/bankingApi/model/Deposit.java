package com.thecoalition.bankingApi.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
public class Deposit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotEmpty
    @Column(name = "deposit_id")
    private Long depositID;

    @Column(name = "deposit_type")
    @NotEmpty
    private String type;
    @Column(name = "transaction_date")
    @NotEmpty
    private String transaction_date;

    @Column(name = "status")
    @NotEmpty
    private String status;


    @JoinColumn(name = "payee_id")
    @NotEmpty
    private Long payee_id;
    @Column(name = "medium")
    @NotEmpty
    private String medium;
    @Column(name = "amount")
    @NotEmpty
    private Double amount;
    @Column(name = "description")
    @NotEmpty
    private String description;

    public Long getDepositID() {
        return depositID;
    }

    public void setDepositID(Long depositID) {
        this.depositID = depositID;
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

    public Long getPayee_id() {
        return payee_id;
    }

    public void setPayee_id(Long payee_id) {
        this.payee_id = payee_id;
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

    @Override
    public String toString() {
        return "Deposit{" +
                "depositID=" + depositID +
                ", type='" + type + '\'' +
                ", transaction_date='" + transaction_date + '\'' +
                ", status='" + status + '\'' +
                ", payee_id=" + payee_id +
                ", medium='" + medium + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }
}

