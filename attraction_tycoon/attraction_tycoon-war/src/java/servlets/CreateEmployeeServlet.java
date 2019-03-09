package servlets;

import dao.DAOException;
import dao.StaffDao;
import dao.UserDao;
import daoImpl.DAOFactory;
import forms.EmployeeForm;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateEmployeeServlet extends HttpServlet {

    public static final String VUE = "/createemployee.jsp";
    public static final String VUE_POST = "/staff";
    public static final String CONF_DAO_FACTORY = "daofactory";

    private StaffDao staffDao;

    private UserDao userDao;

    @Override
    public void init() throws ServletException {

        this.staffDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getStaffDao();
        this.userDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getUserDao();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EmployeeForm form = new EmployeeForm(staffDao, userDao);

        try {
            form.createStaff(request);
        } catch (DAOException ex) {
            Logger.getLogger(CreateEmployeeServlet.class.getName()).log(Level.SEVERE, null, ex);
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
