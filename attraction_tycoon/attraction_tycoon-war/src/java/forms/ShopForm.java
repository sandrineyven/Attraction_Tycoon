/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import beans.Shop;
import dao.DAOException;
import dao.ShopDao;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sandrine
 */
public class ShopForm {

    private ShopDao shopDao;

    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();

    public ShopForm(ShopDao shopDao) {
        this.shopDao = shopDao;
    }

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public void createShop(HttpServletRequest request) throws DAOException {

        String name = getValeurChamp(request, "name");
        String type = getValeurChamp(request, "type");
        String zone = getValeurChamp(request, "zone");

        Shop shop = new Shop();

        try {
            check(name);
            check(type);
            checkZone(zone);
        } catch (Exception e) {
            setErreur("zone", e.getMessage());
        }

        if (erreurs.isEmpty()) {

            shop.setName(name);
            shop.setType(type);
            shop.setZone(Integer.parseInt(zone));
            shopDao.create(shop);
            resultat = "Boutique rajoutée avec succès.";
        } else {
            resultat = "Échec.";
        }
        System.out.println(resultat);
    }

    private void check(String nom) throws Exception {
        if (nom != null && nom.length() < 3) {
            throw new Exception("Le nom/type de la boutique doit contenir au moins 3 caractères.");
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

    public void updateShop(HttpServletRequest request) throws DAOException {
        HttpSession session = request.getSession();
        String idshop = (String) session.getAttribute("id");
        Long id = Long.parseLong(idshop);
        Shop shop = new Shop();
        shop.setId(id);
        String name = getValeurChamp(request, "name");
        String type = getValeurChamp(request, "type");
        String zone = getValeurChamp(request, "zone");

        try {
            check(name);
            check(type);
            checkZone(zone);
        } catch (Exception e) {
            setErreur("zone", e.getMessage());
        }

        if (erreurs.isEmpty()) {
            shop.setName(name);
            shop.setType(type);
            shop.setZone(Integer.parseInt(zone));
            shopDao.update(shop);
            resultat = "Boutique modifiée avec succès.";
        } else {
            resultat = "Échec.";
        }
        System.out.println(resultat);

    }
}
