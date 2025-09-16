package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import modelo.Comentario;
import modelo.Donacion;
import modelo.Proyecto;
import modelo.Usuario;
import repositorio.ComentarioDAO;
import repositorio.DonacionDAO;
import repositorio.ProyectoDAO;

@WebServlet("/projectDetails")
public class ProjectDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/activeProjects");
            return;
        }

        try {
            int idProyecto = Integer.parseInt(idParam);

            ProyectoDAO proyectoDAO = new ProyectoDAO();
            Proyecto proyecto = proyectoDAO.obtenerPorId(idProyecto);

            if (proyecto == null) {
                response.sendRedirect(request.getContextPath() + "/activeProjects");
                return;
            }

            ComentarioDAO comentarioDAO = new ComentarioDAO();
            List<Comentario> comentarios = comentarioDAO.obtenerPorIdProyecto(idProyecto);
            request.setAttribute("comentarios", comentarios);

            HttpSession session = request.getSession(false);
            Usuario usuario = (session != null) ? (Usuario) session.getAttribute("usuario") : null;

            if (usuario != null) {
                DonacionDAO donacionDAO = new DonacionDAO();
                boolean haDonado = donacionDAO.haDonado(usuario.getIdUsuario(), idProyecto);
                request.setAttribute("haDonado", haDonado);

                // Obtener las donaciones del usuario para este proyecto específico
                List<Donacion> misDonacionesProyecto = donacionDAO.obtenerDonacionesPorUsuarioYProyecto(usuario.getIdUsuario(), idProyecto);
                request.setAttribute("misDonacionesProyecto", misDonacionesProyecto);

            } else {
                request.setAttribute("haDonado", false);
            }

            request.setAttribute("proyecto", proyecto);
            request.getRequestDispatcher("/views/project/project-details.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/activeProjects");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/activeProjects");
        }
    }
}
