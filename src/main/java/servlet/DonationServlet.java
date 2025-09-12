package servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import repositorio.DonacionDAO;
import repositorio.ProyectoDAO;
import modelo.Donacion;
import modelo.Proyecto;
import modelo.Usuario;

@WebServlet("/DonationServlet")
public class DonationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("usuario") == null) {
                response.sendRedirect(request.getContextPath() + "/views/auth/login.jsp");
                return;
            }

            Usuario usuario = (Usuario) session.getAttribute("usuario");
            int idUsuario = usuario.getIdUsuario();
            int idProyecto = Integer.parseInt(request.getParameter("idProyecto"));

            ProyectoDAO proyectoDAO = new ProyectoDAO();
            Proyecto proyecto = proyectoDAO.obtenerPorId(idProyecto);

            if (proyecto != null && proyecto.getIdCreador() == idUsuario) {
                session.setAttribute("errorMessage", "No puedes donar a tu propio proyecto.");
                response.sendRedirect(request.getContextPath() + "/projectDetails?id=" + idProyecto);
                return;
            }

            BigDecimal montoDonado = new BigDecimal(request.getParameter("monto"));
            String comentario = request.getParameter("comentario");

            Donacion donacion = new Donacion();
            donacion.setIdDonante(idUsuario);
            donacion.setIdProyecto(idProyecto);
            donacion.setMonto(montoDonado);
            donacion.setComentario(comentario);
            donacion.setFecha(LocalDate.now());

            DonacionDAO donacionDAO = new DonacionDAO();
            donacionDAO.insertar(donacion);
            
            if (proyecto != null) {
                BigDecimal montoActual = proyecto.getMontoRecaudado();
                BigDecimal nuevoMontoRecaudado = montoActual.add(montoDonado);
                proyectoDAO.actualizarMontoRecaudado(idProyecto, nuevoMontoRecaudado);
            }

            response.sendRedirect(request.getContextPath() + "/my-donations");

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Error al procesar la donación. Revisa los logs del servidor.", e);
        }
    }
}
