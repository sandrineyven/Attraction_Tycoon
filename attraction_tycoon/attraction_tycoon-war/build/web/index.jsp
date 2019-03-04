<%-- 
    Document   : index
    Created on : 18 févr. 2019, 18:16:44
    Author     : Sandrine
--%>

<%@ include file = "template/header.jsp" %>
<%@ include file = "template/global.jsp" %>

<style>
    .col-100vh {
        height:100vh;
    }
</style>

<%--
        <div align="left">
            <c:if test="${empty sessionScope.sessionUser}">
                <p><a href="<c:url value="/log"/>">Connexion</a>
                    <a href="<c:url value="/inscription"/>">Inscription</a></p>
                </c:if>
        </div>

--%>

</br>
<div class="col-100vh">
<div class="text-center">
    <div class="inner cover">
        <h1 class="cover-heading">Bienvenue sur Attraction tycoon.</h1>
        <p class="lead">Attraction tycoon vous permet de gérer en temps réel votre parc d'attraction.</p>
        <p class="lead">
    </div>
</div>
<footer class="mastfoot mt-auto">
    <div class="inner">
        <p>Réalisé par Sandrine Yven et Alexandre Lebegue</p>
    </div>
</footer>
</div>


<%@ include file = "template/footer.jsp" %>
