<%-- 
    Document   : affichageShop
    Created on : 27 févr. 2019, 16:22:54
    Author     : Sandrine
--%>


<%@ include file = "template/header.jsp" %>


<div class="container">


    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a href="<c:url value="/createShop"/>" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">+ Ajouter</a>
        <a>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <form method="post" class="form-inline my-2 my-lg-0">
                    <input class="form-control mr-sm-2" type="text" id="search" name="search" value="<c:out value="${search}"/>" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                </form>
            </div>
    </nav>

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
