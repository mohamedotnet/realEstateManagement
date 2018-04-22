package com.RealEstate.dao;

import com.RealEstate.model.Customer;
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
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
    private SessionFactory sessionFactory ;

    public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

    public void storeCustomer(Customer c){
	        c.setRole("customer");
            c.setPicture("images/customer/defaultProfilePicture.jpg");//TODO: modify with gender
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(c);
            session.getTransaction().commit();
            session.close();
    }

    public Customer readCustomerById(String id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Customer WHERE idNumber = :id");
        query.setParameter("id", id);
        List list = query.getResultList();
        System.out.println(list);
        session.getTransaction().commit();
        session.close();
        System.out.println(list.size());
        if (list.size() > 0)
            return (Customer) list.get(0);
        return null;
    }

    public Customer readCustomerByEmail(String email){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Customer WHERE email = :email");
        query.setParameter("email", email);
        List list = query.getResultList();
        session.getTransaction().commit();
        session.close();
        if (list.size() > 0)
            return (Customer) list.get(0);
        return null;
    }
    public Customer readCustomerByUsername(String username) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Customer AS c WHERE c.username = :username");
        query.setParameter("username", username);
        List list = query.getResultList();
        session.getTransaction().commit();
        session.close();
        if(list.size() > 0)
            return (Customer) list.get(0);
        return null;
    }

    public void updateCustomer(Customer  customer) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("update Customer SET name = :name , lastName = :lastName ," +
                "sex = :sex , birthday = :birthday , phone = :phone , email = :email , idNumber = :idNumber , address = :address , " +
                "password = :password , picture = :picture WHERE username = :username");
        query.setParameter("name", customer.getName());
        query.setParameter("lastName", customer.getLastName());
        query.setParameter("username", customer.getUsername());
        query.setParameter("sex", customer.getSex());
        query.setParameter("birthday", customer.getBirthday());
        query.setParameter("email", customer.getEmail());
        query.setParameter("phone", customer.getPhone());
        query.setParameter("password", customer.getPassword());
        query.setParameter("address", customer.getAddress());
        query.setParameter("idNumber", customer.getIdNumber());
        query.setParameter("picture", customer.getPicture());
        query.executeUpdate();
        query = session.createQuery("update Customer set username = :username where email = :email");
        query.setParameter("username", customer.getUsername());
        query.setParameter("email", customer.getEmail());
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public void deleteCustomer(Customer c) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Customer customer = session.get(Customer.class, c.getId());
        session.delete(customer);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteCustomer(String username) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("delete from Customer where username= :username");
        query.setParameter("username", username);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public boolean isValidated(String username){
        boolean isValide = false;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Customer WHERE username = :username AND isActivated = TRUE");
        query.setParameter("username", username);
        if (!query.getResultList().isEmpty())
            isValide = true;
        session.getTransaction().commit();
        session.close();
        return isValide;
    }
    public boolean checkCustomerLogin(String username, String password) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        boolean userExist = false;
        Query query = session.createQuery("FROM Customer AS c WHERE c.username = :username AND c.password = :password");
        query.setParameter("username", username);
        query.setParameter("password", password);
        List list = query.getResultList();
        if(list != null && list.size() > 0)
            userExist = true;
        session.getTransaction().commit();
        session.close();
        return userExist;
    }

    public void validateAccount(String username) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("update Customer set isActivated=true where username= :username");
        query.setParameter("username", username);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    public List<Customer> getCustomersList() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Customer");
        List list = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return (List<Customer>) list;
    }

    @SuppressWarnings("unchecked")
    public List<Customer> getCustomerListUnActive(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Customer WHERE isActivated=false");
        List list = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return (List<Customer>) list;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Customer> getCustomerByName(String name) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Customer WHERE name=:name");
        query.setParameter("name", name);
        List list = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return (List<Customer>) list;
    }

    public String changeProfilePicture(MultipartFile file, String username) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String picture = username + "ProfilePicture" + ".jpg";
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                String rootPath = "C:\\Users\\B.IHAB\\workspace3\\RealEstate\\src\\main\\resources\\images";
                File dir = new File(rootPath + File.separator + "customer");
                File serverFile = new File(dir.getAbsolutePath() + File.separator + picture);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
            } catch (Exception e) {
                ;
            }
        }
        Query query = session.createQuery("update Customer set picture = :picture where username= :username");
        query.setParameter("username", username);
        query.setParameter("picture", "images/customer/" + picture);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return "images/customer/" + picture;
    }

    public Customer createCustomer(String name, String lastName, String username, String sex, String birthday, String phone, String email, String password, String address, String idNumber) {
        Customer customer = new Customer();
        String picture = "images/customer/defaultProfilePicture.jpg";
        /*if (!file.isEmpty()) {
            picture = username + "ProfilePicture.jpg";
            try {
                byte[] bytes = file.getBytes();
                String rootPath = "C:\\Users\\B.IHAB\\workspace3\\RealEstate\\src\\main\\resources\\images";
                File dir = new File(rootPath + File.separator + "customer");
                File serverFile = new File(dir.getAbsolutePath() + File.separator + picture);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
            } catch (Exception e) {
                ;
            }
        }*/
        customer.setName(name);
        customer.setLastName(lastName);
        customer.setUsername(username);
        customer.setSex(sex);
        try {
            customer.setBirthday(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(birthday).getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        customer.setPhone(phone);
        customer.setEmail(email);
        customer.setPassword(password);
        customer.setAddress(address);
        customer.setIdNumber(idNumber);
        customer.setPicture("images/customer/" + picture);

        return customer;
    }
}
