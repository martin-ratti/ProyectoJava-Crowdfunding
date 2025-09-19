package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
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
                request.getSession().setAttribute("errorMessage", "Proyecto no encontrado.");
                response.sendRedirect(request.getContextPath() + "/myProjects");
                return;
            }

            request.setAttribute("proyecto", p);
            request.getRequestDispatcher("/views/project/edit-project.jsp").forward(request, response);
            
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("errorMessage", "ID de proyecto inválido.");
            response.sendRedirect(request.getContextPath() + "/myProjects");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error de base de datos al cargar el proyecto para editar.");
            request.getRequestDispatcher("/views/common/warning.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = 0;
        try {
            id = Integer.parseInt(request.getParameter("idProyecto"));
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            BigDecimal montoMeta = new BigDecimal(request.getParameter("montoMeta"));
            LocalDate fechaFin = LocalDate.parse(request.getParameter("fechaFin"));
            
            ProyectoDAO dao = new ProyectoDAO();
            Proyecto p = dao.obtenerPorId(id);

            if (p == null) {
                request.getSession().setAttribute("errorMessage", "El proyecto que intentas editar ya no existe.");
                response.sendRedirect(request.getContextPath() + "/myProjects");
                return;
            }

            p.setNombreProyecto(nombre);
            p.setDescripcion(descripcion);
            p.setMontoMeta(montoMeta);
            p.setFechaFin(fechaFin);
            
            dao.actualizar(p);
            request.getSession().setAttribute("successMessage", "Proyecto actualizado correctamente.");
            response.sendRedirect(request.getContextPath() + "/myProjects");
            
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("errorMessage", "Error en el formato de los datos para editar el proyecto.");
            response.sendRedirect(request.getContextPath() + "/myProjects");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error de base de datos al guardar los cambios del proyecto.");
            request.getRequestDispatcher("/views/common/warning.jsp").forward(request, response);
        }
    }
}
