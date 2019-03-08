/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Shop;
import beans.Staff;
import dao.DAOException;
import dao.ShopDao;
import dao.StaffDao;
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

/**
 *
 * @author Sandrine
 */
public class SearchServlet extends HttpServlet {

    public static final String VUE = "/search.jsp";
    public static final String CONF_DAO_FACTORY = "daofactory";

    private ShopDao shopDao;
    private StaffDao staffDao;

    @Override
    public void init() throws ServletException {

        this.shopDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getShopDao();
        this.staffDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getStaffDao();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("searchDone", false);
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("searchDone", false);
        String search = request.getParameter("search");
        boolean boxShop = false;
        boolean boxStaff = false;
        boolean boxCarousel = false;

        List<Shop> shops = new ArrayList<>();
        List<Staff> staffs = new ArrayList<>();
        //Recuperation des catégories
        if (request.getParameter("boxShop") != null) {
            boxShop = true;
        }
        if (request.getParameter("boxStaff") != null) {
            boxStaff = true;
        }
        if (request.getParameter("boxCarousel") != null) {
            boxCarousel = true;
        }

        //Recuperation des zones selectionnées
        List<Integer> zones = new ArrayList<>();
        if (request.getParameter("zone1") != null) {
            zones.add(Integer.parseInt(request.getParameter("zone1")));
        }
        if (request.getParameter("zone2") != null) {
            zones.add(Integer.parseInt(request.getParameter("zone2")));
        }
        if (request.getParameter("zone3") != null) {
            zones.add(Integer.parseInt(request.getParameter("zone3")));
        }
        if (request.getParameter("zone4") != null) {
            zones.add(Integer.parseInt(request.getParameter("zone4")));
        }
        if (request.getParameter("zone5") != null) {
            zones.add(Integer.parseInt(request.getParameter("zone5")));
        }

        if (boxShop || !boxShop && !boxStaff && !boxCarousel) {
            //Shop
            if (search.isEmpty() && zones.isEmpty()) {
                System.out.println(zones.size());
                try {
                    shops = shopDao.findAll();
                } catch (DAOException ex) {
                    Logger.getLogger(AffichageShopServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    shops = shopDao.findBySearchAdvanced(search, zones);
                } catch (DAOException ex) {
                    Logger.getLogger(AffichageShopServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        if (boxStaff || !boxShop && !boxStaff && !boxCarousel) {
            //STAFF
            if (search.isEmpty() && zones.isEmpty()) {
                try {
                    staffs = staffDao.findAll();
                } catch (DAOException ex) {
                    Logger.getLogger(AffichageShopServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        if (null != shops) {
            request.setAttribute("shops", shops);
        }
        if (null != staffs) {
            request.setAttribute("staffs", staffs);
        }

        request.setAttribute("searchDone", true);
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

}
