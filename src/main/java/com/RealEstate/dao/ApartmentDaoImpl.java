package com.RealEstate.dao;

import com.RealEstate.model.Apartment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Query;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ApartmentDaoImpl implements ApartmentDao {

    @Autowired
    private SessionFactory sessionFactory ;

    public ApartmentDaoImpl() {

    }

    public void storeApartment(Apartment ap) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(ap);
        session.getTransaction().commit();
        session.close();
    }

    public Apartment readApartmentById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Apartment apartment = session.get(Apartment.class, id);
        session.getTransaction().commit();
        session.close();
        return apartment;
    }

    public Apartment readApartmentByReference(String reference) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Apartment AS a WHERE a.reference = :reference");
        query.setParameter("reference", reference);
        List list = query.getResultList();
        session.getTransaction().commit();
        session.close();
        if(list.size() > 0)
            return (Apartment) list.get(0);
        return null;
    }

    public void updateApartment(String  query) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createQuery(query).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public void deleteApartment(Apartment ap) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Apartment apartment = session.get(Apartment.class, ap.getId());
        session.delete(apartment);
        session.getTransaction().commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    public List<Apartment> apartmentList() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Apartment ");
        List list = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return (List<Apartment>) list;
    }

    public void validatePayment(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("UPDATE Apartment AS a SET a.status = true WHERE a.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    public List<Apartment> customApartmentsList(String floor, String nbrBalcony, String locality, String type, String minPrice, String maxPrice, String minSurface, String maxSurface) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Apartment apartment = new Apartment();
        apartment.setType(type);
        apartment.setSurface(Integer.parseInt(maxSurface.substring(0, maxSurface.length() - 6)));
        apartment.setPrice(Integer.parseInt(maxPrice.substring(4, maxPrice.length() - 3)));
        if(floor.equals(""))
            apartment.setFloor(-1);
        else
            apartment.setFloor(Integer.parseInt(floor));
        if(nbrBalcony.equals(""))
            apartment.setNbrBalcony(-1);
        else
            apartment.setNbrBalcony(Integer.parseInt(nbrBalcony));
        String q = "";
        if(!locality.equals("all"))
            q = "SELECT new Apartment (a.id, a.reference, a.type, a.floor, a.price, a.building, a.surface, a.nbrRoom, a.nbrBalcony, a.status, a.picture1, a.picture2, a.picture3, a.picture4, a.picture5) FROM Apartment as a, Building as b where a.building = b.id and locality = :locality and ";
        else
            q = "FROM Apartment where ";

        q += "price >= :minPrice and price <= :maxPrice and surface >= :minSurface and surface <= :maxSurface ";

        if(!apartment.getType().equals("all"))
            q += "and type = :type ";
        if(apartment.getFloor() != -1)
            q += "and floor = :floor ";
        if(apartment.getNbrBalcony() != -1)
            q += "and nbrBalcony = :nbrBalcony ";
        System.out.println(q);
        Query query = session.createQuery(q);
        if(apartment.getFloor() != -1)
            query.setParameter("floor", apartment.getFloor());
        if(!apartment.getType().equals("all"))
            query.setParameter("type", apartment.getType());
        if(apartment.getNbrBalcony() != -1)
            query.setParameter("nbrBalcony", apartment.getNbrBalcony());
        if(!locality.equals("all"))
            query.setParameter("locality", locality);
        query.setParameter("maxPrice", apartment.getPrice());
        query.setParameter("maxSurface", apartment.getSurface());
        query.setParameter("minPrice", Integer.parseInt(minPrice));
        query.setParameter("minSurface", Integer.parseInt(minSurface));
        List list = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return (List<Apartment>) list;
    }

    public Apartment createApartment(String reference, String price, String floor, String type, String nbrRoom, String building, String surface, String nbrBalcony, MultipartFile[] pictures) {
        Apartment apartment = new Apartment();
        String [] pics = {null, null, null, null, null};
        for(int i=0; i<pictures.length; i++){
            if (!pictures[i].isEmpty()) {
                pics[i] = reference + "Picture" + i + ".jpg";
                try {
                    byte[] bytes = pictures[i].getBytes();
                    String rootPath = "C:\\Users\\B.IHAB\\workspace3\\RealEstate\\src\\main\\resources\\images\\apartment";
                    File dir = new File(rootPath + File.separator + reference);
                    if (!dir.exists())
                        dir.mkdirs();
                    File serverFile = new File(dir.getAbsolutePath() + File.separator + pics[i]);
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                    stream.write(bytes);
                    stream.close();
                } catch (Exception e) {
                    ;
                }
            }
        }
        apartment.setReference(reference);
        apartment.setType(type);
        apartment.setBuilding(Integer.parseInt(building));
        apartment.setFloor(Integer.parseInt(floor));
        apartment.setNbrBalcony(Integer.parseInt(nbrBalcony));
        apartment.setNbrRoom(Integer.parseInt(nbrRoom));
        apartment.setPrice(Integer.parseInt(price));
        apartment.setSurface(Integer.parseInt(surface));
        apartment.setPicture1("images/apartment/" + reference + "/" +pics[0]);
        apartment.setPicture2("images/apartment/" + reference + "/" + pics[1]);
        apartment.setPicture3("images/apartment/" + reference + "/" + pics[2]);
        apartment.setPicture4("images/apartment/" + reference + "/" + pics[3]);
        apartment.setPicture5("images/apartment/" + reference + "/" + pics[4]);
        return apartment;
    }
}