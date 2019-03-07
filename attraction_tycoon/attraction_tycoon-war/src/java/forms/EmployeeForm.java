package forms;

import beans.Staff;
import beans.User;
import dao.DAOException;
import dao.StaffDao;
import dao.UserDao;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sandrine
 */
public class EmployeeForm {

    private StaffDao staffDao;
    private UserDao userDao;
    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();

    public EmployeeForm(StaffDao staffDao, UserDao userDao) {
        this.staffDao = staffDao;
        this.userDao = userDao;
    }

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public void createStaff(HttpServletRequest request) throws DAOException {

        String name = getValeurChamp(request, "name");
        String type = getValeurChamp(request, "type");
        String salary = getValeurChamp(request, "salary");
        String email = getValeurChamp(request, "email");
        String hours = getValeurChamp(request, "hours");
        String status = "A";

        Staff staff = new Staff();

        try {
            check(name);
            check(type);
            checkEmail(email);
            checkSalary(salary);
            checkHours(hours);
        } catch (Exception e) {
            setErreur("name", e.getMessage());
        }

        if (erreurs.isEmpty()) {

            User user = userDao.findByEmail(email);

            staff.setName(name);
            staff.setType(type);
            staff.setHours(Integer.parseInt(hours));
            staff.setId_user(user.getId());
            staff.setStatus(status);
            staff.setSalary(Double.parseDouble(salary));
            staffDao.create(staff);
            resultat = "Employé rajouté avec succès.";
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

    private void checkEmail(String email) {
        try {
            validationEmail(email);
        } catch (Exception e) {
            setErreur("email", e.getMessage());
        }
    }

    private void checkSalary(String salary) {
        try {
            Double.parseDouble(salary);
        } catch (Exception e) {
            setErreur("salary", e.getMessage());
        }
    }

    private void checkHours(String hours) {
        if (hours.length() > 72) {
            setErreur("hours", "Le taux horaire est trop élevé.");
        } else {
            try {
                Integer.parseInt(hours);
            } catch (Exception e) {
                setErreur("hours", e.getMessage());
            }
        }
    }

    private void validationEmail(String email) throws Exception {
        if (email != null) {
            if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
                throw new Exception("Merci de saisir une adresse mail valide.");
            } else if (userDao.findByEmail(email) == null) {
                throw new Exception("Veuillez inscrire l'employé au préalable");
            }
        } else {
            throw new Exception("Merci de saisir une adresse mail.");
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

    public void updateEmployee(HttpServletRequest request) throws DAOException {
        HttpSession session = request.getSession();
        String idstaff = (String) session.getAttribute("id");
        Long id = Long.parseLong(idstaff);
        Staff staff = new Staff();
        staff.setId(id);
        String name = getValeurChamp(request, "name");
        String type = getValeurChamp(request, "type");
        String salary = getValeurChamp(request, "salary");
        String email = getValeurChamp(request, "email");
        String hours = getValeurChamp(request, "hours");
        String status = "A";

        try {
            check(name);
            check(type);
            checkEmail(email);
            checkSalary(salary);
            checkHours(hours);
        } catch (Exception e) {
            setErreur("name", e.getMessage());
        }

        if (erreurs.isEmpty()) {
            User user = userDao.findByEmail(email);
            staff.setName(name);
            staff.setType(type);
            staff.setHours(Integer.parseInt(hours));
            staff.setId_user(user.getId());
            staff.setStatus(status);
            staff.setSalary(Double.parseDouble(salary));
            staffDao.update(staff);
            resultat = "Employé modifié avec succès.";
        } else {
            resultat = "Échec.";
        }
        System.out.println(resultat);

    }
}
