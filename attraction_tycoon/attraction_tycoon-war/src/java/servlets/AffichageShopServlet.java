package servlets;

import beans.Shop;
import dao.DAOException;
import dao.DAOFactory;
import dao.ShopDao;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sandrine
 */
public class AffichageShopServlet extends HttpServlet {

    //TODO: Avant d'aller plus loin: persistance des données
    //Les requetes sql se resemble beaucoup ----> Entity Manager
    public static final String VUE = "/affichageShop.jsp";
    public static final String CONF_DAO_FACTORY = "daofactory";

    private ShopDao shopDao;

    @Override
    public void init() throws ServletException {
        this.shopDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getShopDao();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        try {
            List<Shop> shops = shopDao.findAll();
        } catch (DAOException ex) {
            Logger.getLogger(AffichageShopServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

}
