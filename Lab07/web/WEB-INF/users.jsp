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
        <c:if test="${users.size()!=0}">

            <c:forEach items="${users}" var="user">
                <ul>
                    <li> <input type="text" name="item"  value="${user}"  >${user} </li>
                </ul>
            </c:forEach>

        </c:if>
    </body>
</html>
