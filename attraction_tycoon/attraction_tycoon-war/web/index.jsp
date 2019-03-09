
<%@ include file = "template/header.jsp" %>
<%@ include file = "template/global.jsp" %>

<style>
    .col-100vh {
        height:100vh;
    }
</style>

<div class="col-100vh">
    <div class="text-center">
        <div class="inner cover">
            <h1 class="cover-heading">Bienvenue sur Attraction tycoon.</h1>
            <p class="lead">Attraction tycoon vous permet de gérer en temps réel votre parc d'attraction.</p>
            <p class="lead">
        </div>
    </div>
    <hr>

    <center> 
        <div class="w3-content" style="max-width:800px">
            <img class="mySlides w3-animate-fading" src="ressource/attr1.jpg" style="width:100%">
            <img class="mySlides w3-animate-fading" src="ressource/attr2.jpg" style="width:100%">
            <img class="mySlides w3-animate-fading" src="ressource/attr3.jpg" style="width:100%">
            <img class="mySlides w3-animate-fading" src="ressource/attr4.jpg" style="width:100%">
        </div>
    </center>
    <hr>
    <script>
        var slideIndex = 0;
        carousel();

        function carousel() {
            var i;
            var x = document.getElementsByClassName("mySlides");
            for (i = 0; i < x.length; i++) {
                x[i].style.display = "none";
            }
            slideIndex++;
            if (slideIndex > x.length) {
                slideIndex = 1
            }
            x[slideIndex - 1].style.display = "block";
            setTimeout(carousel, 9000);
        }
    </script>


    <center>
        <footer class="mastfoot mt-auto">
            <div class="inner">
                <p>Réalisé par Sandrine Yven et Alexandre Lebegue</p>
            </div>
        </footer>
    </center>   
</div>

<%@ include file = "template/footer.jsp" %>
