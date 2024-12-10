package com._Project.Tbay.Orders;

import jakarta.persistence.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long orderId;

    @Column(nullable = false)
    private long sellerId;

    @Column(nullable = false)
    private long userId;

    @Column(nullable = false)
    private long listingId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable=false)
    private Date orderDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable=true)
    private Date completionDate;

    @Column(nullable=false)
    private boolean completionStatus;

    public Order(long orderId, long sellerId, long userId, long listingId, Date orderDate, boolean completionStatus){
        this.orderId = orderId;
        this.sellerId = sellerId;
        this.listingId = listingId;
        this.userId = userId;
        this.orderDate = orderDate;
        this.completionStatus= completionStatus;
    }

    public Order(){};

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getListingId() {
        return listingId;
    }

    public void setListingId(long listingId) {
        this.listingId = listingId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public boolean isCompletionStatus() {
        return completionStatus;
    }

    public void setCompletionStatus(boolean completionStatus) {
        this.completionStatus = completionStatus;
    }
}
