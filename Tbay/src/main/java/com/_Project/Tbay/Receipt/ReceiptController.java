package com._Project.Tbay.Receipt;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {
    @Autowired
    private ReceiptService receiptService;

    @GetMapping("/allReceipts")
    public List<Receipt> getAllReceipts() {
        return receiptService.getAllReceipts();
    }

    @GetMapping("/{receiptId}")
    public Receipt getReceipt(@PathVariable long receiptId) {
        return receiptService.getReceiptById(receiptId);
    }

    @PostMapping("/newReceipt")
    public List<Receipt> addNewReceipt(@RequestBody Receipt receipt) {
        receiptService.addNewReceipt(receipt);
        return receiptService.getAllReceipts();
    }

    @PutMapping("/updateReceipt/{receiptId}")
    public Receipt updateReceipt(@PathVariable long receiptId, @RequestBody Receipt receipt) {
        receiptService.updateReceipt(receiptId, receipt);
        return receiptService.getReceiptById(receiptId);
    }

    @DeleteMapping("/delete/{receiptId}")
    public List<Receipt> deleteReceiptById(@PathVariable long receiptId) {
        receiptService.deleteReceiptById(receiptId);
        return receiptService.getAllReceipts();
    }

}
