package com.thecoalition.bankingApi.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
@Entity
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotEmpty
    @Column(name = "DEPOSIT_ID")
    private Long depositID;

    @Column(name = "DEPOSIT_TYPE")
    @NotEmpty
    private String type;
    @Column(name = "TRANSACTION_DATE")
    @NotEmpty
    private String transaction_date;

    @Column(name = "STATUS")
    @NotEmpty
    private String status;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PAYEE_ID")
    @NotEmpty
    private Long payee_id;
    @Column(name = "MEDIUM")
    @NotEmpty
    private String medium;
    @Column(name = "AMOUNT")
    @NotEmpty
    private Double amount;
    @Column(name = "DESCRIPTION")
    @NotEmpty
    private String description;




}
