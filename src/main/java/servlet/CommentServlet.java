package servlet;

import java.io.IOException;
import java.time.LocalDateTime;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import modelo.Comentario;
import modelo.Usuario;
import repositorio.ComentarioDAO;
import repositorio.DonacionDAO;

@WebServlet("/comment")
public class CommentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            int idProyecto = Integer.parseInt(request.getParameter("idProyecto"));
            String descripcion = request.getParameter("descripcion");
            
            DonacionDAO donacionDAO = new DonacionDAO();
            if (!donacionDAO.haDonado(usuario.getIdUsuario(), idProyecto)) {
                response.sendRedirect(request.getContextPath() + "/projectDetails?id=" + idProyecto);
                return;
            }

            Comentario comentario = new Comentario();
            comentario.setIdProyecto(idProyecto);
            comentario.setIdUsuario(usuario.getIdUsuario());
            comentario.setDescripcion(descripcion);
            comentario.setFecha(LocalDateTime.now());
            
            ComentarioDAO comentarioDAO = new ComentarioDAO();
            comentarioDAO.insertar(comentario);

            response.sendRedirect(request.getContextPath() + "/projectDetails?id=" + idProyecto + "#comments");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/activeProjects");
        }
    }
}

