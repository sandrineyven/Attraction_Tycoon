<%-- 
    Document   : affichageShop
    Created on : 27 févr. 2019, 16:22:54
    Author     : Sandrine
--%>


<%@ include file = "template/header.jsp" %>


<div class="container">


    <a href="<c:url value="/createShop"/>" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">+</a>
    <table class="table table-striped">
        <thead>
            <tr>

                <th scope="col">#ID</th>
                <th scope="col">Nom</th>
                <th scope="col">Type</th>
                <th scope="col">Zone</th>
                <th scope="col">Supprimer/Modifier</th>
            </tr>
        </thead>
        <form method="get" action="shops">
            <tbody>

                <c:forEach var="shop" items="${shops}">
                    <tr>
                        <td><c:out value="${shop.id}" /></td>
                        <td><c:out value="${shop.name}" /></td>
                        <td><c:out value="${shop.type}" /></td>
                        <td><c:out value="${shop.zone}" /></td>
                        <td>
                            <form class="form-inline my-2 my-lg-0" >
                                <a href="<c:url value="/shops?id=${shop.id}&delete=1"/>" class="btn btn-primary btn-lg active">
                                    -
                                </a>
                                <a href="<c:url value="/shops?id=${shop.id}&update=1"/>" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">
                                    Modifier
                                </a>
                            </form>
                        </td>
                    </tr>
                </c:forEach>

            </tbody>
        </form>
    </table>

</div>

<%@ include file = "template/footer.jsp" %>
