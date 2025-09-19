package servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import modelo.Usuario;
import repositorio.ProyectoDAO;
import utils.ConfiguracionNoEncontradaException;

@WebServlet("/myProjects")
public class MyProjectsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            ProyectoDAO dao = new ProyectoDAO();
            request.setAttribute("myProjects", dao.obtenerPorUsuario(usuario.getIdUsuario()));
            request.getRequestDispatcher("/views/project/my-projects.jsp").forward(request, response);
        } catch (ConfiguracionNoEncontradaException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error de configuración en la base de datos.");
            request.getRequestDispatcher("/views/common/warning.jsp").forward(request, response);
        }catch (SQLException e) { 
        	e.printStackTrace();
            request.setAttribute("errorMessage", "Error de base de datos al cargar tus proyectos.");
            request.getRequestDispatcher("/views/common/warning.jsp").forward(request, response);
        }
    }
}
