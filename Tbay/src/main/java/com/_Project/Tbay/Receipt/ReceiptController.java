package com._Project.Tbay.Receipt;

import com._Project.Tbay.Listing.Listing;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/receipts")
public class ReceiptController {
    @Autowired
    private ReceiptService receiptService;

    @GetMapping("/allReceipts")
    public String getAllReceipts(Model model) {
        model.addAttribute("receiptList", receiptService.getAllReceipts());
        model.addAttribute("receipt", "All receipts");
        return "receipt-list"; //2do
    }
    
    @GetMapping("/{receiptId}")
    public String getReceipt(@PathVariable long receiptId, Model model) {
        model.addAttribute("receipt", receiptService.getReceiptById(receiptId));
        model.addAttribute("title", receiptId);
        return "receipt.html"; //???
    }

    @PostMapping("/newReceipt")
    public String addNewReceipt(@RequestBody Receipt receipt) {
        receiptService.addNewReceipt(receipt);
        return "redirect:/Receipt/" + receipt.getReceiptId();
    }

//    @PutMapping("/updateReceipt/{receiptId}")
//    public Receipt updateReceipt(@PathVariable long receiptId, @RequestBody Receipt receipt) {
//        receiptService.updateReceipt(receiptId, receipt);
//        return receiptService.getReceiptById(receiptId);
//    }

    @DeleteMapping("/delete/{receiptId}")
    public String deleteReceiptById(@PathVariable long receiptId) {
        receiptService.deleteReceiptById(receiptId);
        return "redirect:/receipts/receipt-list";
    }

}
