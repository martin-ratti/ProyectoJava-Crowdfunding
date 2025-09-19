package servlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.UUID;
import java.time.LocalDate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import modelo.Avance_Proyecto;
import repositorio.Avance_ProyectoDAO;
import utils.Config;
@WebServlet("/addAdvance")
@MultipartConfig
public class AddAdvanceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idProyectoParam = request.getParameter("idProyecto");
        if (idProyectoParam == null || idProyectoParam.isEmpty()) {
            response.sendRedirect("myProjects");
            return;
        }
        request.setAttribute("idProyecto", idProyectoParam);
        request.getRequestDispatcher("/views/project/add-advance.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int idProyecto = 0;

        try {
            idProyecto = Integer.parseInt(request.getParameter("idProyecto"));
            String descripcion = request.getParameter("descripcion");
            Part filePart = request.getPart("foto");

            if (filePart == null || filePart.getSize() == 0) {
                session.setAttribute("errorMessage", "❌ Debes seleccionar una imagen para el avance.");
                session.setAttribute("descripcionTemp", descripcion);
                response.sendRedirect(request.getContextPath() + "/addAdvance?idProyecto=" + idProyecto);
                return;
            }

            String fileName = null;
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

            Avance_Proyecto avance = new Avance_Proyecto();
            avance.setIdProyecto(idProyecto);
            avance.setDescripcion(descripcion);
            avance.setFecha(LocalDate.now());
            avance.setFoto(fileName);

            Avance_ProyectoDAO avanceDAO = new Avance_ProyectoDAO();
            avanceDAO.insertar(avance);

            session.setAttribute("successMessage", "✅ Avance agregado correctamente.");
            response.sendRedirect(request.getContextPath() + "/projectDetails?id=" + idProyecto);

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error de base de datos al guardar el avance.");
            request.getRequestDispatcher("/views/common/warning.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("errorMessage", "Ocurrió un error inesperado al agregar el avance.");
            response.sendRedirect(request.getContextPath() + "/myProjects");
        }
    }
}
