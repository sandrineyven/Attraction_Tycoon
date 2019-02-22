
package servlets;

/**
 *
 * @author Sandrine
 */

import beans.User;
import dao.DAOFactory;
import dao.UserDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Test extends HttpServlet {
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)	
            throws ServletException, IOException {
        	/* Création et initialisation du message. */
	String paramAuteur = request.getParameter( "auteur" );
	String message = "Transmission de variables : OK ! " + paramAuteur;
		
	/* Création du bean */
	User premierBean = new User();
	/* Initialisation de ses propriétés */
	premierBean.setLogin( "login" );
	premierBean.setPassword( "password" );
		
	/* Stockage du message et du bean dans l'objet request */
	request.setAttribute( "test", message );
	request.setAttribute( "bean", premierBean );

        
		
	/* Transmission de la paire d'objets request/response à notre JSP */
	this.getServletContext().getRequestDispatcher( "/index.jsp" ).forward( request, response );
        
}
    
}
