package servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import repositorio.ProyectoDAO;

@WebServlet("/pendingProjects")
public class PendingProjectsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProyectoDAO dao = new ProyectoDAO();
        request.setAttribute("pendingProjects", dao.obtenerPendientes());
        request.getRequestDispatcher("/views/project/pending-projects.jsp").forward(request, response);
    }
}

