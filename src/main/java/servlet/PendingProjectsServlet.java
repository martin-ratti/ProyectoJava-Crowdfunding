package servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import repositorio.ProyectoDAO;
import utils.ConfiguracionNoEncontradaException;

@WebServlet("/pendingProjects")
public class PendingProjectsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ProyectoDAO dao = new ProyectoDAO();
            request.setAttribute("pendingProjects", dao.obtenerPendientes());
            request.getRequestDispatcher("/views/project/pending-projects.jsp").forward(request, response);
        } catch (ConfiguracionNoEncontradaException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error de configuración en la base de datos.");
            request.getRequestDispatcher("/views/common/warning.jsp").forward(request, response);
        }catch (SQLException e) { 
        	e.printStackTrace();
            request.setAttribute("errorMessage", "Error de base de datos al cargar los proyectos pendientes.");
            request.getRequestDispatcher("/views/common/warning.jsp").forward(request, response);
        }
    }
}
