package com.RealEstate.dao;

import com.RealEstate.model.Operator;
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
public class OperatorDaoImpl implements OperatorDao {
    
    @Autowired
    private SessionFactory sessionFactory ;

    public void storeOperator(Operator opp) {
        opp.setRole("operator");
        opp.setPicture("images/customer/defaultProfilePicture.jpg");
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(opp);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Operator readOperatorByIdNumber(String id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Operator WHERE idNumber = :id");
        query.setParameter("id", id);
        List list = query.getResultList();
        session.getTransaction().commit();
        session.close();
        if (list.size() > 0)
            return (Operator) list.get(0);
        return null;
    }

    @Override
    public Operator readOperatorByEmail(String email) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Operator WHERE email = :email");
        query.setParameter("email", email);
        List list = query.getResultList();
        session.getTransaction().commit();
        session.close();
        if (list.size() > 0)
            return (Operator) list.get(0);
        return null;
    }

    public Operator readOperatorById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Operator operator = session.get(Operator.class, id);
        session.getTransaction().commit();
        session.close();
        return operator;
    }

    public Operator readOperatorByUsername(String username) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Operator AS o WHERE o.username = :username");
        query.setParameter("username", username);
        List list = query.getResultList();
        session.getTransaction().commit();
        session.close();
        if(list.size() > 0)
            return (Operator) list.get(0);
        return null;
    }

    public void updateOperator(Operator operator) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("update Operator SET name = :name , lastName = :lastName ," +
                "sex = :sex , birthday = :birthday , phone = :phone , email = :email , idNumber = :idNumber , address = :address , " +
                "password = :password , picture = :picture WHERE username = :username");
        query.setParameter("name", operator.getName());
        query.setParameter("lastName", operator.getLastName());
        query.setParameter("username", operator.getUsername());
        query.setParameter("sex", operator.getSex());
        query.setParameter("birthday", operator.getBirthday());
        query.setParameter("email", operator.getEmail());
        query.setParameter("phone", operator.getPhone());
        query.setParameter("password", operator.getPassword());
        query.setParameter("address", operator.getAddress());
        query.setParameter("idNumber", operator.getIdNumber());
        query.setParameter("picture", operator.getPicture());
        query.executeUpdate();
        query = session.createQuery("update Operator set username = :username where email = :email");
        query.setParameter("username", operator.getUsername());
        query.setParameter("email", operator.getEmail());
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public void deleteOperator(String username) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("delete from Operator where username= :username");
        query.setParameter("username", username);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public boolean checkOperatorLogin(String username, String password) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        boolean userExist = false;
        Query query = session.createQuery("FROM Operator AS o WHERE o.username = :username AND o.password = :password");
        query.setParameter("username", username);
        query.setParameter("password", password);
        List list = query.getResultList();
        if(list != null && list.size() > 0)
            userExist = true;
        session.getTransaction().commit();
        session.close();
        return userExist;
    }

    @SuppressWarnings("unchecked")
    public List<Operator> getOperatorsList() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Operator");
        List list = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return (List<Operator>) list;
    }

    public String changeProfilePicture(MultipartFile file, String username) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String picture = username + "ProfilePicture" + ".jpg";
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                String rootPath = "C:\\Users\\Sassi\\Documents\\RealEstate-master\\src\\main\\resources\\images";
                File dir = new File(rootPath + File.separator + "operator");
                File serverFile = new File(dir.getAbsolutePath() + File.separator + picture);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
            } catch (Exception e) {
                ;
            }
        }
        Query query = session.createQuery("update Operator set picture = :picture where username= :username");
        query.setParameter("username", username);
        query.setParameter("picture", "images/operator/" + picture);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return "images/operator/" + picture;
    }

    public Operator storeOperator(String name, String lastName, String username, String sex, String birthday, String phone, String email, String password, String address, String idNumber) {
        Operator operator = new Operator();
        String picture = "images/customer/defaultProfilePicture.jpg";
        /*if (!file.isEmpty()) {
            picture = username + "PictureProfilePicture.jpg";
            try {
                byte[] bytes = file.getBytes();
                String rootPath = "C:\\Users\\B.IHAB\\workspace3\\RealEstate\\src\\main\\resources\\images";
                File dir = new File(rootPath + File.separator + "operator");
                File serverFile = new File(dir.getAbsolutePath() + File.separator + picture);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
            } catch (Exception e) {
                ;
            }
        }*/
        operator.setName(name);
        operator.setLastName(lastName);
        operator.setUsername(username);
        operator.setSex(sex);
        try {
            operator.setBirthday(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(birthday).getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        operator.setPhone(phone);
        operator.setEmail(email);
        operator.setPassword(password);
        operator.setAddress(address);
        operator.setIdNumber(idNumber);
        operator.setPicture("images/customer/" + picture);
        return operator;
    }
}
