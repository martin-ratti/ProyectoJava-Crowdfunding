package servlet;

import java.io.IOException;

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

        if (usuario.getTelefono() != null) {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }

        int idComentario = Integer.parseInt(request.getParameter("idComentario"));
        int idProyecto = Integer.parseInt(request.getParameter("idProyecto"));

        ComentarioDAO dao = new ComentarioDAO();
        dao.deshabilitarComentario(idComentario);

        response.sendRedirect(request.getContextPath() + "/projectDetails?id=" + idProyecto + "#comments");
    }
}
