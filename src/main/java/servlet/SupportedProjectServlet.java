package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import modelo.*;
import repositorio.*;

@WebServlet("/supportedProjects")
public class SupportedProjectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Usuario usuario = (session != null) ? (Usuario) session.getAttribute("usuario") : null;

        if (usuario == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        ProyectoDAO proyectoDAO = new ProyectoDAO();
        List<Proyecto> proyectosApoyados = proyectoDAO.obtenerProyectosDonadosPorUsuario(usuario.getIdUsuario());

        request.setAttribute("proyectosApoyados", proyectosApoyados);
        request.getRequestDispatcher("/views/project/supported-projects.jsp").forward(request, response);
    }
}
