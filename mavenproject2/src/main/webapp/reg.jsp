
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Regisztráció</title>
    </head>
    <body>
        <h1>Regisztráció!</h1>
            <form method="post" action="reg">
            <input type="text" name="name" placeholder="login name">
            <input type="password" name="password" placeholder="password"> <br>
            <select name="role">
                <option value="ADMINISTRATOR">ADMINISTRATOR</option>
                <option value="USER">USER</option>
            </select>
            <input type="submit" value="save">
            </form>        
    </body>
</html>
