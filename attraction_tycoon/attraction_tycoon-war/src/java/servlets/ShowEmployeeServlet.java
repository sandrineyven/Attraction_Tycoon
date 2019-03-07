/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Staff;
import dao.DAOException;
import dao.StaffDao;
import daoImpl.DAOFactory;
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
public class ShowEmployeeServlet extends HttpServlet {

     public static final String VUE = "/showemployee.jsp";
    public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String VUE_POST = "/staff";

    private StaffDao staffDao;

    @Override
    public void init() throws ServletException {
        this.staffDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getStaffDao();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
             HttpSession session = request.getSession();
            String id = request.getParameter("id");
            Staff staff = new Staff();
         try {
             staff = staffDao.findById(Integer.parseInt(id));
         } catch (DAOException ex) {
             Logger.getLogger(ShowEmployeeServlet.class.getName()).log(Level.SEVERE, null, ex);
         }
             System.out.println(staff.getName());
         session.setAttribute("staff", staff);
            this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
       
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

}
