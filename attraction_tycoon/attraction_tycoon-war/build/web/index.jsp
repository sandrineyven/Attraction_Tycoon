<%-- 
    Document   : index
    Created on : 18 févr. 2019, 18:16:44
    Author     : Sandrine
--%>

<%@ include file = "template/header.jsp" %>
        <div align="left">
            <c:if test="${empty sessionScope.sessionUser}">
                <p><a href="<c:url value="/log"/>">Connexion</a>
                    <a href="<c:url value="/inscription"/>">Inscription</a></p>
                </c:if>
        </div>

<p>PAGE ACCUEIL</p>
        
<%@ include file = "template/footer.jsp" %>
