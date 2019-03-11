package servlets;

import dao.CarouselDao;
import dao.DAOException;
import daoImpl.DAOFactory;
import forms.CarouselForm;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateCarouselServlet extends HttpServlet {

    public static final String VUE = "/createcarousel.jsp";
    public static final String VUE_POST = "/carousels";
    public static final String CONF_DAO_FACTORY = "daofactory";

    private CarouselDao carouselDao;

    @Override
    public void init() throws ServletException {

        this.carouselDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getCarouselDao();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CarouselForm form = new CarouselForm(carouselDao);

        try {
            form.createCarousel(request);
        } catch (DAOException ex) {
            Logger.getLogger(CreateCarouselServlet.class.getName()).log(Level.SEVERE, null, ex);
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
