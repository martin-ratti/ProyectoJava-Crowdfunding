package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Usuario;
import repositorio.UsuarioDAO;
import utils.PasswordUtils;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/auth/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            LocalDate fechaNacimiento = LocalDate.parse(request.getParameter("fecha_nacimiento"));
            String telefono = request.getParameter("telefono");

            String hashedPassword = PasswordUtils.hashPassword(password);

            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setNombre(nombre);
            nuevoUsuario.setApellido(apellido);
            nuevoUsuario.setEmail(email);
            nuevoUsuario.setPassword(hashedPassword);
            nuevoUsuario.setTelefono(telefono);
            nuevoUsuario.setFechaNacimiento(fechaNacimiento);

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario usuarioRegistrado = usuarioDAO.insertar(nuevoUsuario);

            HttpSession session = request.getSession(true);
            session.setAttribute("usuario", usuarioRegistrado);
            session.setAttribute("successMessage", "¡Registro exitoso! Bienvenido, " + nombre);
            response.sendRedirect(request.getContextPath() + "/home");

        } catch (DateTimeParseException e) {
            request.setAttribute("errorMessage", "El formato de la fecha de nacimiento no es válido.");
            request.getRequestDispatcher("/views/auth/register.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            // Verificar si es un error de email duplicado
            if (e.getMessage().contains("Duplicate entry")) {
                request.setAttribute("errorMessage", "El correo electrónico ya está en uso.");
                request.getRequestDispatcher("/views/auth/register.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Error de base de datos durante el registro.");
                request.getRequestDispatcher("/views/common/warning.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Hubo un error inesperado en el registro. Inténtalo de nuevo.");
            request.getRequestDispatcher("/views/auth/register.jsp").forward(request, response);
        }
    }
}
