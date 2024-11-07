package com._Project.Tbay.Receipt;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;


@Entity
@Table(name = "receipts")
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long receiptId;

    private List<Long> items;

    private float price;

    private Date date;

    public Receipt(long receiptId, List<Long> items, float price, Date date) {
        this.receiptId = receiptId;
        this.items = items;
        this.price = price;
        this.date = date;
    }

    public Receipt() {}

    public void setReceiptId(long receiptId) {
        this.receiptId = receiptId;
    }

    public long getReceiptId() {
        return receiptId;
    }

    public void setItems(List<Long> items) {
        this.items = items;
    }

    public List<Long> getItems() {
        return items;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }
}
