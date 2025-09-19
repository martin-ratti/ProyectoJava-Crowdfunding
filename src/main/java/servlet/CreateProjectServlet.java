package servlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
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
import utils.Config;

@WebServlet(name = "CreateProjectServlet", urlPatterns = {"/createProject"})
@MultipartConfig
public class CreateProjectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            PaisDAO paisDAO = new PaisDAO();

            List<Categoria> categorias = categoriaDAO.obtenerTodos();
            List<Pais> paises = paisDAO.obtenerTodos();

            request.setAttribute("categorias", categorias);
            request.setAttribute("paises", paises);

            request.getRequestDispatcher("/views/project/create-project.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error al cargar los datos necesarios para crear un proyecto.");
            request.getRequestDispatcher("/views/common/warning.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        try {
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            if (usuario == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            String nombreProyecto = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            BigDecimal montoMeta = new BigDecimal(request.getParameter("monto_objetivo"));
            
            BigDecimal maximoPermitido = new BigDecimal("99999999999.99"); 
            if(montoMeta.compareTo(maximoPermitido) > 0) {
                session.setAttribute("errorMessage", "El monto meta no puede ser tan alto.");
                response.sendRedirect(request.getContextPath() + "/createProject");
                return;
            }

            LocalDate fechaFin = LocalDate.parse(request.getParameter("fecha_limite"));
            LocalDate fechaIni = LocalDate.now();
            int idCategoria = Integer.parseInt(request.getParameter("categoria"));
            int idPais = Integer.parseInt(request.getParameter("pais"));
            Part filePart = request.getPart("foto");

            if (filePart == null || filePart.getSize() == 0) {
                session.setAttribute("errorMessage", "❌ Debes seleccionar una imagen para el proyecto.");
                response.sendRedirect(request.getContextPath() + "/createProject");
                return;
            }

            String fileName = null;
            if (filePart != null && filePart.getSize() > 0) {
                String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String extension = "";
                int dotIndex = originalFileName.lastIndexOf(".");
                if (dotIndex >= 0) {
                    extension = originalFileName.substring(dotIndex);
                }
                fileName = UUID.randomUUID().toString() + extension;

                String uploadPath = Config.get("upload.dir");
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) uploadDir.mkdirs();

                filePart.write(new File(uploadDir, fileName).getAbsolutePath());
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
            session.setAttribute("successMessage", "¡Proyecto creado! Ahora está pendiente de revisión.");
            response.sendRedirect("myProjects");
            
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error de base de datos al crear el proyecto.");
            request.getRequestDispatcher("/views/common/warning.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("errorMessage", "Error inesperado al crear el proyecto. Revisa los datos ingresados.");
            response.sendRedirect(request.getContextPath() + "/createProject");
        }
    }
}
