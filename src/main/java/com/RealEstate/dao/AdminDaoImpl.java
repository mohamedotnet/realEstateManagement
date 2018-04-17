package com.RealEstate.dao;

import com.RealEstate.model.Admin;
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
public class AdminDaoImpl implements AdminDao {

    @Autowired
    private SessionFactory sessionFactory ;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public Admin readAdminByIdNumber(String id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Admin WHERE idNumber = :id");
        query.setParameter("id", id);
        List list = query.getResultList();
        session.getTransaction().commit();
        session.close();
        if (list.size() > 0)
            return (Admin) list.get(0);
        return null;
    }
    public Admin readAdminByEmail(String email){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Admin WHERE email = :email");
        query.setParameter("email", email);
        List list = query.getResultList();
        session.getTransaction().commit();
        session.close();
        if (list.size() > 0)
            return (Admin) list.get(0);
        return null;
    }

    public Admin readAdminByUsername(String username) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Admin AS a WHERE a.username = :username");
        query.setParameter("username", username);
        List list = query.getResultList();
        session.getTransaction().commit();
        session.close();
        if(list.size() > 0)
            return (Admin) list.get(0);
        return null;
    }

    public void storeAdmin(Admin ad) {
        ad.setRole("admin");
        ad.setPicture("images/customer/defaultProfilePicture.jpg");
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(ad);
        session.getTransaction().commit();
        session.close();
    }

    public Admin readAdminById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Admin admin = session.get(Admin.class, id);
        session.getTransaction().commit();
        session.close();
        return admin;
    }
    public void updateAdmin(Admin  admin) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createNativeQuery("update Admin SET name = :name , last_name = :lastName ," +
                "sex = :sex , birthday = :birthday , phone = :phone , email = :email , id_number = :idNumber , address = :address , " +
                "password = :password , username =:username, picture = :picture WHERE id = :id");
        query.setParameter("name", admin.getName());
        query.setParameter("lastName", admin.getLastName());
        query.setParameter("username", admin.getUsername());
        query.setParameter("sex", admin.getSex());
        query.setParameter("birthday", admin.getBirthday());
        query.setParameter("email", admin.getEmail());
        query.setParameter("phone", admin.getPhone());
        query.setParameter("password", admin.getPassword());
        query.setParameter("address", admin.getAddress());
        query.setParameter("idNumber", admin.getIdNumber());
        query.setParameter("picture", admin.getPicture());
        query.setParameter("id", admin.getId());
        System.out.println(admin.getId());
        System.out.println(admin.getLastName());
        System.out.println(query.executeUpdate());
        session.getTransaction().commit();
        session.close();
    }

    public void deleteAdmin(String username) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("delete from Admin where username= :username");
        query.setParameter("username", username);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public boolean checkAdminLogin(String username, String password) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        boolean userExist = false;
        Query query = session.createQuery("FROM Admin AS a WHERE a.username = :username AND a.password = :password");
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
    public List<Admin> getAdminsList() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Admin");
        List list = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return (List<Admin>) list;
    }

    public String changeProfilePicture(MultipartFile file, String username) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String picture = username + "ProfilePicture" + ".jpg";
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                String rootPath = "C:\\Users\\B.IHAB\\workspace3\\RealEstate\\src\\main\\resources\\images";
                File dir = new File(rootPath + File.separator + "admin");
                File serverFile = new File(dir.getAbsolutePath() + File.separator + picture);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
            } catch (Exception e) {
                ;
            }
        }
        Query query = session.createQuery("update Admin set picture = :picture where username= :username");
        query.setParameter("username", username);
        query.setParameter("picture", "images/admin/" + picture);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return "images/admin/" + picture;
    }

    public Admin registerAdmin(String name, String lastName, String username, String sex, String birthday, String phone, String email, String password, String address, String idNumber) {
        Admin admin = new Admin();
        String picture = "images/customer/defaultProfilePicture.jpg";
        /*if (!file.isEmpty()) {
            picture = username + "PictureProfilePicture.jpg";
            try {
                byte[] bytes = file.getBytes();
                String rootPath = "C:\\Users\\B.IHAB\\workspace3\\RealEstate\\src\\main\\resources\\images";
                File dir = new File(rootPath + File.separator + "admin");
                File serverFile = new File(dir.getAbsolutePath() + File.separator + picture);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
            } catch (Exception e) {
                ;
            }
        }*/
        admin.setName(name);
        admin.setLastName(lastName);
        admin.setUsername(username);
        admin.setSex(sex);
        try {
            admin.setBirthday(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(birthday).getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        admin.setPhone(phone);
        admin.setEmail(email);
        admin.setPassword(password);
        admin.setAddress(address);
        admin.setIdNumber(idNumber);
        admin.setPicture("images/agent/" +picture);
        return admin;
    }

}
