
package forms;

import beans.Carousel;
import beans.Carousel;
import dao.CarouselDao;
import dao.DAOException;
import dao.CarouselDao;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class CarouselForm {

    private CarouselDao carouselDao;

    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();

    public CarouselForm(CarouselDao carouselDao) {
        this.carouselDao = carouselDao;
    }

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public void createCarousel(HttpServletRequest request) throws DAOException {

        String name = getValeurChamp(request, "name");
        String type = getValeurChamp(request, "type");
        String zone = getValeurChamp(request, "zone");
        String duration = getValeurChamp(request, "duration");
        String capacity = getValeurChamp(request, "capacity");


        Carousel carousel = new Carousel();

        try {
            check(name);
            check(type);
            checkZone(zone);
        } catch (Exception e) {
            setErreur("zone", e.getMessage());
        }

        if (erreurs.isEmpty()) {

            carousel.setName(name);
            carousel.setType(type);
            carousel.setZone(Integer.parseInt(zone));
            carousel.setDuration(Integer.parseInt(duration));
            carousel.setCapacity(Integer.parseInt(capacity));
            carousel.setState("A");
            carousel.setIs_open(true);
            carousel.setWaiting_time(0);
            carouselDao.create(carousel);
            resultat = "Manège rajoutée avec succès.";
        } else {
            resultat = "Échec.";
        }
        System.out.println(resultat);
    }

    private void check(String nom) throws Exception {
        if (nom != null && nom.length() < 3) {
            throw new Exception("Le nom/type du manège doit contenir au moins 3 caractères.");
        }
    }

    private void checkZone(String zone) throws Exception {
        if (zone != null) {
            try {
               Integer.parseInt(zone);
            } catch (Exception e) {
                throw new Exception("Veuillez saisir la zone associée.");
            }
            int z = Integer.parseInt(zone);
            if (z < 1 || z > 5) {
                    throw new Exception("Veuillez saisir une zone entre 1 et 5.");
                }

        } else {
            throw new Exception("Veuillez saisir la zone associée.");
        }
    }

    /*
 * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
    private void setErreur(String champ, String message) {
        erreurs.put(champ, message);
    }

    /*
 * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
 * sinon.
     */
    private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
        String valeur = request.getParameter(nomChamp);
        if (valeur == null || valeur.trim().length() == 0) {
            return null;
        } else {
            return valeur.trim();
        }
    }

    public void updateCarousel(HttpServletRequest request) throws DAOException {
        HttpSession session = request.getSession();
        String idcarousel = (String) session.getAttribute("id");
        Long id = Long.parseLong(idcarousel);
        Carousel carousel = new Carousel();
        carousel.setId(id);
        String name = getValeurChamp(request, "name");
        String type = getValeurChamp(request, "type");
        String zone = getValeurChamp(request, "zone");
        String duration = getValeurChamp(request, "duration");
        String capacity = getValeurChamp(request, "capacity");

        try {
            check(name);
            check(type);
            checkZone(zone);
        } catch (Exception e) {
            setErreur("zone", e.getMessage());
        }

        if (erreurs.isEmpty()) {
           carousel.setName(name);
            carousel.setType(type);
            carousel.setZone(Integer.parseInt(zone));
            carousel.setDuration(Integer.parseInt(duration));
            carousel.setCapacity(Integer.parseInt(capacity));
            carouselDao.update(carousel);
            resultat = "Manège modifiée avec succès.";
        } else {
            resultat = "Échec.";
        }
        System.out.println(resultat);

    }
}
