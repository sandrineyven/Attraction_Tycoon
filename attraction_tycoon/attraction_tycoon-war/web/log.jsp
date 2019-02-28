<%-- 
    Document   : log
    Created on : 22 févr. 2019, 15:13:28
    Author     : Sandrine
--%>

<%-- Bootstrap integration --%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>


<%@ include file = "template/header.jsp" %>

<div class="container">
        <div class="row">

            <form method="post" action="log">
                <fieldset>
                    <legend>Connexion</legend>
                    <p>Vous pouvez vous connecter via ce formulaire.</p>

                    <label for="nom">Adresse email <span class="requis">*</span></label>
                    <input type="email" id="email" name="email" value="<c:out value="${utilisateur.email}"/>" size="20" maxlength="60" class="form-control" aria-label="Adresse email" aria-describedby="addon-wrapping" placeholder="Adresse email"/>
                    <span class="erreur">${form.erreurs['email']}</span>
                    <br />

                    <label for="motdepasse">Mot de passe <span class="requis">*</span></label>
                    <input type="password" id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" class="form-control" aria-label="Mot de passe" aria-describedby="addon-wrapping" placeholder="Mot de passe"/>
                    <span class="erreur">${form.erreurs['motdepasse']}</span>
                    <br />

                    <input type="submit" value="Connexion" class="btn btn-primary" />
                    <br />

                    <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
                    <%-- Vérification de la présence d'un objet utilisateur en session --%>
                    <c:if test="${!empty sessionScope.sessionUser}">
                        <%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
                        <p class="succes">Vous êtes connecté(e) avec l'adresse : ${sessionScope.sessionUser.email}</p>
                    </c:if>
                </fieldset>
            </form>
        </div>
        <div class="row">
            <form method="get" action="<c:url value="/inscription" />">
                <input type="submit" value="Inscription" class="btn btn-secondary" />
                <br />
            </form>
        </div>
    </div>

</div>
<%@ include file = "template/footer.jsp" %>

