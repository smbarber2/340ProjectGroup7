package com._Project.Tbay.Report;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/reports")

public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping("/allReports")
    public List<Report> getAllReports() {
        return reportService.getAllReports();
    }

    @GetMapping("/{reportId}")
    public Report getReport(@PathVariable long reportId) {
        return reportService.getReportById(reportId);
    }

    @PostMapping("/newReport")
    public List<Report> addNewReport(@RequestBody Report report) {
        reportService.addNewReport(report);
        return reportService.getAllReports();
    }

    @PutMapping("/updateReport/{reportId}")
    public Report updateListing(@PathVariable long reportId, @RequestBody Report report) {
        reportService.updateReport(reportId, report);
        return reportService.getReportById(reportId);
    }

    @DeleteMapping("/deleteReport/{reportId}")
    public List<Report> deleteReportById(@PathVariable long reportId) {
        reportService.deleteReportById(reportId);
        return reportService.getAllReports();
    }
}
