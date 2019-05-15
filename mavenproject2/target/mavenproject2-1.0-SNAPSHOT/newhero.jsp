<%@page import="com.mycompany.mavenproject2.repository.MineralResourceRepository"%>
<%@page import="com.mycompany.mavenproject2.model.User"%>
<%@page import="com.mycompany.mavenproject2.model.Species"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hős</title>
    </head>
    <body>
        <h1>Üdv <c:out default="????" value="${sessionScope['user'].name}"> </c:out>!</h1>
        <hr>
        
        <h1>Hős felvitel</h1>
                
        <form method="post" action="hero">
                <input type="text" name="name" placeholder="name of hero">
                <input type="text" name="description" placeholder="description"> <br>

                <c:forEach var="onespecies" items="${species}">
                    <c:out value="${onespecies.name}" ></c:out> 
                    <input type="number" name="pspec_${onespecies.id}" min="0">
                    <br> 
                </c:forEach> 
                
                <input type="submit" value="save">
        </form>         
        
        <h2>Felvett hősök</h2>
        
        <c:forEach var="hero" items="${sessionScope['user'].heroes}">
            <c:out default="????" value="${hero.name}"> </c:out>     <br>
        </c:forEach>   
        <hr>
        
        <br>
<!--itt lehessen felvenni új birodalmat is, nem feltétlen kell bele új épület egyből azt esetleg
egy másik formon-->
        <h1>Birodalom felvitel</h1>

        <form method="post" action="empire">
            <input type="text" name="name" placeholder="name of empre">
            <input type="text" name="description" placeholder="description"> <br>
            <input type="submit" value="save">
        </form>
        
        <h2>Felvett birodalmak</h2>
        
        <c:forEach var="empire" items="${sessionScope['user'].empires}">
            <c:out default="????" value="${empire.name}"> </c:out>     
            <br>
            Erőforrásai:
            <br>
            <c:forEach var="resource" items="${empire.resources}">
                Név: <c:out default="????" value="${resource.minerals.name}"> </c:out>     
                <br>
                Leírás: <c:out default="????" value="${resource.minerals.description}"> </c:out>     
                <br>
                Darab: <c:out default="????" value="${resource.count}"> </c:out>     
                <br>
            </c:forEach>   
                <br>
        </c:forEach>   
        
        <hr>        <br>

        <h1>Épület építése</h1>
        <c:if test="${empty sessionScope['user'].empires}"> 
            Előbb birodalmat kell hozzáadnod.
        </c:if>
        
        <c:if test="${not empty sessionScope['user'].empires}"> 
            <form method="post" action="building">
                Válassz birodalmat: 
                <select name="empire">
                    <c:forEach var="empire" items="${sessionScope['user'].empires}">
                        <option value="<c:out default="0" value="${empire.id}"></c:out>">
                            <c:out default="??" value="${empire.name}"></c:out>
                        </option>
                    </c:forEach>
                </select>
                Válassz épület típust: 
                <select name="building">
                    <c:forEach var="building" items="${buildings}">
                        <option value="<c:out default="0" value="${building.id}"></c:out>">
                            <c:out default="??" value="${building.name}"></c:out>
                        </option>
                    </c:forEach>
                </select> 
                <input type="submit" value="save">
            </form>
        
        <h2>Felvett Épületek</h2>
            <c:forEach var="empire" items="${sessionScope['user'].empires}">
                <c:out default="????" value="${empire.name}"> </c:out>     
                <br>
                Épületei:
                <br>                
                <c:if test="${not empty empire.buildings}">
                    <c:forEach var="building" items="${empire.buildings}">
                        Név: <c:out default="????" value="${building.building.name}"> </c:out>     
                        <br>
                        Darab: <c:out default="????" value="${building.count}"> </c:out>     
                        <br>
                    </c:forEach>   
                        <br>
                </c:if>
                <c:if test="${empty empire.buildings}">
                    Még nincs épület hozzáadva
                </c:if>
            </c:forEach>   
        </c:if>
                          
    <hr> <br>
        <h2>Birodalom keresése leírás alapján</h2>
        <form method="post" action="searchbydesc">
            Birodalom Neve:
            <input type="text" name="searchname" placeholder="name"> <br>
            Birodalom leírása: 
            <!--jelenleg ez van-->
            <input type="text" name="searchdescription" placeholder="description"> <br>
            <!--hány ásvány kincs legyen benne, lehet az is kell h melyikből?!?!-->
            Minimális ásvány meghatározása:
            <select name="searchresources">
                    <c:forEach var="resource" items="${allResources}">
                        <option value="<c:out default="0" value="${resource.id}"></c:out>">
                            <c:out default="????" value="${resource.name}"></c:out>
                        </option>
                    </c:forEach>
            </select> 
            <br/>
            Minimum ásvány mennyiség meghatározása:
            <input type="number" name="quantityresource" placeholder="Ásványkincs mennyisége (ettől lesz nagyobb)">
            <br/>
            Válasszon épületet:
            <!--hány épület legyen benne-->
            <select name="searchbuilding">
                    <c:forEach var="building" items="${buildings}">
                        <option value="<c:out default="0" value="${building.id}"></c:out>">
                            <c:out default="??" value="${building.name}"></c:out>
                        </option>
                    </c:forEach>
                </select> 
            <br>
            <input type="submit" value="search">
        </form>
        
    </body>
</html>
