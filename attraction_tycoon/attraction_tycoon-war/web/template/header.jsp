<%-- 
    Document   : header
    Created on : 27 févr. 2019, 15:18:53
    Author     : Sandrine
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Attraction Tycoon</title>
    </head>
    <body bgcolor="white">
        <div align="center">
            <h2>Attraction Tycoon - Gestion d'un parc d'attraction</h2>
        </div>
        <div align="right">
            <c:if test="${!empty sessionScope.sessionUser}">
                <p>Vous êtes connecté(e) avec l'adresse :</p>
                <p>${sessionScope.sessionUser.email}</p>
                <p><a href="<c:url value="/deconnexion"/>">Deconnexion</a></p>
            </c:if>
        </div>

        <div align="left">
            <p><a href="<c:url value="/"/>">Accueil</a></p>

        </div>


