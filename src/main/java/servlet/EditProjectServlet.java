package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import modelo.Proyecto;
import repositorio.ProyectoDAO;

@WebServlet("/editProject")
public class EditProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("idProyecto"));
            ProyectoDAO dao = new ProyectoDAO();
            Proyecto p = dao.obtenerPorId(id);

            if (p == null) {
                request.setAttribute("errorMessage", "Proyecto no encontrado.");
                request.getRequestDispatcher("/views/project/edit-project.jsp").forward(request, response);
                return;
            }

            request.setAttribute("proyecto", p);
            request.getRequestDispatcher("/views/project/edit-project.jsp").forward(request, response);
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
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            BigDecimal montoMeta = new BigDecimal(request.getParameter("montoMeta"));
            LocalDate fechaFin = LocalDate.parse(request.getParameter("fechaFin"));
            ProyectoDAO dao = new ProyectoDAO();
            Proyecto p = dao.obtenerPorId(id);

            if (p == null) {
                request.setAttribute("errorMessage", "Proyecto no encontrado.");
                response.sendRedirect(request.getContextPath() + "/myProjects");
                return;
            }

            p.setNombreProyecto(nombre);
            p.setDescripcion(descripcion);
            p.setMontoMeta(montoMeta);
            p.setFechaFin(fechaFin);
            dao.actualizar(p);
            response.sendRedirect(request.getContextPath() + "/myProjects");
        } catch (Exception e) {
            e.printStackTrace();
            int id = Integer.parseInt(request.getParameter("idProyecto"));
            ProyectoDAO dao = new ProyectoDAO();
            Proyecto p = dao.obtenerPorId(id);

            request.setAttribute("proyecto", p);
            request.setAttribute("errorMessage", "Error al editar el proyecto.");
            request.getRequestDispatcher("/views/project/edit-project.jsp").forward(request, response);
        }
    }
}

