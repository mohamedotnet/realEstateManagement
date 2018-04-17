package com.RealEstate.dao;

import com.RealEstate.model.PaymentReceipt;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Query;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Repository
public class PaymentReceiptDaoImpl implements PaymentReceiptDao {

    @Autowired
    private SessionFactory sessionFactory ;

    public PaymentReceiptDaoImpl() {

    }

    public void storePaymentReceipt(PaymentReceipt pr) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        System.out.println(pr.getPicture());
        session.save(pr);
        session.getTransaction().commit();
        session.close();
    }

    public PaymentReceipt readPaymentReceipt(PaymentReceipt pr) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        PaymentReceipt paymentReceipt = session.get(PaymentReceipt.class, pr.getId());
        session.getTransaction().commit();
        session.close();
        return paymentReceipt;
    }

    public void updatePaymentReceipt(String  query) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createQuery(query).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public void deletePaymentReceipt(PaymentReceipt pr) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        PaymentReceipt paymentReceipt = session.get(PaymentReceipt.class, pr.getId());
        session.delete(paymentReceipt);
        session.getTransaction().commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    public List<PaymentReceipt> getPaymentReceiptsList() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM PaymentReceipt");
        List list = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return (List<PaymentReceipt>) list;
    }

    public PaymentReceipt createPaymentReceipt(MultipartFile file, int value, String bank, String date, String time, String customer) {
        PaymentReceipt paymentReceipt = new PaymentReceipt();
        paymentReceipt.setBank(bank);
        paymentReceipt.setCustomer(customer);
        try {
            paymentReceipt.setDate(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(date).getTime()));
            paymentReceipt.setTime(java.sql.Time.valueOf(time));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        paymentReceipt.setValue(value);
        String picture = customer + "paymentReceipt.jpg";
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                String rootPath = "C:\\Users\\B.IHAB\\workspace3\\RealEstate\\src\\main\\resources\\images";
                File dir = new File(rootPath + File.separator + "paymentReceipt");
                File serverFile = new File(dir.getAbsolutePath() + File.separator + picture);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
            } catch (Exception e) {
                ;
            }
        }
        paymentReceipt.setPicture("images/paymentReceipt/" + picture);
        return paymentReceipt;
    }

}