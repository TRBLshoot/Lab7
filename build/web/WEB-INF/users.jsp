<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dylano's Users Table</title>
    </head>
    <table>
        <td>
            <div>
                <h2>List of Users:</h2>
                <table>
                    <tr>
                        <th>E-mail</th>
                        <th>First name</th>
                        <th>Last name</th>
                        <th>Active Status</th>
                        <th>Action</th>
                    </tr>

                    <tbody>
                        <c:forEach var="user" items="${users}"> 
                        <tr>
                            <td>${user.email}</td>
                            <td>${user.firstName}</td>
                            <td>${user.lastName}</td>
                            <td>${user.active}</td>
                            <td>
                                <a href="user?action=edit&user=${user.email}">Edit</a>
                                <a href="user?action=delete&user=${user.email}">Delete</a>
                            </td>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </td>
        <td>
            <div><form action="user" method="POST">
                <input type="hidden" name="action" value="commitEdit">
                <h2>Edit User</h2>
                <table>
                    <tr><td><label for="viewEmail">Email: </label></td><td><input type="text" name="viewEmail" value="<%= request.getAttribute("emailToEdit")%>" readonly="true"></td></tr>
                    <tr><td><label for="editFirstName">First Name: </label></td><td><input type="text" name="editFirstName" value="<%= request.getAttribute("editFirstName") %>"></td></tr>
                    <tr><td><label for="editLastName">Last Name: </label></td><td><input type="text" name="editLastName" value="<%= request.getAttribute("editLastName") %>"></td></tr>
                    <tr><td><label for="editRole"></label></td>
                        <td>
                                <select name="editRole">
                                    <option>Select Role</option>
                                    <c:forEach var="role" items="${roles}">
                                    <option>${role.roleName}</option>
                                    </c:forEach>
                                </select>
                            
                        </td>
                    </tr>
                </table>
                <button type="submit">Submit</button>
            </form>
            </div>
        </td>
        <td>
            <div>
                <form name="addUser" action="user" method="POST">
                <input type="hidden" name="action" value="addUser">
                <table>
                    <h2>Add a New User:</h2>
                    <tr><td><label for="inputEmail">Email: </label></td><td><input type="email" name="inputEmail"></td></tr>
                    <tr><td><label for="inputFirstName">First Name: </label></td><td><input type="text" name="inputFirstName" ></td></tr>
                    <tr><td><label for="inputLastName">Last Name: </label></td><td><input type="text" name="inputLastName" ></td></tr>
                    <tr><td><label for="inputPassword">Password: </label></td><td><input type="password" name="inputPassword" ></td></tr>
                    <tr><td><label for="selectRole"></label></td>
                        <td>
                            <select name="selectRole">
                                <option>Select Role</option>
                                <c:forEach var="role" items="${roles}">
                                    <option>${role.roleName}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                </table>
            <button type="submit">Add</button>
            </form>
            </div>
        </td>
    
    
    
</html>
