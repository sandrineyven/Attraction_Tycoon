<%-- 
    Document   : showstaff
    Created on : 6 mars 2019, 17:07:10
    Author     : Sandrine
--%>



<%@ include file = "template/header.jsp" %>


<div class="container">


        <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a href="<c:url value="/createemployee"/>" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">+ Ajouter</a>
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
                <th scope="col">Metier</th>
                <th scope="col">Détails/Supprimer</th>
            </tr>
        </thead>
        <form method="get" action="staffs">
            <tbody>

                <c:forEach var="staff" items="${staffs}">
                    <tr>
                        <td><c:out value="${staff.id}" /></td>
                        <td><c:out value="${staff.name}" /></td>
                        <td><c:out value="${staff.type}" /></td>
                        <td>
                            <form class="form-inline my-2 my-lg-0" >
                                <a href="<c:url value="/employee?id=${staff.id}"/>" class="btn btn-primary btn-lg active">
                                    ...
                                </a>
                                <a href="<c:url value="/staff?id=${staff.id}&delete=1"/>" class="btn btn-primary btn-lg active">
                                    -
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
