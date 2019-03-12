<%@ include file = "template/header.jsp" %>

<div class="container">

    <h2>Fiche employé</h2>
    <hr>
    <b>#ID : </b><c:out value="${staff.id}" /></br>
    <b>Nom : </b><c:out value="${staff.name}" /></br>
    <b>Métier : </b><c:out value="${staff.type}" /></br>
    <b>Email : </b><c:out value="${user.email}" /></br>
    <b>Salaire : </b><c:out value="${staff.salary}" /> $/h</br>
    <b>Taux horaire : </b><c:out value="${staff.hours}" />h/semaine</br>
    </br>
    <a href="<c:url value="/updateemployee?id=${staff.id}"/>" class="btn btn-link btn-m active" role="button" aria-pressed="true">Modifier</a>

    
</div>

<%@ include file = "template/footer.jsp" %>
