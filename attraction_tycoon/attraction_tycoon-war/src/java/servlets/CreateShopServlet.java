/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Shop;
import dao.DAOException;
import dao.DAOFactory;
import dao.ShopDao;
import forms.ShopForm;
import java.io.IOException;
import java.io.PrintWriter;
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
public class CreateShopServlet extends HttpServlet {

    public static final String VUE = "/createshop.jsp";
    public static final String CONF_DAO_FACTORY = "daofactory";

    private ShopDao shopDao;

    @Override
    public void init() throws ServletException {

        this.shopDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getShopDao();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 /* Préparation de l'objet formulaire */
        ShopForm form = new ShopForm(shopDao);

        try {
            form.createShop(request);
        } catch (DAOException ex) {
            Logger.getLogger(CreateShopServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("form", form);

//        if (form.getErreurs().isEmpty()) {
//            response.sendRedirect("affichageShop.jsp");
//        } else {
            this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
//        }
    }

}
