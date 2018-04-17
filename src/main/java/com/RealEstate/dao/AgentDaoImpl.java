package com.RealEstate.dao;

import com.RealEstate.model.Agent;
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
public class AgentDaoImpl implements AgentDao {
    
    @Autowired
    private SessionFactory sessionFactory ;


    public void storeAgent(Agent ag) {
        ag.setLocality("Locality1");
        ag.setPicture("images/customer/defaultProfilePicture.jpg");
        ag.setRole("agent");
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(ag);
        session.getTransaction().commit();
        session.close();
    }

    public Agent readAgentByIdNumber(String id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Agent WHERE idNumber = :id");
        query.setParameter("id", id);
        List list = query.getResultList();
        session.getTransaction().commit();
        session.close();
        if (list.size() > 0)
            return (Agent) list.get(0);
        return null;
    }
    public Agent readAgentByEmail(String email){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Agent WHERE email = :email");
        query.setParameter("email", email);
        List list = query.getResultList();
        session.getTransaction().commit();
        session.close();
        if (list.size() > 0)
            return (Agent) list.get(0);
        return null;
    }
    public Agent readAgentById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Agent agent = session.get(Agent.class, id);
        session.getTransaction().commit();
        session.close();
        return agent;
    }

    public Agent readAgentByUsername(String username) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Agent AS c WHERE c.username = :username");
        query.setParameter("username", username);
        List list = query.getResultList();
        session.getTransaction().commit();
        session.close();
        if(list.size() > 0)
            return (Agent) list.get(0);
        return null;
    }

    public void updateAgent(Agent  agent) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createNativeQuery("update Agent SET name = :name , last_name = :lastName ," +
                "sex = :sex , birthday = :birthday , phone = :phone , email = :email , id_number = :idNumber , address = :address , " +
                "password = :password , username =:username, picture = :picture WHERE id = :id");
        query.setParameter("name", agent.getName());
        query.setParameter("lastName", agent.getLastName());
        query.setParameter("username", agent.getUsername());
        query.setParameter("sex", agent.getSex());
        query.setParameter("birthday", agent.getBirthday());
        query.setParameter("email", agent.getEmail());
        query.setParameter("phone", agent.getPhone());
        query.setParameter("password", agent.getPassword());
        query.setParameter("address", agent.getAddress());
        query.setParameter("idNumber", agent.getIdNumber());
        query.setParameter("picture", agent.getPicture());
        query.setParameter("id", agent.getId());
        System.out.println(agent.getId());
        System.out.println(agent.getLastName());
        System.out.println(query.executeUpdate());
        session.getTransaction().commit();
        session.close();
    }

    public void deleteAgent(String username) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("delete from Agent where username= :username");
        query.setParameter("username", username);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public boolean checkAgentLogin(String username, String password) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        boolean userExist = false;
        Query query = session.createQuery("FROM Agent AS a WHERE a.username = :username AND a.password = :password");
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
    public List<Agent> getAgentsList() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Agent");
        List list = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return (List<Agent>) list;
    }

    public String changeProfilePicture(MultipartFile file, String username) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String picture = username + "ProfilePicture" + ".jpg";
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                String rootPath = "C:\\Users\\B.IHAB\\workspace3\\RealEstate\\src\\main\\resources\\images";
                File dir = new File(rootPath + File.separator + "agent");
                File serverFile = new File(dir.getAbsolutePath() + File.separator + picture);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
            } catch (Exception e) {
                return "Agent/changeProfilePicture";
            }
        }
        Query query = session.createQuery("update Agent set picture = :picture where username= :username");
        query.setParameter("username", username);
        query.setParameter("picture", "images/agent/" + picture);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return "images/agent/" + picture;
    }


    @Override
    public Agent createAgent(String name, String lastName, String username, String sex, String birthday, String phone, String email, String password, String address, String idNumber) {
        Agent agent = new Agent();
        String picture = "images/customer/defaultProfilePicture.jpg";
        /*if (!file.isEmpty()) {
            picture = username + "PictureProfilePicture.jpg";
            try {
                byte[] bytes = file.getBytes();
                String rootPath = "C:\\Users\\B.IHAB\\workspace3\\RealEstate\\src\\main\\resources\\images";
                File dir = new File(rootPath + File.separator + "agent");
                File serverFile = new File(dir.getAbsolutePath() + File.separator + picture);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
            } catch (Exception e) {
                ;
            }
        }*/

        agent.setName(name);
        agent.setLastName(lastName);
        agent.setUsername(username);
        agent.setSex(sex);
        try {
            agent.setBirthday(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(birthday).getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        agent.setPhone(phone);
        agent.setEmail(email);
        agent.setPassword(password);
        agent.setAddress(address);
        agent.setIdNumber(idNumber);
        //agent.setLocality(locality);
        agent.setPicture("images/agent/" +picture);
        return agent;
    }
}
