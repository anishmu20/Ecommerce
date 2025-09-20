package com.backsmiths.payment.payment;


import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.STRING;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="payment")
public class Payment {

    @Id
    @GeneratedValue
    private Integer id;
    private BigDecimal amount;
    @Enumerated(STRING)
    private PaymentMethod payMethod;
    private Integer orderId;
    @CreatedDate
    @Column(updatable = false,nullable = false)
    private LocalDateTime createDate;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDate;


    public Payment(Integer id, BigDecimal amount, PaymentMethod payMethod, Integer orderId, LocalDateTime createDate, LocalDateTime lastModifiedDate) {
        this.id = id;
        this.amount = amount;
        this.payMethod = payMethod;
        this.orderId = orderId;
        this.createDate = createDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public Payment() {
    }

    public Payment(Integer id, BigDecimal amount, PaymentMethod payMethod, Integer orderId) {
        this.id = id;
        this.amount = amount;
        this.payMethod = payMethod;
        this.orderId = orderId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public PaymentMethod getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(PaymentMethod payMethod) {
        this.payMethod = payMethod;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
