package servlets;

import beans.Shop;
import dao.DAOException;
import daoImpl.DAOFactory;
import dao.ShopDao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sandrine
 */
public class AffichageShopServlet extends HttpServlet {

    public static final String VUE = "/affichageShop.jsp";
    public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String VUE_POST = "/shops";

    private ShopDao shopDao;

    @Override
    public void init() throws ServletException {

        this.shopDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getShopDao();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        List<Shop> shops = new ArrayList<>();
        Shop shopUnique = new Shop();

        String id = request.getParameter("id");
        String del = request.getParameter("delete");
        String upd = request.getParameter("update");

        if (null != id && null == del && null == upd) {
            try {
                shopUnique = shopDao.findById(Integer.parseInt(id));
            } catch (DAOException ex) {
                Logger.getLogger(AffichageShopServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            shops.add(shopUnique);
        } else {

            try {
                shops = shopDao.findAll();
            } catch (DAOException ex) {
                Logger.getLogger(AffichageShopServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        session.setAttribute("shops", shops);

        if (null != id && (null != del || null != upd)) {
            if (null != del && del.equals("1")) {
                //DELETE
                try {
                    shopDao.delete(Integer.parseInt(id));
                } catch (DAOException ex) {
                    Logger.getLogger(AffichageShopServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                String redirect = response.encodeRedirectURL(request.getContextPath() + VUE_POST);
                response.sendRedirect(redirect);
            } else if (null != upd && upd.equals("1")) {
                //  UPDATE
                this.getServletContext().getRequestDispatcher("/updateshop").forward(request, response);
            }
        } else {
            this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String search = request.getParameter("search");
        
        List<Shop> shops = new ArrayList<>();
        
        if(search.trim() != null){
            try {
               shops = shopDao.findBySearch(search);
            } catch (DAOException ex) {
                Logger.getLogger(AffichageShopServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(null != shops){
                   session.setAttribute("shops", shops);
            }
        }
        
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

}
