package servlet;

import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Usuario;
import repositorio.UsuarioDAO;
import utils.ConfiguracionNoEncontradaException;
import utils.PasswordUtils;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/auth/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String passwordIngresada = request.getParameter("password");

        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario usuario = usuarioDAO.obtenerPorEmail(email);

            if (usuario != null && PasswordUtils.checkPassword(passwordIngresada, usuario.getPassword())) {
                HttpSession oldSession = request.getSession(false);
                if (oldSession != null) {
                    oldSession.invalidate();
                }
                HttpSession newSession = request.getSession(true);
                newSession.setAttribute("usuario", usuario);
                newSession.setAttribute("successMessage", "¡Bienvenido, " + usuario.getNombre() + "!");
                response.sendRedirect(request.getContextPath() + "/activeProjects");
            } else {
                request.setAttribute("errorMessage", "Email o contraseña incorrectos");
                request.getRequestDispatcher("/views/auth/login.jsp").forward(request, response);
            }
        } catch (ConfiguracionNoEncontradaException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error de configuración en la base de datos.");
            request.getRequestDispatcher("/views/common/warning.jsp").forward(request, response);
        }catch (SQLException e) {            e.printStackTrace();
            request.setAttribute("errorMessage", "Error de conexión. No se pudo verificar tu cuenta.");
            request.getRequestDispatcher("/views/common/warning.jsp").forward(request, response);
        }
    }
}
