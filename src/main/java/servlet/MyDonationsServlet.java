package servlet;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Donacion;
import modelo.Usuario;
import repositorio.DonacionDAO;

@WebServlet("/my-donations")
public class MyDonationsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/views/auth/login.jsp");
            return;
        }

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        int idUsuario = usuario.getIdUsuario();

        DonacionDAO donacionDAO = new DonacionDAO();
        List<Donacion> misDonaciones = donacionDAO.obtenerPorIdDonante(idUsuario);

        request.setAttribute("misDonaciones", misDonaciones);
        request.getRequestDispatcher("/views/user/my-donations.jsp").forward(request, response);
    }
}
