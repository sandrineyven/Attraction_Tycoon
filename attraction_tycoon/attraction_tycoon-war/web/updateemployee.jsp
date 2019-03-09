<%@ include file = "template/header.jsp" %>

<div class="container">

    <form method="post" action="updateemployee">
        <fieldset>
            <legend><h2>Modifier un employé</h2></legend>
            <hr>
            </br>
            <div class="input-group flex-nowrap">
                <input type="text" id="name" name="name" value="<c:out value="${staff.name}"/>" size="20" maxlength="20" class="form-control" aria-label="Nom" aria-describedby="addon-wrapping" placeholder="Nom"/>
            </div>            
            <span class="erreur">${form.erreurs['name']}</span>
            <br />

            <div class="input-group flex-nowrap">
                <input type="email" id="login" name="email" value="<c:out value="${user.email}"/>" size="20" maxlength="60"  class="form-control" aria-label="Email" aria-describedby="addon-wrapping" placeholder="Email"/>
            </div>
            <span class="erreur">${form.erreurs['email']}</span>
            <br />

            <div class="input-group flex-nowrap">

                <input type="type" id="type" name="type" value="<c:out value="${staff.type}"/>" size="20" maxlength="60" class="form-control" aria-label="Metier" aria-describedby="addon-wrapping" placeholder="Metier"/>
            </div>
            <span class="erreur">${form.erreurs['type']}</span>
            <br />

            <div class="input-group flex-nowrap">
                <input type="salary" id="salary" name="salary" value="<c:out value="${staff.salary}"/>" size="20" maxlength="10"  class="form-control" aria-label="Salaire /h" aria-describedby="addon-wrapping" placeholder="Salaire /h"/>
            </div>
            <span class="erreur">${form.erreurs['salary']}</span>
            <br />

            <div class="input-group flex-nowrap">
                <input type="hours" id="hours" name="hours" value="<c:out value="${staff.hours}"/>" size="20" maxlength="2"  class="form-control" aria-label="Taux horaire h/semaine" aria-describedby="addon-wrapping" placeholder="Taux horaire h/semaine"/>
            </div>
            <span class="erreur">${form.erreurs['hours']}</span>
            <br />

            <input type="submit" value="Modifier" class="btn btn-success" />
            <br />

            <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
        </fieldset>
    </form>
</div>

<%@ include file = "template/footer.jsp" %>
