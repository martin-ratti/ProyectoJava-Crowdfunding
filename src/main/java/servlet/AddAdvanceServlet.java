package servlet;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import modelo.Avance_Proyecto;
import repositorio.Avance_ProyectoDAO;

@MultipartConfig
public class AddAdvanceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIR = "uploads";

    private Avance_ProyectoDAO avanceDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        avanceDAO = new Avance_ProyectoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String idProyectoStr = request.getParameter("idProyecto");
        if (idProyectoStr != null && !idProyectoStr.isEmpty()) {
            request.setAttribute("idProyecto", idProyectoStr);
            request.getRequestDispatcher("/views/project/add-advance.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/myProjects");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {
            int idProyecto = Integer.parseInt(request.getParameter("idProyecto"));
            String descripcion = request.getParameter("descripcion");
            
            Part filePart = request.getPart("foto");
            String fileName = filePart.getSubmittedFileName();
            
            String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            filePart.write(uploadPath + File.separator + fileName);

            Avance_Proyecto nuevoAvance = new Avance_Proyecto();
            nuevoAvance.setIdProyecto(idProyecto);
            nuevoAvance.setDescripcion(descripcion);
            nuevoAvance.setFoto(fileName);
            nuevoAvance.setFecha(LocalDate.now());

            avanceDAO.insertar(nuevoAvance);

            response.sendRedirect(request.getContextPath() + "/avancesProyecto?idProyecto=" + idProyecto);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error al agregar el avance.");
            request.getRequestDispatcher("/views/project/add-advance.jsp").forward(request, response);
        }
    }
}

