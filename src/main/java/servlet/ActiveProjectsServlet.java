package servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import modelo.Categoria;
import modelo.Proyecto;
import modelo.Usuario;
import repositorio.CategoriaDAO;
import repositorio.DonacionDAO;
import repositorio.ProyectoDAO;

@WebServlet("/activeProjects")
public class ActiveProjectsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProyectoDAO proyectoDAO = new ProyectoDAO();
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        DonacionDAO donacionDAO = new DonacionDAO();

        String query = request.getParameter("query");
        String categoriaParam = request.getParameter("idCategoria");
        Integer idCategoria = null;

        if (categoriaParam != null && !categoriaParam.trim().isEmpty()) {
            try {
                idCategoria = Integer.parseInt(categoriaParam);
            } catch (NumberFormatException e) {
                System.err.println("Parámetro idCategoria inválido: " + categoriaParam);
            }
        }

        List<Proyecto> proyectos = proyectoDAO.buscarProyectos(query, idCategoria);
        List<Categoria> categorias = categoriaDAO.obtenerTodos();

        HttpSession session = request.getSession(false);
        Usuario usuario = (session != null) ? (Usuario) session.getAttribute("usuario") : null;

        if (usuario != null && usuario.getTelefono() != null) {
            List<Integer> idsProyectos = proyectos.stream().map(Proyecto::getIdProyecto).collect(Collectors.toList());
            if (!idsProyectos.isEmpty()) {
                Map<Integer, Boolean> donacionesMap = donacionDAO.haDonadoEnMultiples(usuario.getIdUsuario(), idsProyectos);
                request.setAttribute("donacionesMap", donacionesMap);
            }
        }

        request.setAttribute("activeProjects", proyectos);
        request.setAttribute("categories", categorias);
        request.setAttribute("selectedCategoryId", idCategoria);

        if (query != null && !query.trim().isEmpty()) {
            request.setAttribute("searchQuery", query);
        }

        request.getRequestDispatcher("/views/project/active-projects.jsp").forward(request, response);
    }
}

