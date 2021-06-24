<%-- 
    Document   : users
    Created on : Jun 24, 2021, 1:00:10 PM
    Author     : 844568
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Users</h1>


        <div class="container">
            <h2>Add User</h2>
            <form method="post" action="user">

                <label for="email">Email</label>
                <input type="email" name="email" id="" required>

                <label for="first_name">First Name</label>
                <input type="text" name="firstName" id="" required>

                <label for="last_name">Last Name</label>
                <input type="text" name="lastName" id="" required>

                <label for="password">Password</label>
                <input type="text" name="password" id="" required> 

                <select name="account_type" id="">
                    <option value="1">System Admin</option>
                    <option value="2">Regular User</option>
                    <option value="3">Company Admin</option>
                </select>

                <input type="submit" value="Save">
                <input type="hidden" name="action" value="saveNewUser"> 

            </form>
        </div>
        <div class="container">
            <form action="">




                <h2>Manage Users</h2>

                <div class="table_headers">
                    <span>Email </span>
                    <span>  FirstName</span>
                    <span> LastName</span>
                    <span> Role</span>
                    <span>  Edit</span>
                    <span>  Delete</span>
                </div>
                <ul>

                    <c:if test="${users.size()!=0}">

                        <c:forEach items="${users}" var="user">
                            <span>${user.email}</span>
                            <span>${user.firstName}</span>
                            <span>${user.lastName}</span>
                            <span>${user.role}</span>
                            <input type="submit" value="Edit">
                            <input type="submit" value="Delete">
                            <br>
                        </c:forEach>

                    </c:if>


                </ul>
            </form>
        </div>
        <div class="container">
            <h2>Edit User</h2>
            <form action="">

                <input type="text" name="" id="">
                <input type="text" name="" id="">
                <input type="text" name="" id="">

                <select name="account_type" id="">
                    <option value="1">System Admin</option>
                    <option value="2">Regular User</option>
                    <option value="3">Company Admin</option>
                </select>


            </form>
        </div>

    </body>
</html>






