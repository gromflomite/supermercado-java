package com.ipartek.formacion.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ipartek.formacion.controller.frontoffice.ProductosFrontOfficeController;
import com.ipartek.formacion.modelo.dao.impl.UsuarioDAOImpl;
import com.ipartek.formacion.modelo.pojo.Rol;
import com.ipartek.formacion.modelo.pojo.Usuario;

@WebServlet("/register")
public class UserRegisterController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final static Logger LOG = Logger.getLogger(ProductosFrontOfficeController.class);
    private final static UsuarioDAOImpl DAO = UsuarioDAOImpl.getInstance();    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	// Objects
	Alerta alerta 	= new Alerta(); // Feedback object
	Usuario newUser = new Usuario(); // User object
	Rol newUserRole = new Rol(); // User role object
	
	// Java session
	HttpSession javaSession = request.getSession();
	
	// Other elements
	String passwordChecked = "";
	boolean problemsInForm = false;

	try {

	    // Get parameters from view (register.jsp)
	    String userName = request.getParameter("userName");
	    String password = request.getParameter("pass");
	    String passwordConfirm = request.getParameter("passConfirm");
	    String dob = request.getParameter("dob");
	    

	    /** ***************************************************************************************************************************
	     * Checking if the username is not empty in first place.
	     * 
	     * If the register form is not properly filled (but we have the name at least) we must have to send that name back to the form 
	     * (as indicated in the course exercice). Trying to set the name into the userobject in the next "if-else" is not possible if 
	     * all the other checks (password and dob) does not comply (we lose the userName value).
	     */
	    if (!userName.isEmpty()) {

		// Set name to user object
		newUser.setNombre(userName);

	    } else { // Username field empty -> Not valid

		problemsInForm = true;

	    } // End userName check *******************************************************************************************************
	    
	    
	    /** ***************************************************************************************************************************
	     * Same scenario that above
	     * 
	     * We are going to check if DOB field is valid:
	     * 
	     * DOB OK -> Set the value to the object
	     * DOB KO -> Change "problemsInForm" value to true	     
	     */
	    if (!dob.isEmpty()) {
		
		newUser.setDob(dob);
		
	    } else {
		
		problemsInForm = true;
		
	    } // End DOB check ************************************************************************************************************    
	    

	    /**
	     * Check other form values
	     */
	    if ((!problemsInForm) && (!password.isEmpty()) && (!passwordConfirm.isEmpty())) {

		// Check if both passwords are equal
		if ((password != null) && (password.equals(passwordConfirm))) {

		    passwordChecked = password;

		    // Set values to user role
		    newUserRole.setId(Rol.USUARIO); // Hardcoded as plain user

		    // Set values to user object		   
		    newUser.setContrasenia(passwordChecked);		    
		    newUser.setRol(newUserRole);

		    // Call DAO
		    try {

			DAO.insert(newUser);
			alerta = new Alerta("success", "Te has registrado con éxito. Ya puedes iniciar sesión!!");

		    } catch (Exception e) { // Problem with DAO method

			problemsInForm = true;
			alerta = new Alerta("danger", "El nombre de usuario ya existe");

		    } // End try call DAO

		} else { // Password fields are non-coincident

		    problemsInForm = true;
		    alerta = new Alerta("danger", "Las contraseñas no coinciden");

		} // End password check

	    } else { // Not all fields filled

		problemsInForm = true;
		alerta = new Alerta("danger", "Debes rellenar todos los campos");

	    } // End all fields filled check

	} catch (Exception e) {

	    LOG.error(e);

	} finally {

	    /**
	     * 
	     * After check all the values received from form fields:
	     * 
	     * Values OK --> Redirect to login.jsp
	     * Values KO --> Forward to register.jsp	       
	     */	    
	    if (problemsInForm) { // Form values KO -> Forward --> register.jsp

		// Add the feedback and user object (to refill the form fields) to request
		request.setAttribute("alerta", alerta);
		request.setAttribute("newUser", newUser);

		// Forward
		request.getRequestDispatcher("views/register.jsp").forward(request, response);

	    } else { // Form values OK -> Redirect -> login.jsp

		// Using redirect -> We will have to put the feedback into the session
		javaSession.setAttribute("alerta", alerta);

		// Redirect
		response.sendRedirect("views/login.jsp");

	    } // End if-else 

	} // End finally

    } // End doPost() 

} // End class
