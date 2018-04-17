package com.RealEstate.dao;

import com.RealEstate.model.PaymentReceipt;
import com.RealEstate.model.Sale;

import java.util.List;

public interface SaleDao {

    void storeSale(Sale sale);
    Sale readSaleById(int id);
    Sale readSaleByReference(String reference);
    void updateSale(String query);
    void deleteSale(Sale sale);
    void validateSale(String reference);
    void validatePayment(String reference, PaymentReceipt paymentReceipt);
    List<Sale> getSalesList();
    List<Sale> getSalesListByCustomer(String username);

}
