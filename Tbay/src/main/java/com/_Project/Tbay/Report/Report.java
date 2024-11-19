package com._Project.Tbay.Report;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;


@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int reportId;

    private long listingId;

    private long adminId;

    private long userId;

    private String name;

    private String reason;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private boolean status;

    public Report(int reportId, long listingId, long adminId, long userId, String name, String reason, Date date, Boolean status) {
        this.reportId = reportId;
        this.adminId = adminId;
        this.userId = userId;
        this.name = name;
        this.reason = reason;
        this.date = date;
        this.status = status;
    }

    public Report() {}

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public long getReportId() {
        return reportId;
    }

    public void setListingId(long listingId) {
        this.listingId = listingId;
    }

    public long getListingId() {
        return listingId;
    }

    public void setAdminId(long adminId) {
        this.adminId = adminId;
    }

    public long getAdminId() {
        return adminId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }
}
