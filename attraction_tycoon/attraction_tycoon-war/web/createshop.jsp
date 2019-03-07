<%-- 
    Document   : createshop
    Created on : 4 mars 2019, 17:53:30
    Author     : Sandrine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file = "template/header.jsp" %>

<div class="container">

    <form method="post" action="createShop">
        <fieldset>
            <legend><h2>Ajouter une boutique</h2></legend>
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
                <input type="select" id="zone" name="zone" value="<c:out value="${shop.zone}"/>" class="form-control" aria-label="N° zone" aria-describedby="addon-wrapping" placeholder="N° zone"/>
            </div>

            <span class="erreur">${form.erreurs['zone']}</span>
            <br />

            <input type="submit" value="Ajouter" class="btn btn-success" />
            <br />

            <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
        </fieldset>
    </form>

</div>

<%@ include file = "template/footer.jsp" %>
