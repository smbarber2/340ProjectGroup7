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
}
