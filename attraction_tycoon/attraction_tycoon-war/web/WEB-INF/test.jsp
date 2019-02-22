<%-- 
    Document   : test
    Created on : 19 févr. 2019, 16:58:21
    Author     : Sandrine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <p>
            <%
                String attribut = (String) request.getAttribute("test");
                out.println(attribut);

                String parametre = request.getParameter("auteur");
                out.println(parametre);
            %>
        </p>
        <p>
            Récupération du bean :
            <%
                com.sdzee.beans.UserBean notreBean = (com.sdzee.beans.UserBean) request.getAttribute("login");
                out.println(notreBean.getLogin());
                out.println(notreBean.getPassword());
            %>
        </p>
    </body>
</html>
