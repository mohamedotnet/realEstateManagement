package com.RealEstate.dao;

import com.RealEstate.model.Contract;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Repository
public class ContractDaoImpl implements ContractDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void storeContract(Contract contract) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(contract);
        session.getTransaction().commit();
        session.close();
    }

    public Contract readContractById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Contract contract = session.get(Contract.class, id);
        session.beginTransaction().commit();
        session.close();
        return contract;
    }

    public void updateContract(Contract contract) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("UPDATE Contract SET date = :date , time = :time , " +
                "content = :content , manager = :manager , " +
                "sale = :sale , file = :file WHERE id = :id");
        query.setParameter("date", contract.getDate());
        query.setParameter("time", contract.getTime());
        query.setParameter("content", contract.getContent());
        query.setParameter("manager", contract.getManager());
        query.setParameter("sale", contract.getSale());
        query.setParameter("file", contract.getFile());
        query.setParameter("id", contract.getId());
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public void deleteContract(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Contract contract = session.get(Contract.class, id);
        session.delete(contract);
        session.getTransaction().commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    public List<Contract> getContractsList() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        javax.persistence.Query query = session.createQuery("FROM Contract ");
        List list = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return (List<Contract>) list;
    }

    public Contract createContract(MultipartFile file, String sale, String content, String date, String time, String manager) {
        Contract contract = new Contract();
        contract.setContent(content);
        contract.setManager(manager);
        try {
            contract.setDate(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(date).getTime()));
            contract.setTime(java.sql.Time.valueOf(time));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        contract.setSale(sale);
        String picture = sale + "contract.jpg";
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                String rootPath = "C:\\Users\\B.IHAB\\workspace3\\RealEstate\\src\\main\\resources\\images";
                File dir = new File(rootPath + File.separator + "contract");
                File serverFile = new File(dir.getAbsolutePath() + File.separator + picture);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
            } catch (Exception e) {
                ;
            }
        }
        contract.setFile("images/contract/" + picture);
        return contract;
    }
}