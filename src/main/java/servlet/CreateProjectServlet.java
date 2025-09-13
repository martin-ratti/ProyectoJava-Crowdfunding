package servlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import modelo.Categoria;
import modelo.Pais;
import modelo.Proyecto;
import modelo.Usuario;
import repositorio.CategoriaDAO;
import repositorio.PaisDAO;
import repositorio.ProyectoDAO;

@WebServlet(name = "CreateProjectServlet", urlPatterns = {"/createProject"})
@MultipartConfig
public class CreateProjectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CategoriaDAO categoriaDAO = new CategoriaDAO();
        PaisDAO paisDAO = new PaisDAO();

        List<Categoria> categorias = categoriaDAO.obtenerTodos();
        List<Pais> paises = paisDAO.obtenerTodos();

        request.setAttribute("categorias", categorias);
        request.setAttribute("paises", paises);

        request.getRequestDispatcher("/views/project/create-project.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // ⬇️ Los names deben coincidir con el JSP
        String nombreProyecto = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");

        String montoParam = request.getParameter("monto_objetivo");
        BigDecimal montoMeta = BigDecimal.ZERO;
        if (montoParam != null && !montoParam.isEmpty()) {
            montoMeta = new BigDecimal(montoParam);
        }

        String fechaParam = request.getParameter("fecha_limite");
        LocalDate fechaFin = (fechaParam != null && !fechaParam.isEmpty())
                ? LocalDate.parse(fechaParam)
                : LocalDate.now().plusMonths(1);

        LocalDate fechaIni = LocalDate.now();

        int idCategoria = Integer.parseInt(request.getParameter("categoria"));
        int idPais = Integer.parseInt(request.getParameter("pais"));

        Part filePart = request.getPart("foto");
        String fileName = null;

        if (filePart != null && filePart.getSize() > 0) {
            String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String extension = "";
            int dotIndex = originalFileName.lastIndexOf(".");
            if (dotIndex >= 0) {
                extension = originalFileName.substring(dotIndex);
            }
            fileName = UUID.randomUUID().toString() + extension;


            
            String uploadPath = getServletContext().getRealPath("/uploads");

            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdirs();

            filePart.write(uploadPath + File.separator + fileName);
        }

        CategoriaDAO categoriaDAO = new CategoriaDAO();
        PaisDAO paisDAO = new PaisDAO();
        ProyectoDAO proyectoDAO = new ProyectoDAO();

        Categoria categoria = categoriaDAO.obtenerPorId(idCategoria);
        Pais pais = paisDAO.obtenerPorId(idPais);

        Proyecto proyecto = new Proyecto();
        proyecto.setNombreProyecto(nombreProyecto);
        proyecto.setDescripcion(descripcion);
        proyecto.setMontoMeta(montoMeta);
        proyecto.setMontoRecaudado(BigDecimal.ZERO);
        proyecto.setFechaIni(fechaIni);
        proyecto.setFechaFin(fechaFin);
        proyecto.setIdCreador(usuario.getIdUsuario());
        proyecto.setEstado("Pendiente");
        proyecto.setCategoria(categoria);
        proyecto.setPais(pais);
        proyecto.setFoto(fileName);

        proyectoDAO.insertar(proyecto);

        response.sendRedirect("myProjects");
    }
}
