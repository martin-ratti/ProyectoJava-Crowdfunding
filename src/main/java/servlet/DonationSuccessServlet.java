package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import modelo.Donacion;
import modelo.Proyecto;
import modelo.Usuario;
import repositorio.DonacionDAO;
import repositorio.ProyectoDAO;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet("/donation-success")
public class DonationSuccessServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/views/auth/login.jsp");
            return;
        }
        
        int idProyecto = 0;
        try {
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            int idUsuario = usuario.getIdUsuario();
    
            BigDecimal monto = (BigDecimal) session.getAttribute("pendingMonto");
            String comentario = (String) session.getAttribute("pendingComentario");
            idProyecto = (int) session.getAttribute("pendingIdProyecto");
    
            ProyectoDAO proyectoDAO = new ProyectoDAO();
            Proyecto proyecto = proyectoDAO.obtenerPorId(idProyecto);
    
            if (proyecto != null && proyecto.getIdCreador() == idUsuario) {
                session.setAttribute("errorMessage", "No puedes donar a tu propio proyecto.");
                response.sendRedirect(request.getContextPath() + "/projectDetails?id=" + idProyecto);
                return;
            }
    
            Donacion donacion = new Donacion();
            donacion.setIdDonante(idUsuario);
            donacion.setIdProyecto(idProyecto);
            donacion.setMonto(monto);
            donacion.setComentario(comentario);
            donacion.setFecha(LocalDate.now());
    
            DonacionDAO donacionDAO = new DonacionDAO();
            donacionDAO.insertar(donacion);
    
            if (proyecto != null) {
                BigDecimal nuevoMonto = proyecto.getMontoRecaudado().add(monto);
                proyectoDAO.actualizarMontoRecaudado(idProyecto, nuevoMonto);
            }
    
            session.removeAttribute("pendingMonto");
            session.removeAttribute("pendingComentario");
            session.removeAttribute("pendingIdProyecto");
    
            session.setAttribute("successMessage", "¡Gracias por tu donación!");
            response.sendRedirect(request.getContextPath() + "/projectDetails?id=" + idProyecto + "#user-donations");

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Hubo un error al procesar tu donación en la base de datos.");
            request.getRequestDispatcher("/views/common/warning.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("errorMessage", "Ocurrió un error inesperado después del pago. Por favor, revisa tus donaciones.");
            response.sendRedirect(request.getContextPath() + (idProyecto > 0 ? "/projectDetails?id=" + idProyecto : "/activeProjects"));
        }
    }
}
