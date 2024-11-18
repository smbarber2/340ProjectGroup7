package com._Project.Tbay.Report;

import com._Project.Tbay.User.User;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/reports")

public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping("/all")
    public String getAllReports(Model model) {
        model.addAttribute("reportList", reportService.getAllReports());
        model.addAttribute("title", "All Reports");
        return "all-reports"; //Like the table from hw
    }

    @GetMapping("/{reportId}")
    public String getReport(@PathVariable long reportId, Model model) {
        model.addAttribute("report", reportService.getReportById(reportId));
        model.addAttribute("title", reportId);
        return "report-info";
    }

    //@PostMapping("/newReport")
    //public List<Report> addNewReport(@RequestBody Report report) {
    //    reportService.addNewReport(report);
    //    return reportService.getAllReports();
   // }

    @GetMapping("/createReportForm")
    public String showCreateForm(){
        return "report-create";
    }
    @PostMapping("/newReport")
    public String addNewReport(Report report) {
        reportService.saveReport(report);
        return "redirect:/reports/all";
    }


//    @PutMapping("/updateReport/{reportId}")
//    public Report updateListing(@PathVariable long reportId, @RequestBody Report report) {
//        reportService.updateReport(reportId, report);
//        return reportService.getReportById(reportId);
//    }

    @GetMapping("/updateReport/{reportId}")
    public String showUpdateReport(@PathVariable long reportId, @RequestBody Model model){
        model.addAttribute("report", reportService.getReportById(reportId));
        return "edit-report";
    }
    @PostMapping("/update")
    public String updateReport(Report report) {
        reportService.updateReport(report.getReportId(), report);
        return "redirect:/profile/" + report.getReportId();
    }


    @GetMapping("/deleteReport/{reportId}")
    public String deleteReportById(@PathVariable long reportId) {
        reportService.deleteReportById(reportId);
        return "redirect:/reports/all";
    }


}
