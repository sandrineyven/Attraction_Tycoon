package servlets;

import beans.Carousel;
import beans.Carousel;
import dao.DAOException;
import daoImpl.DAOFactory;
import dao.CarouselDao;
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

public class ShowCarouselServlet extends HttpServlet {

    public static final String VUE = "/showcarousel.jsp";
    public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String VUE_POST = "/carousels";

    private CarouselDao carouselDao;

    @Override
    public void init() throws ServletException {

        this.carouselDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getCarouselDao();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        List<Carousel> carousels = new ArrayList<>();
        Carousel carouselUnique = new Carousel();

        String id = request.getParameter("id");
        String del = request.getParameter("delete");
        String upd = request.getParameter("update");

        if (null != id && null == del && null == upd) {
            try {
                carouselUnique = carouselDao.findById(Integer.parseInt(id));
            } catch (DAOException ex) {
                Logger.getLogger(ShowCarouselServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            carousels.add(carouselUnique);
        } else {

            try {
                carousels = carouselDao.findAll();
            } catch (DAOException ex) {
                Logger.getLogger(ShowCarouselServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
       }

        session.setAttribute("carousels", carousels);

        if (null != id && (null != del || null != upd)) {
            if (null != del && del.equals("1")) {
                //DELETE
                try {
                    carouselDao.delete(Integer.parseInt(id));
                } catch (DAOException ex) {
                    Logger.getLogger(ShowCarouselServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                String redirect = response.encodeRedirectURL(request.getContextPath() + VUE_POST);
                response.sendRedirect(redirect);
            } else if (null != upd && upd.equals("1")) {
                //  UPDATE
                this.getServletContext().getRequestDispatcher("/updateCarousel").forward(request, response);
            }
        } else {
            this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
       }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       HttpSession session = request.getSession();
        String search = request.getParameter("search");
        
        List<Carousel> carousels = new ArrayList<>();
        
        if(search.trim() != null){
            try {
               carousels = carouselDao.findBySearch(search);
            } catch (DAOException ex) {
                Logger.getLogger(ShowCarouselServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(null != carousels){
                   session.setAttribute("carousels", carousels);
            }
        }
        
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

}
