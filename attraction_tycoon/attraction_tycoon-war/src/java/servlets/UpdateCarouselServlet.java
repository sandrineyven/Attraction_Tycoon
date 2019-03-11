package servlets;

import beans.Carousel;
import dao.DAOException;
import daoImpl.DAOFactory;
import dao.CarouselDao;
import forms.CarouselForm;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateCarouselServlet extends HttpServlet {

    public static final String VUE = "/updatecarousel.jsp";
    public static final String VUE_POST = "/carousels";
    public static final String CONF_DAO_FACTORY = "daofactory";

    private CarouselDao carouselDao;

    @Override
    public void init() throws ServletException {

        this.carouselDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getCarouselDao();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Carousel carousel = null;
        String id = request.getParameter("id");

        if (null != id) {
            int idcarousel = Integer.parseInt(id);
            try {
                carousel = carouselDao.findById(idcarousel);
            } catch (DAOException ex) {
                Logger.getLogger(UpdateCarouselServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            request.setAttribute("carousel", carousel);
            session.setAttribute("id", id);
        }

        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
        request.setAttribute("carousel", null);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CarouselForm form = new CarouselForm(carouselDao);

        try {
            form.updateCarousel(request);
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
