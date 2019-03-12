<%@ include file = "template/header.jsp" %>


<div class="container">
    </br>
    <h2>Zones</h2>
    <table class="table table-striped">
        <thead>
            <tr>
                <th scope="col">#ID</th>
                <th scope="col">Nom</th>
                <th scope="col">Boutiques</th>
                <th scope="col">Manèges</th>
            </tr>
        </thead>
        <form method="get" action="zone">
            <tbody>

                <c:forEach var="zone" items="${zones}">
                    <tr>
                        <td><c:out value="${zone.id}" /></td>
                        <td><c:out value="${zone.name}" /></td>
                        <td>
                            <ul>
                                <c:forEach var="shop" items="${zone.shopList}">
                                    <form class="form-inline my-2 my-lg-0" >
                                        <li><a href="<c:url value="/shops?id=${shop.id}"/>">
                                                <c:out value="${shop.name}" />
                                            </a></li>
                                    </form>
                                </c:forEach>
                            </ul>
                        </td>
                        <td>
                            <ul>
                                <c:forEach var="carousel" items="${zone.carouselList}">
                                    <form class="form-inline my-2 my-lg-0" >
                                        <li><a href="<c:url value="/carousels?id=${carousel.id}"/>">
                                                <c:out value="${carousel.name}" />
                                            </a></li>
                                    </form>
                                </c:forEach>
                            </ul>
                        </td>
                    </tr>
                </c:forEach>

            </tbody>
        </form>
    </table>

    <center> 
        <figure class="figure">
            <div class="w3-content" style="max-width:800px">
                <img src="ressource/map.jpg" class="img-fluid img-thumbnail" alt="Responsive image" >
                <figcaption class="figure-caption">Carte du parc</figcaption>
        </figure>
    </center>

</div>

<%@ include file = "template/footer.jsp" %>
