package ca.sait.services;

import ca.sait.dataaccess.RoleDB;
import ca.sait.entities.Role;
import java.util.List;

/**
 *
 * @author Dylan
 */
public class RoleService {
    public List<Role> getAll() {
        RoleDB roledb = new RoleDB();
        List<Role> roles = roledb.getAll();
        return roles;
    }
}
