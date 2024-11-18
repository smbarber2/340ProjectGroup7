package com._Project.Tbay.Report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
    @Autowired
    private ReportRepository reportRepository;

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }


    public Report getReportById(long reportId) {
        return reportRepository.findById(reportId).orElse(null);
    }

    public void addNewReport(Report report){
        reportRepository.save(report);
    }

    public void updateReport(long reportId, Report report) {
        Report existing = getReportById(reportId);
        existing.setReason(existing.getReason());
        existing.setStatus(existing.isStatus());

        reportRepository.save(existing);
    }

    public void deleteReportById(long reportId) {
        reportRepository.deleteById(reportId);
    }

    public void saveReport(Report report){
        reportRepository.save(report);
    }
}
