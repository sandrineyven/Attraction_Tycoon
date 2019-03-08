<%-- 
    Document   : search
    Created on : 7 mars 2019, 21:14:59
    Author     : Sandrine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file = "template/header.jsp" %>

<div class="container">

    <form method="post" action="search">
        <fieldset>
            <legend><h2>Recherche avancée</h2></legend>

            <h6>N° Zone : </h6>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" id="zone1" name="zone1" value="1">
                <label class="form-check-label" for="inlineCheckbox1">1</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" id="zone2" name="zone2" value="2">
                <label class="form-check-label" for="inlineCheckbox2">2</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" id="zone3" name="zone3"value="3">
                <label class="form-check-label" for="inlineCheckbox2">3</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" id="zone4" name="zone4" value="4">
                <label class="form-check-label" for="inlineCheckbox2">4</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" id="zone5" name="zone5" value="5">
                <label class="form-check-label" for="inlineCheckbox2">5</label>
            </div>

            <h6>Catégorie : </h6>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" id="boxStaff" name="boxStaff" value="true">
                <label class="form-check-label" for="inlineCheckbox1">Staff</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" id="boxShop" name="boxShop" value="true">
                <label class="form-check-label" for="inlineCheckbox2">Boutiques</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" id="boxCarousel" name="boxCarousel" value="true">
                <label class="form-check-label" for="inlineCheckbox2">Manèges</label>
            </div>

            <div class="input-group flex-nowrap">
                <input class="form-control mr-sm-2" type="text" id="search" name="search" size="20" maxlength="60" placeholder="Search" aria-label="Search">
                <input type="submit" value="Rechercher" class="btn btn-success" />
            </div>

        </fieldset>
    </form>
    <c:if test="${searchDone}">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th scope="col">Catégorie</th>
                    <th scope="col">#ID</th>
                    <th scope="col">Nom</th>
                    <th scope="col">Zone</th>
                </tr>
            </thead>
            <form method="get" action="shops">
                <tbody>
                    <c:forEach var="shop" items="${shops}">
                        <tr>
                            <td><c:out value="Boutiques" /></td>
                            <td><c:out value="${shop.id}" /></td>
                            <td><a href="<c:url value="/shops?id=${shop.id}"/>">
                                    <c:out value="${shop.name}" />
                                </a></td>
                            <td><c:out value="${shop.zone}" /></td>
                        </tr>
                    </c:forEach>
                    <c:forEach var="staff" items="${staffs}">
                        <tr>
                            <td><c:out value="Staff" /></td>
                            <td><c:out value="${staff.id}" /></td>
                            <td><a href="<c:url value="/employee?id=${staff.id}"/>">
                                    <c:out value="${staff.name}" />
                                </a></td>
                            <td><c:out value="#" /></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </form>
        </table>
    </c:if>
</div>

<%@ include file = "template/footer.jsp" %>
