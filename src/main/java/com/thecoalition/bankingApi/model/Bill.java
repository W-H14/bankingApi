package com.thecoalition.bankingApi.model;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



@Entity
@Table
public class Bill{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "status")
    private String status;


    @Column(name = "payee")
    private String payee;


    @Column(name = "nickname")
    private String nickname;


    @Column(name = "creation_date")
    private String creation_date;


    @Column(name = "payment_date")
    private String payment_date;


    @Column(name = "recurring_date")
    private Long recurring_date;


    @Column(name = "upcoming_payment")
    private String upcoming_payment;


    @Column (name = "account_id")
    private Long account_id;


    @Column(name = "payment_amount")
    private Double payment_amount;

    public Bill() {
    }

    public Bill(Long id, String status, String payee, String nickname, String creation_date, String payment_date, Long recurring_date, String upcoming_payment, Long account_id, Double payment_amount) {
        this.id = id;
        this.status = status;
        this.payee = payee;
        this.nickname = nickname;
        this.creation_date = creation_date;
        this.payment_date = payment_date;
        this.recurring_date = recurring_date;
        this.upcoming_payment = upcoming_payment;
        this.account_id = account_id;
        this.payment_amount = payment_amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public String getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(String payment_date) {
        this.payment_date = payment_date;
    }

    public Long getRecurring_date() {
        return recurring_date;
    }

    public void setRecurring_date(Long recurring_date) {
        this.recurring_date = recurring_date;
    }

    public String getUpcoming_payment() {
        return upcoming_payment;
    }

    public void setUpcoming_payment(String upcoming_payment) {
        this.upcoming_payment = upcoming_payment;
    }

    public Long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Long account_id) {
        this.account_id = account_id;
    }

    public Double getPayment_amount() {
        return payment_amount;
    }

    public void setPayment_amount(Double payment_amount) {
        this.payment_amount = payment_amount;
    }
}

