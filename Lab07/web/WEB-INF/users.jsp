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
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Manage Users</title>
        <link rel="stylesheet" href="./assets/styles/style.css">
    </head>
    <body>
        <div class="flex-container">
                <div class="flex-child">

                    <h2>Add User</h2>
                    <form method="post" action="user">

                        <label for="email">Email: </label>
                        <input type="email" name="email" id="" required>
                        <br><br>
                        <label for="first_name">First Name: </label>
                        <input type="text" name="firstName" id="" required>
                        <br><br>
                        <label for="last_name">Last Name: </label>
                        <input type="text" name="lastName" id="" required>
                        <br><br>
                        <label for="password">Password: </label>
                        <input type="password" name="password" id="" required> 
                        <br><br>
                        <select name="account_type" id="">
                            <option value="1">System Admin</option>
                            <option value="2">Regular User</option>
                            <option value="3">Company Admin</option>
                        </select>

                        <input type="submit" value="Save">
                        <input type="hidden" name="saveNewUser" > 

                    </form>
                </div>

            <div class="flex-child">
                <h1>Manage Users</h1>
                <form method="post" action="user"  >

                    <div class="table_headers">
                        <span>Email</span>
                        <span>First Name</span>
                        <span>Last Name</span>
                        <span>Role</span>
                        <span>Edit</span>
                        <span>Delete</span>
                    </div>
                    <ul>

                        <c:if test="${users.size()!=0}">

                            <c:forEach items="${users}" var="user">
                                <form method="post" action="user">

                                    <span name="email" >${user.email}</span>
                                    <span>${user.firstName}</span>
                                    <span>${user.lastName}</span>
                                    <span>${user.role}</span>
                                    <input type="submit" value="Edit">
                                    <input type="hidden" name="editButton" value="${user.email}"> 
                                </form>
                                <form method="post" action="user">
                                    <input type="submit" value="Delete" name>
                                    <input type="hidden" name="deleteUser" value="${user.email}"> 

                                </form>
                        </form>

                    </c:forEach>
                </c:if>

                </ul>               

            </div>

            <div class="flex-child">
                    <h2>Edit User</h2>
                    <form method="post" action="user">

                        <input type="text" name="email" id="" value="${userEmail}" readonly>

                        <br><br>
                        <input type="text" name="firstName" id="" value="${userFirstName}" placeholder="First Name">
                        <br><br>
                        <input type="text" name="lastName" id=""value="${userLastName}" placeholder="Last Name">
                        <br><br>
                        <select name="account_type" id=""   value="${userRole}">

                            <option value="1"    <c:if test = "${userRole ==1}">selected</c:if> >System Admin</option>
                            <option value="2"  <c:if test = "${userRole ==2}">selected</c:if>>Regular User</option>
                            <option value="3"  <c:if test = "${userRole ==3}">selected</c:if>>Company Admin</option>
                            </select>

                            <input type="submit" value="Save">
                            <input type="hidden" name="saveUserInfo" value="${userEmail}">

                    </form>
                    <form method="post" action="user">
                        <input type="submit" name="clearFields" value="Cancel">
                    </form>  
                    <p>${errorMessage}</p>
                </div>
        </div>
    </body>
</html>






