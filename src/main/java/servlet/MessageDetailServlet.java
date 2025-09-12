package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Contacto;
import repositorio.ContactoDAO;
import java.io.IOException;

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
                response.sendRedirect(request.getContextPath() + "/showMessages");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/showMessages");
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
            }
        } catch (NumberFormatException e) {
        }
        response.sendRedirect(request.getContextPath() + "/showMessages");
    }
}

