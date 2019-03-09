<%@ include file = "template/header.jsp" %>

<div class="container">

    <h1>Fiche employé</h1>
    <a href="<c:url value="/updateemployee?id=${staff.id}"/>" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Modifier</a>
    <h3>#ID : </h3><a><c:out value="${staff.id}" /></a>
    <h3>Nom : </h3><a><c:out value="${staff.name}" /></a>
    <h3>Métier : </h3><a><c:out value="${staff.type}" /></a>
    <h3>Email : </h3><a><c:out value="${user.email}" /></a>
    <h3>Salaire : </h3><a><c:out value="${staff.salary}" /> $/h</a>
    <h3>Taux horaire : </h3><a><c:out value="${staff.hours}" />h/semaine</a>

</div>

<%@ include file = "template/footer.jsp" %>
