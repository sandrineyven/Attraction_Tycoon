<%-- 
    Document   : index
    Created on : 18 févr. 2019, 18:16:44
    Author     : Sandrine
--%>

<%@ include file = "template/header.jsp" %>

        <form method="get" action="<c:url value="/log" />">
            <input type="submit" value="Connexion" class="sansLabel" />
            <br />
        </form>
        
<%@ include file = "template/footer.jsp" %>
