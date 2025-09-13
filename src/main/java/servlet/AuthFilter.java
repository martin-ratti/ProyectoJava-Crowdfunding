package servlet;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Usuario;

@WebFilter("/*")
public class AuthFilter implements Filter {
    private static final long serialVersionUID = 1L;

    private static final String[] adminOnlyPaths = {
        "/approveProject",
        "/rejectProject",
        "/pendingProjects",
        "/showMessages"
    };

    private static final String[] userOnlyPaths = {
        "/myProjects",
        "/my-donations",
        "/createProject",
        "/editProject",
        "/addAdvance",
        "/supportedProjects"

    };
    
    private static final String[] publicPaths = {
        "/home",
        "/login",
        "/register",
        "/activeProjects",
        "/projectDetails",
        "/categories",
        "/how-it-works",
        "/about",
        "/contact"
    };

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        String path = req.getRequestURI().substring(req.getContextPath().length());

        if (path.equals("") || path.equals("/")) {
            res.sendRedirect(req.getContextPath() + "/home");
            return;
        }
        
        if (path.startsWith("/views/") || path.startsWith("/assets/") || path.startsWith("/uploads/")) {
            chain.doFilter(request, response);
            return;
        }

        for (String pub : publicPaths) {
            if (path.equals(pub) || path.equals("/avancesProyecto")) { // Avances es público
                chain.doFilter(request, response);
                return;
            }
        }

        Usuario usuario = (session != null) ? (Usuario) session.getAttribute("usuario") : null;
        if (usuario == null) {
            res.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        boolean esAdmin = (usuario.getTelefono() == null);

        // Bloquear a usuarios normales de las páginas de administrador
        if (!esAdmin) {
            for (String adminPath : adminOnlyPaths) {
                if (path.equals(adminPath)) {
                    res.sendRedirect(req.getContextPath() + "/views/common/forbidden.jsp");
                    return;
                }
            }
        }

        // Bloquear a administradores de las páginas de usuario
        if (esAdmin) {
            for (String userPath : userOnlyPaths) {
                if (path.equals(userPath)) {
                    res.sendRedirect(req.getContextPath() + "/views/common/forbidden.jsp");
                    return;
                }
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}
    
    @Override
    public void destroy() {}
}

