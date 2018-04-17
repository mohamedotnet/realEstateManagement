package com.RealEstate.dao;

import com.RealEstate.model.Manager;
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
public class ManagerDaoImpl implements ManagerDao {

    @Autowired
    private SessionFactory sessionFactory ;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void storeManager(Manager m) {
        m.setPicture("images/customer/defaultProfilePicture.jpg");//TODO:modify
        m.setRole("manager");
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(m);
        session.getTransaction().commit();
        session.close();
    }
    public Manager readManagerByIdNumber(String id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Manager WHERE idNumber = :id");
        query.setParameter("id", id);
        List list = query.getResultList();
        session.getTransaction().commit();
        session.close();
        if (list.size() > 0)
            return (Manager) list.get(0);
        return null;
    }
    public Manager readManagerByEmail(String email){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Manager WHERE email = :email");
        query.setParameter("email", email);
        List list = query.getResultList();
        session.getTransaction().commit();
        session.close();
        if (list.size() > 0)
            return (Manager) list.get(0);
        return null;
    }
    public Manager readManagerById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Manager manager = session.get(Manager.class, id);
        session.getTransaction().commit();
        session.close();
        return manager;
    }

    public Manager readManagerByUsername(String username) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Manager AS c WHERE c.username = :username");
        query.setParameter("username", username);
        List list = query.getResultList();
        session.getTransaction().commit();
        session.close();
        if(list.size() > 0)
            return (Manager) list.get(0);
        return null;
    }

    public void updateManager(Manager manager) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("update Manager SET name = :name , lastName = :lastName ," +
                "sex = :sex , birthday = :birthday , phone = :phone , email = :email , idNumber = :idNumber , address = :address , " +
                "password = :password , picture = :picture WHERE username = :username");
        query.setParameter("name", manager.getName());
        query.setParameter("lastName", manager.getLastName());
        query.setParameter("username", manager.getUsername());
        query.setParameter("sex", manager.getSex());
        query.setParameter("birthday", manager.getBirthday());
        query.setParameter("email", manager.getEmail());
        query.setParameter("phone", manager.getPhone());
        query.setParameter("password", manager.getPassword());
        query.setParameter("address", manager.getAddress());
        query.setParameter("idNumber", manager.getIdNumber());
        query.setParameter("picture", manager.getPicture());
        query.executeUpdate();
        query = session.createQuery("update Manager set username = :username where email = :email");
        query.setParameter("username", manager.getUsername());
        query.setParameter("email", manager.getEmail());
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public void deleteManager(String username) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("delete from Manager where username= :username");
        query.setParameter("username", username);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public boolean checkManagerLogin(String username, String password) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        boolean userExist = false;
        Query query = session.createQuery("FROM Manager AS r WHERE r.username = :username AND r.password = :password");
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
    public List<Manager> getManagersList() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Manager");
        List list = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return (List<Manager>) list;
    }

    public String changeProfilePicture(MultipartFile file, String username) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String picture = username + "ProfilePicture" + ".jpg";
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                String rootPath = "C:\\Users\\B.IHAB\\workspace3\\RealEstate\\src\\main\\resources\\images";
                File dir = new File(rootPath + File.separator + "manager");
                File serverFile = new File(dir.getAbsolutePath() + File.separator + picture);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
            } catch (Exception e) {
                ;
            }
        }
        Query query = session.createQuery("update Manager set picture = :picture where username= :username");
        query.setParameter("username", username);
        query.setParameter("picture", "images/manager/" + picture);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return "images/manager/" + picture;
    }

    public Manager storeManager(String name, String lastName, String username, String sex, String birthday, String phone, String email, String password, String address, String idNumber) {
        Manager manager = new Manager();
        String picture = "images/customer/defaultProfilePicture.jpg";
        /*if (!file.isEmpty()) {
            picture = username + "PictureProfilePicture.jpg";
            try {
                byte[] bytes = file.getBytes();
                String rootPath = "C:\\Users\\B.IHAB\\workspace3\\RealEstate\\src\\main\\resources\\images";
                File dir = new File(rootPath + File.separator + "manager");
                File serverFile = new File(dir.getAbsolutePath() + File.separator + picture);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
            } catch (Exception e) {
                ;
            }
        }*/
        manager.setName(name);
        manager.setLastName(lastName);
        manager.setUsername(username);
        manager.setSex(sex);
        try {
            manager.setBirthday(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(birthday).getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        manager.setPhone(phone);
        manager.setEmail(email);
        manager.setPassword(password);
        manager.setAddress(address);
        manager.setIdNumber(idNumber);
        manager.setPicture("images/customer/" + picture);
        return manager;
    }
}
