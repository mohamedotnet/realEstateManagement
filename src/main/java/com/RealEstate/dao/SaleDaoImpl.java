package com.RealEstate.dao;

import com.RealEstate.model.PaymentReceipt;
import com.RealEstate.model.Sale;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;


@Repository
public class SaleDaoImpl implements SaleDao {

    @Autowired
    private SessionFactory sessionFactory ;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void storeSale(Sale s) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(s);
        session.getTransaction().commit();
        session.close();
    }

    public Sale readSaleById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Sale sale = session.get(Sale.class, id);
        session.getTransaction().commit();
        session.close();
        return sale;
    }

    public Sale readSaleByReference(String reference) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Sale AS s WHERE s.reference = :reference");
        query.setParameter("reference", reference);
        List list = query.getResultList();
        session.getTransaction().commit();
        session.close();
        if(list.size() > 0)
            return (Sale) list.get(0);
        return null;
    }

    public void updateSale(String  query) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createQuery(query).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public void deleteSale(Sale s) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Sale sale = session.get(Sale.class, s.getId());
        session.delete(sale);
        session.getTransaction().commit();
        session.close();
    }

    public void validateSale(String reference) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("UPDATE Sale AS s SET s.isValidated = true WHERE s.reference = :reference");
        query.setParameter("reference", reference);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public void validatePayment(String reference, PaymentReceipt paymentReceipt) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("UPDATE Sale AS s SET s.paymentReceipt = :paymentReceipt WHERE s.reference = :reference");
        query.setParameter("paymentReceipt", paymentReceipt.getId());
        query.setParameter("reference", reference);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    public List<Sale> getSalesList() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Sale");
        List list = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return (List<Sale>) list;
    }

    @SuppressWarnings("unchecked")
    public List<Sale> getSalesListByCustomer(String username) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Sale WHERE isValidated = false AND customer = :customer");
        query.setParameter("customer", username);
        List list = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return (List<Sale>) list;
    }
}
