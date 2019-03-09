package servlets;

import dao.DAOException;
import daoImpl.DAOFactory;
import dao.ShopDao;
import forms.ShopForm;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateShopServlet extends HttpServlet {

    public static final String VUE = "/createshop.jsp";
    public static final String VUE_POST = "/shops";
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
        ShopForm form = new ShopForm(shopDao);

        try {
            form.createShop(request);
        } catch (DAOException ex) {
            Logger.getLogger(CreateShopServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("form", form);

        if (form.getErreurs().isEmpty()) {
            String redirect = response.encodeRedirectURL(request.getContextPath() + VUE_POST);
            response.sendRedirect(redirect);
        } else {
            this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
        }
    }

}
