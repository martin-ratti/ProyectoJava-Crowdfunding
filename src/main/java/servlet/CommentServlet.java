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

import modelo.Comentario;
import modelo.Usuario;
import repositorio.ComentarioDAO;
import repositorio.DonacionDAO;
import utils.ConfiguracionNoEncontradaException;

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

        int idProyecto = 0;
        try {
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            idProyecto = Integer.parseInt(request.getParameter("idProyecto"));
            String descripcion = request.getParameter("descripcion");
            
            DonacionDAO donacionDAO = new DonacionDAO();
            if (!donacionDAO.haDonado(usuario.getIdUsuario(), idProyecto)) {
                session.setAttribute("errorMessage", "Debes donar a este proyecto para poder comentar.");
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
            
            session.setAttribute("successMessage", "Comentario publicado con éxito.");
            response.sendRedirect(request.getContextPath() + "/projectDetails?id=" + idProyecto + "#comments");

        } catch (NumberFormatException e) {
            session.setAttribute("errorMessage", "ID de proyecto inválido.");
            response.sendRedirect(request.getContextPath() + "/activeProjects");
        } catch (ConfiguracionNoEncontradaException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error de configuración en la base de datos.");
            request.getRequestDispatcher("/views/common/warning.jsp").forward(request, response);
        }catch (SQLException e) { 
        	e.printStackTrace();
            request.setAttribute("errorMessage", "Error de base de datos al publicar el comentario.");
            request.getRequestDispatcher("/views/common/warning.jsp").forward(request, response);
        }
    }
}
