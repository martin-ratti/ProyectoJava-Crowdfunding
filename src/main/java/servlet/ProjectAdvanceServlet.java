package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Avance_Proyecto;
import modelo.Proyecto;
import repositorio.Avance_ProyectoDAO;
import repositorio.ProyectoDAO;
import utils.ConfiguracionNoEncontradaException;

@WebServlet("/projectAdvances")
public class ProjectAdvanceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProyectoDAO proyectoDAO = new ProyectoDAO();
    private Avance_ProyectoDAO avanceDAO = new Avance_ProyectoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String idParam = request.getParameter("idProyecto");
            if (idParam == null || idParam.isEmpty()) {
                response.sendRedirect(request.getContextPath() + "/activeProjects");
                return;
            }
            int idProyecto = Integer.parseInt(idParam);

            Proyecto proyecto = proyectoDAO.obtenerPorId(idProyecto);
            if (proyecto == null) {
                request.getSession().setAttribute("errorMessage", "Proyecto no encontrado.");
                response.sendRedirect(request.getContextPath() + "/activeProjects");
                return;
            }

            List<Avance_Proyecto> avances = avanceDAO.obtenerPorProyecto(idProyecto);
            Avance_Proyecto ultimoAvance = null;
            List<Avance_Proyecto> historialAvances = Collections.emptyList();

            if (!avances.isEmpty()) {
                ultimoAvance = avances.get(0);
                if (avances.size() > 1) {
                    historialAvances = avances.subList(1, avances.size());
                }
            }

            request.setAttribute("proyecto", proyecto);
            request.setAttribute("avances", avances);
            request.setAttribute("ultimoAvance", ultimoAvance);
            request.setAttribute("historialAvances", historialAvances);

            request.getRequestDispatcher("/views/project/project-advances.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            request.getSession().setAttribute("errorMessage", "ID de proyecto inválido.");
            response.sendRedirect(request.getContextPath() + "/activeProjects");
        } catch (ConfiguracionNoEncontradaException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error de configuración en la base de datos.");
            request.getRequestDispatcher("/views/common/warning.jsp").forward(request, response);
        }catch (SQLException e) { 
        	e.printStackTrace();
            request.setAttribute("errorMessage", "Error de base de datos al cargar los avances del proyecto.");
            request.getRequestDispatcher("/views/common/warning.jsp").forward(request, response);
        }
    }
}
