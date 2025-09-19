package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Contacto;
import repositorio.ContactoDAO;
import utils.ConfiguracionNoEncontradaException;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/messageDetail")
public class MessageDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            ContactoDAO dao = new ContactoDAO();
            Contacto mensaje = dao.obtenerPorId(id);

            if (mensaje != null) {
                request.setAttribute("mensaje", mensaje);
                request.getRequestDispatcher("/views/admin/message-detail.jsp").forward(request, response);
            } else {
                request.getSession().setAttribute("errorMessage", "El mensaje que buscas no existe.");
                response.sendRedirect(request.getContextPath() + "/showMessages");
            }
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("errorMessage", "ID de mensaje no válido.");
            response.sendRedirect(request.getContextPath() + "/showMessages");
        } catch (ConfiguracionNoEncontradaException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error de configuración en la base de datos.");
            request.getRequestDispatcher("/views/common/warning.jsp").forward(request, response);
        }catch (SQLException e) { 
        	e.printStackTrace();
            request.setAttribute("errorMessage", "Error de base de datos al cargar el mensaje.");
            request.getRequestDispatcher("/views/common/warning.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String action = request.getParameter("action");

            if ("visto".equals(action)) {
                ContactoDAO dao = new ContactoDAO();
                dao.marcarComoVisto(id);
                request.getSession().setAttribute("successMessage", "Mensaje marcado como visto.");
            }
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("errorMessage", "ID de mensaje no válido.");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error de base de datos al actualizar el mensaje.");
            request.getRequestDispatcher("/views/common/warning.jsp").forward(request, response);
            return;
        }
        response.sendRedirect(request.getContextPath() + "/showMessages");
    }
}
