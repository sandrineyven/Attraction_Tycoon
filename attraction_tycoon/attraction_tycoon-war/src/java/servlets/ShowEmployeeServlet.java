package servlets;

import beans.Staff;
import beans.User;
import dao.DAOException;
import dao.StaffDao;
import dao.UserDao;
import daoImpl.DAOFactory;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowEmployeeServlet extends HttpServlet {

    public static final String VUE = "/showemployee.jsp";
    public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String VUE_POST = "/staff";

    private StaffDao staffDao;
    private UserDao userDao;

    @Override
    public void init() throws ServletException {

        this.staffDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getStaffDao();
        this.userDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getUserDao();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String id = request.getParameter("id");
        Staff staff = new Staff();
        User user = new User();
        try {
            staff = staffDao.findById(Integer.parseInt(id));
        } catch (DAOException ex) {
            Logger.getLogger(ShowEmployeeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (staff != null) {
            try {
                user = userDao.findById(staff.getId_user());
            } catch (DAOException ex) {
                Logger.getLogger(UpdateEmployeeServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        session.setAttribute("staff", staff);
        session.setAttribute("user", user);
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

}
