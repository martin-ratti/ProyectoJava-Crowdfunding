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

@WebServlet(name = "DeleteProjectServlet", urlPatterns = {"/deleteProject"})
public class DeleteProjectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        Usuario usuario = (session != null) ? (Usuario) session.getAttribute("usuario") : null;
        String redirectPage = "/myProjects"; 

        if (usuario != null && usuario.getTelefono() == null) {
            redirectPage = "/activeProjects";
        }

        try {
            int idProyecto = Integer.parseInt(request.getParameter("idProyecto"));

            ProyectoDAO dao = new ProyectoDAO();
            dao.borrarDefinitivamente(idProyecto);

            request.getSession().setAttribute("successMessage", "Proyecto borrado correctamente.");
            response.sendRedirect(request.getContextPath() + redirectPage);

        } catch (NumberFormatException e) {
            request.getSession().setAttribute("errorMessage", "ID de proyecto inválido.");
            response.sendRedirect(request.getContextPath() + redirectPage);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error de base de datos al borrar el proyecto.");
            request.getRequestDispatcher("/views/common/warning.jsp").forward(request, response);
        }
    }
}
