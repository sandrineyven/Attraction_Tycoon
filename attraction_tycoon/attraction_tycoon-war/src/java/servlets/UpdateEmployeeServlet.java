/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Staff;
import beans.User;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sandrine
 */
public class UpdateEmployeeServlet extends HttpServlet {

    public static final String VUE = "/updateemployee.jsp";
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

        HttpSession session = request.getSession();
        Staff staff = null;
        User user = null;
        String id = request.getParameter("id");

        if (null != id) {
            int idstaff = Integer.parseInt(id);

            try {
                staff = staffDao.findById(idstaff);
            } catch (DAOException ex) {
                Logger.getLogger(UpdateEmployeeServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (staff != null) {
                try {
                    user = userDao.findById(staff.getId_user());
                } catch (DAOException ex) {
                    Logger.getLogger(UpdateEmployeeServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            request.setAttribute("staff", staff);
            request.setAttribute("user", user);
            session.setAttribute("id", id);
        }

        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EmployeeForm form = new EmployeeForm(staffDao, userDao);
        try {

            form.updateEmployee(request);
        } catch (DAOException ex) {
            Logger.getLogger(UpdateEmployeeServlet.class.getName()).log(Level.SEVERE, null, ex);
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
