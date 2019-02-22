<%-- 
    Document   : index
    Created on : 18 févr. 2019, 18:16:44
    Author     : Sandrine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Accueil</title>
    </head>
    <body bgcolor="white">
        <div align="center">
            <h1>Attraction Tycoon - Gestion d'un parc d'attraction</h1>
        </div>
        <p>
            ${test}
            ${param.auteur}
        </p>
        <p>
            Récupération du bean :
            ${bean.login}
            ${bean.password}
        </p>
    </body>
</html>
