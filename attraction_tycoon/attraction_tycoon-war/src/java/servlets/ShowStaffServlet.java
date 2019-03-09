package servlets;

import beans.Staff;
import dao.DAOException;
import daoImpl.DAOFactory;
import dao.StaffDao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowStaffServlet extends HttpServlet {

    public static final String VUE = "/showstaff.jsp";
    public static final String VUE_POST = "/staff";
    public static final String CONF_DAO_FACTORY = "daofactory";

    private StaffDao staffDao;

    @Override
    public void init() throws ServletException {
        this.staffDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getStaffDao();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Staff> staffs = null;
        try {
            staffs = staffDao.findAll();
        } catch (DAOException ex) {
            Logger.getLogger(ShowStaffServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("staffs", staffs);

        String id = request.getParameter("id");
        String del = request.getParameter("delete");

        if (null != id) {

            if (null != del && del.equals("1")) {
                //DELETE
                try {
                    staffDao.delete(Integer.parseInt(id));
                } catch (DAOException ex) {
                    Logger.getLogger(ShowStaffServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                String redirect = response.encodeRedirectURL(request.getContextPath() + VUE_POST);
                response.sendRedirect(redirect);
            }
        } else {
            this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String search = request.getParameter("search");

        List<Staff> staffs = new ArrayList<>();

        if (search.trim() != null) {
            try {
                staffs = staffDao.findBySearch(search);
            } catch (DAOException ex) {
                Logger.getLogger(AffichageShopServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (null != staffs) {
                request.setAttribute("staffs", staffs);
            }
        }

        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

}
