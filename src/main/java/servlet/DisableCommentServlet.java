package servlet;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import modelo.Usuario;
import repositorio.ComentarioDAO;

@WebServlet("/disableComment")
public class DisableCommentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        // Solo los administradores pueden deshabilitar comentarios
        if (!usuario.esAdmin()) {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }
        
        String idProyectoStr = request.getParameter("idProyecto");
        String redirectUrl = request.getContextPath() + "/projectDetails?id=" + idProyectoStr + "#comments";

        try {
            int idComentario = Integer.parseInt(request.getParameter("idComentario"));
            ComentarioDAO dao = new ComentarioDAO();
            dao.deshabilitarComentario(idComentario);
            session.setAttribute("successMessage", "Comentario deshabilitado correctamente.");

        } catch (NumberFormatException e) {
            session.setAttribute("errorMessage", "Error: El ID del comentario no es válido.");
        } catch (SQLException e) {
            e.printStackTrace();
            request.getRequestDispatcher("/views/common/warning.jsp").forward(request, response);
            return;
        }
        
        response.sendRedirect(redirectUrl);
    }
}

