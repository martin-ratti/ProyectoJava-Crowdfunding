package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Contacto;
import repositorio.ContactoDAO;

@WebServlet(name = "ShowMessagesServlet", urlPatterns = {"/showMessages"})
public class ShowMessagesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ContactoDAO dao = new ContactoDAO();
            List<Contacto> mensajes = dao.obtenerTodos();
            request.setAttribute("mensajes", mensajes);
            request.getRequestDispatcher("/views/admin/show-messages.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error de base de datos al cargar los mensajes.");
            request.getRequestDispatcher("/views/common/warning.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/messageDetail?id=" + id);
        } else {
            response.sendRedirect(request.getContextPath() + "/showMessages");
        }
    }
}
