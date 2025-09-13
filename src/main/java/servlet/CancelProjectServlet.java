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
            ProyectoDAO dao = new ProyectoDAO();
            Proyecto proyecto = dao.obtenerPorId(id);

            if (proyecto != null) {
                request.setAttribute("proyecto", proyecto);
                request.getRequestDispatcher("/views/project/cancel-project.jsp").forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/myProjects");
            }
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
            Proyecto proyecto = dao.obtenerPorId(id);

            if (proyecto != null) {
                proyecto.setEstado("Cancelado");

                Cancelacion_Proyecto cancelacion = new Cancelacion_Proyecto();
                cancelacion.setIdProyecto(id);
                cancelacion.setMotivo(motivo);
                cancelacion.setFecha(LocalDate.now());

                dao.cancelarProyecto(proyecto, cancelacion);

                request.getSession().setAttribute("successMessage", "Proyecto cancelado con éxito.");
            }

            response.sendRedirect(request.getContextPath() + "/myProjects");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error al cancelar el proyecto.");
            request.getRequestDispatcher("/views/project/cancel-project.jsp").forward(request, response);
        }
    }
}
