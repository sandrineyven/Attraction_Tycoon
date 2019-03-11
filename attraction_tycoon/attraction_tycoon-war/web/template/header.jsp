<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%-- Bootstrap integration --%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>

<html>


    <div class ="container">
        <div class="shadow">
            <nav class="navbar sticky-top navbar-expand-lg navbar-light bg-light">
                <a class="navbar-brand" href="index.jsp">
                    <img src="ressource/LOGO_TRANSPARENT.png" width="60" height="25" alt="5" class="d-inline-block align-top">
                </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item active">
                            <a class="nav-link" href="index.jsp" >Home <span class="sr-only">(current)</span></a>
                        </li>
                        <c:if test="${empty sessionScope.sessionUser}">
                            <li class="nav-item">
                                <a class="nav-link" href="log.jsp">Connexion</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="inscription.jsp">Inscription</a>
                            </li>
                        </c:if>
                        <c:if test="${!empty sessionScope.sessionUser}">
                            <li class="nav-item">
                                <a class="nav-link" href="<c:url value="/deconnexion"/>">Deconnexion</a>
                            </li>

                            <li class="nav-item">
                                <a class="nav-link" href="<c:url value="search.jsp"/>">Recherche avancée</a>
                            </li>

                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                    Menu
                                </a>
                                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <a class="dropdown-item" href="<c:url value="/staff"/>">Staff</a>
                                    <a class="dropdown-item" href="<c:url value="/shops"/>">Shops</a>
                                    <a class="dropdown-item" href="<c:url value="/carousels"/>">Maneges</a>
                                    <a class="dropdown-item" href="<c:url value="/zones"/>">Zones</a>
                                </div>
                            </li>
                        </c:if>  
                    </ul>
                    <form class="form-inline my-2 my-lg-0">
                        <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                    </form>
                </div>
            </nav>
        </div>
    </div>
    <body>