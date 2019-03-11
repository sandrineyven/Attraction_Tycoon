<%@ include file = "template/header.jsp" %>

<div class="container">

    <form method="post" action="updateCarousel">
        <fieldset>
            <legend><h2>Modifier un man�ge</h2></legend>
            <hr>
            </br>
            <div class="input-group flex-nowrap">
                <input type="text" id="name" name="name" value="<c:out value="${carousel.name}"/>" size="20" maxlength="20" class="form-control" aria-label="Nom" aria-describedby="addon-wrapping" placeholder="Nom"/>
            </div>            
            <span class="erreur">${form.erreurs['name']}</span>
            <br />


            <div class="input-group flex-nowrap">

                <input type="type" id="type" name="type" value="<c:out value="${carousel.type}"/>" size="20" maxlength="60" class="form-control" aria-label="Type" aria-describedby="addon-wrapping" placeholder="Type"/>
            </div>
            <span class="erreur">${form.erreurs['type']}</span>
            <br />
            
            <div class="input-group flex-nowrap">

                <input type="type" id="duration" name="duration" value="<c:out value="${carousel.duration}"/>" size="20" maxlength="60" class="form-control" aria-label="Duration" aria-describedby="addon-wrapping" placeholder="Dur�e du man�ge"/>
            </div>
            <span class="erreur">${form.erreurs['duration']}</span>
            <br />
            
            
            <div class="input-group flex-nowrap">

                <input type="type" id="capacity" name="capacity" value="<c:out value="${carousel.capacity}"/>" size="20" maxlength="60" class="form-control" aria-label="Capacity" aria-describedby="addon-wrapping" placeholder="Capacit�"/>
            </div>
            <span class="erreur">${form.erreurs['capacity']}</span>
            <br />

            <div class="input-group flex-nowrap">
                <select id="select" name="zone" value="<c:out value="${carousel.zone}"/>" class="form-control" aria-label="N� zone" aria-describedby="addon-wrapping" placeholder="N� zone">
                    <option value="">N� zone</option>
                    <option value="1">Zone N�1</option>
                    <option value="2">Zone N�2</option>
                    <option value="3">Zone N�3</option>
                    <option value="4">Zone N�4</option>
                    <option value="5">Zone N�5</option>
                </select>    
            </div>

            <span class="erreur">${form.erreurs['zone']}</span>
            <br />

            <input type="submit" value="Modifier" class="btn btn-success" />
            <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
        </fieldset>
    </form>
</div>

<%@ include file = "template/footer.jsp" %>

