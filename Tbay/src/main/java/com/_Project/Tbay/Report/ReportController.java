package com._Project.Tbay.Report;

import com._Project.Tbay.Admin.AdminService;
import com._Project.Tbay.User.User;
import com._Project.Tbay.User.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/reports")

public class ReportController {
    @Autowired
    private ReportService reportService;
    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminservice;

    @GetMapping("/all/{adminId}")
    public String getAllReports(@PathVariable long adminId,Model model) {
        model.addAttribute("admin", adminservice.getAdminById(adminId));
        model.addAttribute("reportList", reportService.getAllReports());
        model.addAttribute("title", "All Reports");
        return "all-reports"; //Like the table from hw
    }

    @GetMapping("/{adminId}/{reportId}")
    public String getReport(@PathVariable long reportId, @PathVariable long adminId, Model model) {
        model.addAttribute("admin", adminservice.getAdminById(adminId));
        model.addAttribute("report", reportService.getReportById(reportId));
        model.addAttribute("title", reportId);
        return "report-info";
    }

    //@PostMapping("/newReport")
    //public List<Report> addNewReport(@RequestBody Report report) {
    //    reportService.addNewReport(report);
    //    return reportService.getAllReports();
   // }

    @GetMapping("/createReportForm/{adminId}/{userId}")
    public String showCreateForm(@PathVariable long userId, @PathVariable long adminId, Model model){
        model.addAttribute("admin", adminservice.getAdminById(adminId));
        model.addAttribute("user", userService.getUserById(userId));
        model.addAttribute("title", userId);
        return "report-create";
    }
    @PostMapping("/newReport")
    public String addNewReport(@PathVariable long adminId, Report report, Model model) {
        model.addAttribute("admin", adminservice.getAdminById(adminId));
        reportService.saveReport(report);
        return "redirect:/reports/all/{adminId";
    }


//    @PutMapping("/updateReport/{reportId}")
//    public Report updateListing(@PathVariable long reportId, @RequestBody Report report) {
//        reportService.updateReport(reportId, report);
//        return reportService.getReportById(reportId);
//    }

    @GetMapping("/updateReport/{adminId}/{reportId}")
    public String showUpdateReport(@PathVariable long reportId, @PathVariable long adminId, Model model){
        model.addAttribute("admin", adminservice.getAdminById(adminId));
        model.addAttribute("report", reportService.getReportById(reportId));
        return "edit-report";
    }

    @PostMapping("/update")
    public String updateReport(@RequestParam long adminId, Report report, Model model) {
        model.addAttribute("admin", adminservice.getAdminById(adminId));
        reportService.addNewReport(report);
        return "redirect:/reports/" + report.getReportId();
    }


    @GetMapping("/deleteReport/{adminId}/{reportId}")
    public String deleteReportById(@PathVariable long reportId, @PathVariable long adminId, Model model) {
        model.addAttribute("admin", adminservice.getAdminById(adminId));
        reportService.deleteReportById(reportId);
        return "redirect:/reports/all/{adminId}";
    }

}
