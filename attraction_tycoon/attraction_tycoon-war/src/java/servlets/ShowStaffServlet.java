/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Staff;
import dao.DAOException;
import daoImpl.DAOFactory;
import dao.StaffDao;
import java.io.IOException;
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
public class ShowStaffServlet extends HttpServlet {

    public static final String VUE = "/showstaff.jsp";
    public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String VUE_POST = "/staff";

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
        String upd = request.getParameter("update");

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

    }
}
