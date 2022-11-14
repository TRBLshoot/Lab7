package ca.sait.dataaccess;

import ca.sait.entities.Role;
import ca.sait.entities.User;
import java.util.*;
import javax.persistence.*;
/**
 *
 * @author Dylan
 */
public class UserDB {
     EntityManagerFactory emFactory = DBUtil.getEmFactory();
     EntityManager eManager = emFactory.createEntityManager();
    public List<User> getAll() {
        return eManager.createNamedQuery("User.findAll", User.class).getResultList();
    }
    
    public User get(String email) throws Exception {
        User user = eManager.find(User.class, email);
        return user;
    }
    
    public void delete(User user) throws Exception {
        EntityTransaction eTransaction = eManager.getTransaction();
        try {
            eTransaction.begin();
            eManager.remove(eManager.merge(user));
            eTransaction.commit();
        } catch (Exception ex) {
            eTransaction.rollback();
        } finally {
            eManager.close();
        } 
    }
    
        public void update(User user) throws Exception {
        EntityTransaction eTransaction = eManager.getTransaction();
        try {
            eTransaction.begin();
            eManager.merge(user);
            eTransaction.commit();
        } catch (Exception ex) {
            eTransaction.rollback();
        } finally {
            eManager.close();
        } 
    }
        
        public void insert(User user) throws Exception {
        EntityTransaction eTransaction = eManager.getTransaction();
        try {
            eTransaction.begin();
            eManager.persist(user);
            eTransaction.commit();
        } catch (Exception ex) {
            eTransaction.rollback();
        } finally {
            eManager.close();
        } 
    }
}
