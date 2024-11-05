package com._Project.Tbay.Receipt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiptService {
    @Autowired
    private ReceiptRepository receiptRepository;

    public List<Receipt> getAllReceipts() {
        return receiptRepository.findAll();
    }

    public Receipt getReceiptById(long receiptId) {
        return receiptRepository.findById(receiptId).orElse(null);
    }

    public void addNewReceipt(Receipt receipt) {
        receiptRepository.save(receipt);
    }

    public void updateReceipt(long receiptId, Receipt receipt) {
        Receipt existing = getReceiptById(receiptId);
        existing.setItems(existing.getItems());
        existing.setPrice(existing.getPrice());
        existing.setDate(existing.getDate());

        receiptRepository.save(existing);
    }

    public void deleteReceiptById(long receiptId){
        receiptRepository.deleteById(receiptId);
    }

}
