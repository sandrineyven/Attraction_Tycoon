<%-- 
    Document   : updateshop
    Created on : 6 mars 2019, 11:35:23
    Author     : Sandrine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file = "template/header.jsp" %>

<div class="container">

    <form method="post" action="updateshop">
        <fieldset>
            <legend><h2>Modifier une boutique</h2></legend>
            <hr>
            </br>
            <div class="input-group flex-nowrap">
                <input type="text" id="name" name="name" value="<c:out value="${shop.name}"/>" size="20" maxlength="20" class="form-control" aria-label="Nom" aria-describedby="addon-wrapping" placeholder="Nom"/>
            </div>            
            <span class="erreur">${form.erreurs['name']}</span>
            <br />


            <div class="input-group flex-nowrap">

                <input type="type" id="type" name="type" value="<c:out value="${shop.type}"/>" size="20" maxlength="60" class="form-control" aria-label="Type" aria-describedby="addon-wrapping" placeholder="Type"/>
            </div>
            <span class="erreur">${form.erreurs['type']}</span>
            <br />

            <div class="input-group flex-nowrap">
                <input type="Zone" id="zone" name="zone" value="<c:out value="${shop.zone}"/>" size="20" maxlength="1"  class="form-control" aria-label="N° zone" aria-describedby="addon-wrapping" placeholder="N° zone"/>
            </div>
            <span class="erreur">${form.erreurs['zone']}</span>
            <br />

        <input type="submit" value="Modifier" class="btn btn-success" />
            <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
        </fieldset>
    </form>

</div>

<%@ include file = "template/footer.jsp" %>

