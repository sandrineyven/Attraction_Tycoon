<%-- 
    Document   : inscription
    Created on : 27 f�vr. 2019, 11:39:05
    Author     : Sandrine
--%>

<%-- Bootstrap integration --%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>


<%@ include file = "template/header.jsp" %>

<div class="container">

    <form method="post" action="inscription">
        <fieldset>
            <legend>Inscription</legend>
            <p>Vous pouvez vous inscrire via ce formulaire.</p>

            </br>
            <%--<label for="login">Nom d'utilisateur</label>
            <input type="text" id="nom" name="nom" value="<c:out value="${utilisateur.login}"/>" size="20" maxlength="20" />
            --%>
            <div class="input-group flex-nowrap">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="addon-wrapping">!</span>
                </div>
                <input type="text" id="nom" name="nom" value="<c:out value="${utilisateur.login}"/>" size="20" maxlength="20" class="form-control" aria-label="Utilisateur" aria-describedby="addon-wrapping" placeholder="Utilisateur"/>
            </div>            
            <span class="erreur">${form.erreurs['login']}</span>
            <br />

            <%--<label for="email">Adresse email <span class="requis">*</span></label>--%>
            <div class="input-group flex-nowrap">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="addon-wrapping">@</span>
                </div>
                <input type="email" id="email" name="email" value="<c:out value="${utilisateur.email}"/>" size="20" maxlength="60" class="form-control" aria-label="Adresse email" aria-describedby="addon-wrapping" placeholder="Adresse email"/>
            </div>
            <span class="erreur">${form.erreurs['email']}</span>
            <br />

            <%--<label for="motdepasse">Mot de passe <span class="requis">*</span></label>--%>
            <div class="input-group flex-nowrap">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="addon-wrapping">$</span>
                </div>
                <input type="password" id="motdepasse" name="motdepasse" value="" size="20" maxlength="20"  class="form-control" aria-label="Mot de passe" aria-describedby="addon-wrapping" placeholder="Mot de passe"/>
            </div>
            <%-- <input type="password" id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" /> --%>
            <span class="erreur">${form.erreurs['motdepasse']}</span>
            <br />

            <%-- <label for="confirmation">Confirmation du mot de passe <span class="requis">*</span></label>
             <input type="password" id="confirmation" name="confirmation" value="" size="20" maxlength="20" />
            --%>
            <div class="input-group flex-nowrap">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="addon-wrapping">!</span>
                </div>
                <input type="password" id="confirmation" name="confirmation" value="" size="20" maxlength="20"  class="form-control" aria-label="Confirmation" aria-describedby="addon-wrapping" placeholder="Confirmation du mot de passe"/>
            </div>        
            <span class="erreur">${form.erreurs['confirmation']}</span>
            <br />

            <input type="submit" value="Inscription" class="btn btn-success" />
            <br />

            <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
        </fieldset>
    </form>

</div>
<%@ include file = "template/footer.jsp" %>