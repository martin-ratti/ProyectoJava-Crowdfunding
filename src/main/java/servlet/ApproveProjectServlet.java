package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repositorio.ProyectoDAO;
import modelo.Proyecto;
import java.io.IOException;

@WebServlet("/approveProject")
public class ApproveProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("idProyecto");

        if (idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                ProyectoDAO dao = new ProyectoDAO();
                Proyecto proyecto = dao.obtenerPorId(id);

                if (proyecto != null) {
                    proyecto.setEstado("Activo");
                    dao.actualizarEstado(proyecto.getIdProyecto(), "Activo");
                    request.setAttribute("successMessage", "Proyecto aprobado correctamente.");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "ID de proyecto inválido.");
            }
        }
        response.sendRedirect(request.getContextPath() + "/pendingProjects");
    }
}

