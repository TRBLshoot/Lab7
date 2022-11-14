package ca.sait.services;
import ca.sait.dataaccess.UserDB;
import ca.sait.entities.*;
import java.util.*;

/**
 *
 * @author Dylan
 */
public class UserService {
    UserDB userdb = new UserDB();
    
    public List<User> getAll() {
        List<User> users = userdb.getAll();
        return users;
    }
    
     public User get(String email) throws Exception {
        User user = this.userdb.get(email);
        return user;
    }
     
     public void insert(String email, boolean active, String firstName, String lastName, String password, Role role) throws Exception {
        User user = new User(email, active, firstName, lastName, password);
        user.setRole(role);
        userdb.insert(user);
    }
    
    public void update(String email, boolean active, String firstName, String lastName, String password, Role role) throws Exception {
        User user = new User(email, active, firstName, lastName, password);
        user.setRole(role);
        userdb.update(user);
    }
    
    public void delete(String email) throws Exception {
        userdb.delete(userdb.get(email));
    }
}