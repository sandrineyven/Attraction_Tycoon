package servlets;

import beans.Shop;
import beans.Zone;
import dao.DAOException;
import dao.ShopDao;
import dao.ZoneDao;
import daoImpl.DAOFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ZoneServlet extends HttpServlet {

    public static final String VUE = "/zone.jsp";
    public static final String CONF_DAO_FACTORY = "daofactory";

    private ZoneDao zoneDao;
    private ShopDao shopDao;

    @Override
    public void init() throws ServletException {
        this.zoneDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getZoneDao();
        this.shopDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getShopDao();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Shop> shops = new ArrayList<>();
        List<Zone> zones = null;
        try {
            zones = zoneDao.findAll();
        } catch (DAOException ex) {
            Logger.getLogger(ShowStaffServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (null != zones) {
            for (Zone zone : zones) {
                try {
                    shops = shopDao.findByZone(zone.getId());
                } catch (DAOException ex) {
                    Logger.getLogger(ZoneServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (!shops.isEmpty()) {
                    zone.setShopList(shops);
                }
            }
        }

        request.setAttribute("zones", zones);
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
