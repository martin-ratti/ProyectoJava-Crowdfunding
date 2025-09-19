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
import repositorio.ProyectoDAO;
import utils.ConfiguracionNoEncontradaException;

@WebServlet(name = "DeleteProjectServlet", urlPatterns = {"/deleteProject"})
public class DeleteProjectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        Usuario usuario = (session != null) ? (Usuario) session.getAttribute("usuario") : null;
        
        if (usuario == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // La redirección depende de si es admin o usuario
        String redirectPage = usuario.esAdmin() ? "/activeProjects" : "/myProjects";

        try {
            int idProyecto = Integer.parseInt(request.getParameter("idProyecto"));
            ProyectoDAO dao = new ProyectoDAO();
            dao.borrarDefinitivamente(idProyecto);
            session.setAttribute("successMessage", "Proyecto borrado correctamente.");
        
        } catch (NumberFormatException e) {
            session.setAttribute("errorMessage", "Error: ID de proyecto inválido.");
        } catch (ConfiguracionNoEncontradaException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error de configuración en la base de datos.");
            request.getRequestDispatcher("/views/common/warning.jsp").forward(request, response);
        }catch (SQLException e) { 
        	e.printStackTrace();
            // En caso de error de BD, redirige a la página de warning
            request.getRequestDispatcher("/views/common/warning.jsp").forward(request, response);
            return;
        }
        
        response.sendRedirect(request.getContextPath() + redirectPage);
    }
}

