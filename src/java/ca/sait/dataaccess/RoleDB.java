package ca.sait.dataaccess;
import ca.sait.entities.Role;
import java.util.*;
import javax.persistence.*;

/**
 *
 * @author Dylan
 */
public class RoleDB {
    public List<Role> getAll() {
        EntityManagerFactory emFactory = DBUtil.getEmFactory();
        EntityManager eManager = emFactory.createEntityManager();
        return eManager.createNamedQuery("Role.findAll", Role.class).getResultList();
    }
}
