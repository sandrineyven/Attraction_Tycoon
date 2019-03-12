
<%@ include file = "template/header.jsp" %>




<div class="container">


    </br>
    <h2> Liste des manèges </h2>

    <table class="table table-striped">
        <thead>
            <tr>
                <th scope="col">#ID</th>
                <th scope="col">Nom</th>
                <th scope="col">Type</th>
                <th scope="col">Zone</th>
                <th scope="col">Durée</th>
                <th scope="col">Capacité Max</th>
                <th scope="col">Supprimer/Modifier</th>
            </tr>
        </thead>
        <form method="get" action="carousels">
            <tbody>
                <c:forEach var="carousel" items="${carousels}">
                    <tr>
                        <td><c:out value="${carousel.id}" /></td>
                        <td><c:out value="${carousel.name}" /></td>
                        <td><c:out value="${carousel.type}" /></td>
                        <td><c:out value="${carousel.zone}" /></td>
                        <td><c:out value="${carousel.duration}" /></td>
                        <td><c:out value="${carousel.capacity}" /></td>
                        <td>
                            <form class="form-inline my-2 my-lg-0" >
                                <a href="<c:url value="/carousels?id=${carousel.id}&delete=1"/>" class="btn btn-danger btn-sm active">
                                    -
                                </a>
                                <a href="<c:url value="/carousels?id=${carousel.id}&update=1"/>" class="btn btn-primary btn-sm active" role="button" aria-pressed="true">
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

<nav class="navbar navbar-expand-lg navbar-light bg-white">
    <a href="<c:url value="/createCarousel"/>" class="btn btn-white btn-m active" role="button" aria-pressed="true">+ Ajouter</a>
    <a>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <form method="post" class="form-inline my-2 my-lg-0">
                <input class="form-control mr-sm-2" type="text" id="search" name="search" value="<c:out value="${search}"/>" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>
        </div>
</nav>
<%@ include file = "template/footer.jsp" %>
