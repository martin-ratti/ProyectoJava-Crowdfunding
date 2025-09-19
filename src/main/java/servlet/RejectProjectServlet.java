package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repositorio.ProyectoDAO;
import modelo.Proyecto;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/rejectProject")
public class RejectProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String idParam = request.getParameter("idProyecto");
            if (idParam != null && !idParam.isEmpty()) {
                int id = Integer.parseInt(idParam);
                ProyectoDAO dao = new ProyectoDAO();
                Proyecto proyecto = dao.obtenerPorId(id);

                if (proyecto != null) {
                    dao.actualizarEstado(proyecto.getIdProyecto(), "Rechazado");
                    request.getSession().setAttribute("successMessage", "Proyecto rechazado correctamente.");
                } else {
                    request.getSession().setAttribute("errorMessage", "El proyecto a rechazar no fue encontrado.");
                }
            }
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("errorMessage", "ID de proyecto inválido.");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error de base de datos al rechazar el proyecto.");
            request.getRequestDispatcher("/views/common/warning.jsp").forward(request, response);
            return;
        }
        response.sendRedirect(request.getContextPath() + "/pendingProjects");
    }
}
