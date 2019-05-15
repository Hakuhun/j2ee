<%@page import="com.mycompany.mavenproject2.model.User"%>
<%@page import="com.mycompany.mavenproject2.model.Species"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Birodalom</title>
    </head>
    <body>
        <h1>Birodalom felvitel</h1>

        
        <c:out default="????" value="${sessionScope['user'].name}"> </c:out>     
        <c:forEach var="empire" items="${sessionScope['user'].empires}">
            <c:out default="????" value="${empire.name}"> </c:out>     
            
        </c:forEach>   

        <!--itt kéne egy legördülő mindkettőnek-->
        
        <form method="post" action="hero">
                <input type="text" name="name" placeholder="name of hero">
                <input type="text" name="description" placeholder="description"> <br>

                <c:forEach var="onespecies" items="${species}">
                    <c:out value="${onespecies.name}" ></c:out> 
                    <input type="number" name="pspec_${onespecies.id}">
                    <br> 
                </c:forEach> 
                
                <input type="submit" value="save">
        </form>         
        <br>
<!--itt lehessen felvenni új birodalmat is, nem feltétlen kell bele új épület egyből azt esetleg
egy másik formon-->
        <form method="post" action="newbuilding">
            <input type="text" name="name" placeholder="name of empre">
            <input type="text" name="description" placeholder="description"> <br>
            <input type="submit" value="save">
        </form>

    </body>
</html>