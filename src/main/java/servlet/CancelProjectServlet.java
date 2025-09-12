package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import modelo.Cancelacion_Proyecto;
import modelo.Proyecto;
import repositorio.ProyectoDAO;

@WebServlet("/cancelProject")
public class CancelProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("idProyecto"));
            request.setAttribute("idProyecto", id);
            request.getRequestDispatcher("/views/project/cancel-project.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/myProjects");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("idProyecto"));
            String motivo = request.getParameter("motivo");
            ProyectoDAO dao = new ProyectoDAO();
            Proyecto p = dao.obtenerPorId(id);

            if (p != null) {
                p.setEstado("Cancelado");
                Cancelacion_Proyecto c = new Cancelacion_Proyecto();
                c.setIdProyecto(id);
                c.setMotivo(motivo);
                c.setFecha(LocalDate.now());
                dao.cancelarProyecto(p, c);
            }
            response.sendRedirect(request.getContextPath() + "/myProjects");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error al cancelar el proyecto.");
            request.getRequestDispatcher("/views/project/cancel-project.jsp").forward(request, response);
        }
    }
}

