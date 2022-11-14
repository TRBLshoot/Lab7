package ca.sait.servlets;
import ca.sait.services.*;
import ca.sait.entities.*;

import java.util.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dylan
 */
public class UserServlet extends HttpServlet {
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        UserService userService = new UserService();
        RoleService roleService = new RoleService();
        
        String emailToEdit = "";
        String editFirstName = "";
        String editLastName = "";
        
        List<User> userList = null;
        List<Role> roleList = null;
        
        try {
            userList = userService.getAll();
            roleList = roleService.getAll();
            request.setAttribute("users", userList);
            request.setAttribute("roles", roleList);
            
            String actionType = request.getParameter("action");
            
            if(actionType != null && actionType.equals("delete")) {
                userService.delete(request.getParameter("user"));
                request.getSession().invalidate();
                response.sendRedirect("user");
            } else if(actionType != null && actionType.equals("edit")) {
                emailToEdit = request.getParameter("user");
               
                for(int x = 0; x < userList.size(); x++)
                {
                    String check = userList.get(x).getEmail();
                    if(emailToEdit.equals(check))
                    {
                        emailToEdit = userList.get(x).getEmail();
                        editFirstName = userList.get(x).getFirstName();
                        editLastName = userList.get(x).getLastName();
                    }
                }
            }
            request.setAttribute("emailToEdit", emailToEdit);
            request.setAttribute("editFirstName", editFirstName);
            request.setAttribute("editLastName", editLastName);
            this.getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
    }  

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserService userService = new UserService();
        RoleService roleService = new RoleService();
        List<User> userList = null;
        List<Role> roleList = null;
        
        try {
            userList = userService.getAll();
            roleList = roleService.getAll();
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        String action = request.getParameter("action");

        if(action.equals("addUser"))
            {
                String email = request.getParameter("inputEmail");
                String firstName = request.getParameter("inputFirstName");
                String lastName = request.getParameter("inputLastName");
                String password = request.getParameter("inputPassword");
                String roleName = request.getParameter("selectRole");
                Role role = null;

                for(int x = 0; x < roleList.size(); x++)
                {
                    if(roleList.get(x).getRoleName().equals(roleName))
                    {
                        role = roleList.get(x);
                    }
                }
                 
            try {
                
                    userService.insert(email, true, firstName, lastName, password, role);
                } 
                    catch (Exception ex) {
                    Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if(action.equals("commitEdit")) {
                String emailAddress = request.getParameter("viewEmail");
                String firstName = request.getParameter("editFirstName");
                String lastName = request.getParameter("editLastName");
                String roleName = request.getParameter("editRole");
                Role role = null;
                try {
                    for(int x = 0; x < roleList.size(); x++)
                    {
                        if(roleList.get(x).getRoleName().equals(roleName))
                        {
                            role = roleList.get(x);
                        }
                    }
                    
                    for(int x = 0; x < userList.size(); x++)
                    {
                        String check = userList.get(x).getEmail();
                        if(emailAddress.equals(check))
                        {
                            userService.update(userList.get(x).getEmail(),userList.get(x).getActive(),firstName,lastName,userList.get(x).getPassword(),role);
                        }
                    }
                    
                } 
                    catch (Exception ex) {
                    Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        request.getSession().invalidate();
        response.sendRedirect("user");
    }
}
