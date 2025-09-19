package servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import modelo.Categoria;
import modelo.Pais;
import modelo.Proyecto;
import modelo.Usuario;
import repositorio.CategoriaDAO;
import repositorio.DonacionDAO;
import repositorio.PaisDAO;
import repositorio.ProyectoDAO;

@WebServlet("/activeProjects")
public class ActiveProjectsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ProyectoDAO proyectoDAO = new ProyectoDAO();
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            DonacionDAO donacionDAO = new DonacionDAO();

            String query = request.getParameter("query");
            String categoriaParam = request.getParameter("idCategoria");
            String paisParam = request.getParameter("idPais");
            Integer idCategoria = null;
            Integer idPais = null;

            if (categoriaParam != null && !categoriaParam.trim().isEmpty()) {
                try {
                    idCategoria = Integer.parseInt(categoriaParam);
                } catch (NumberFormatException e) {
                    request.getSession().setAttribute("errorMessage", "Categoría no válida.");
                    response.sendRedirect(request.getContextPath() + "/activeProjects");
                    return;
                }
            }
            
            if (paisParam != null && !paisParam.trim().isEmpty()) {
                try {
                    idPais = Integer.parseInt(paisParam);
                } catch (NumberFormatException e) {
                    request.getSession().setAttribute("errorMessage", "País no válido.");
                    response.sendRedirect(request.getContextPath() + "/activeProjects");
                    return;
                }
            }

            List<Proyecto> proyectos = proyectoDAO.buscarProyectos(query, idCategoria, idPais);
            List<Categoria> categorias = categoriaDAO.obtenerTodos();
            List<Pais> paises = proyectoDAO.obtenerPaisesConProyectosActivos();

            HttpSession session = request.getSession(false);
            Usuario usuario = (session != null) ? (Usuario) session.getAttribute("usuario") : null;

            if (usuario != null && !usuario.esAdmin()) {
                List<Integer> idsProyectos = proyectos.stream().map(Proyecto::getIdProyecto).collect(Collectors.toList());
                if (!idsProyectos.isEmpty()) {
                    Map<Integer, Boolean> donacionesMap = donacionDAO.haDonadoEnMultiples(usuario.getIdUsuario(), idsProyectos);
                    request.setAttribute("donacionesMap", donacionesMap);
                }
            }

            request.setAttribute("activeProjects", proyectos);
            request.setAttribute("categories", categorias);
            request.setAttribute("paises", paises);
            request.setAttribute("selectedCategoryId", idCategoria);
            request.setAttribute("selectedPaisId", idPais);


            if (query != null && !query.trim().isEmpty()) {
                request.setAttribute("searchQuery", query);
            }

            request.getRequestDispatcher("/views/project/active-projects.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error de base de datos al cargar los proyectos.");
            request.getRequestDispatcher("/views/common/warning.jsp").forward(request, response);
        }
    }
}
