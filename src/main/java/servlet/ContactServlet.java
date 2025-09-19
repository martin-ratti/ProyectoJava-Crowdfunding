package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Contacto;
import repositorio.ContactoDAO;
import utils.ConfiguracionNoEncontradaException;

@WebServlet(name = "ContactServlet", urlPatterns = {"/contact"})
public class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       request.getRequestDispatcher("/views/common/contact.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        try {
            String nombre = request.getParameter("nombre");
            String email = request.getParameter("email");
            String asunto = request.getParameter("asunto");
            String mensaje = request.getParameter("mensaje");

            Contacto contacto = new Contacto();
            contacto.setNombre(nombre);
            contacto.setEmail(email);
            contacto.setAsunto(asunto);
            contacto.setMensaje(mensaje);
            contacto.setFecha(LocalDateTime.now());

            ContactoDAO dao = new ContactoDAO();
            dao.insertar(contacto);
            session.setAttribute("successMessage", "¡Tu mensaje fue enviado con éxito!");
            response.sendRedirect(request.getContextPath() + "/home");
            
        } catch (ConfiguracionNoEncontradaException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error de configuración en la base de datos.");
            request.getRequestDispatcher("/views/common/warning.jsp").forward(request, response);
        }catch (SQLException e) { 
        	e.printStackTrace();
            request.setAttribute("errorMessage", "Hubo un error de base de datos al enviar el mensaje.");
            request.getRequestDispatcher("/views/common/warning.jsp").forward(request, response);
        }
    }
}
