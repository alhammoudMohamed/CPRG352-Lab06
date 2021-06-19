<%-- 
    Document   : shoppingList
    Created on : Jun 17, 2021, 1:49:18 PM
    Author     : 844568
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
    </head>
    <body>
        <h1>Shopping List</h1>

        <form method="post" >


            <h2> ${username} is logged in</h2>


            <h2>Add Item </h2>
            <input type="text" name="itemToAdd" >
            <input type="submit" value="Add Item" name="addItem">


            <c:if test="${items.size()!=0}">


                <c:forEach items="${items}" var="item">
                    <ul>
                        <li> <input type="radio" name="item"  value="${item}"  >${item} </li>


                    </ul>

                </c:forEach>
            </c:if>

            <br>
            <br>
            <p>${clicked}</p>
            <input type="submit" name="deleteButton" value="Delete">
            <br>
            <br>
            <a href="shoppingList?logout">Logout</a>

        </form>

    </body>
</html>
