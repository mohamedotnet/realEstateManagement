package com.RealEstate.dao;

import com.RealEstate.model.PaymentReceipt;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PaymentReceiptDao {
    void storePaymentReceipt(PaymentReceipt paymentReceipt);
    PaymentReceipt readPaymentReceipt(PaymentReceipt paymentReceipt);
    void updatePaymentReceipt(String query);
    void deletePaymentReceipt(PaymentReceipt paymentReceipt);
    List<PaymentReceipt> getPaymentReceiptsList();
    PaymentReceipt createPaymentReceipt(MultipartFile file, int value, String bank, String date, String time, String customer);
}
