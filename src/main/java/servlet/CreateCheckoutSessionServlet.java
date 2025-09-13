package servlet;

import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import utils.Config;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/create-checkout-session")
public class CreateCheckoutSessionServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        Stripe.apiKey = Config.get("stripe.secret.key");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/views/auth/login.jsp");
            return;
        }

        int idProyecto = Integer.parseInt(request.getParameter("idProyecto"));
        BigDecimal monto = new BigDecimal(request.getParameter("monto"));
        String comentario = request.getParameter("comentario");

        // Guardar temporalmente en sesión
        session.setAttribute("pendingMonto", monto);
        session.setAttribute("pendingComentario", comentario);
        session.setAttribute("pendingIdProyecto", idProyecto);

        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(request.getRequestURL().toString().replace("/create-checkout-session", "/donation-success"))
                .setCancelUrl(request.getRequestURL().toString().replace("/create-checkout-session", "/donation-cancel"))
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .addLineItem(
                        SessionCreateParams.LineItem.builder()
                                .setQuantity(1L)
                                .setPriceData(
                                        SessionCreateParams.LineItem.PriceData.builder()
                                                .setCurrency("ars")
                                                .setUnitAmount(monto.multiply(new BigDecimal("100")).longValue()) // centavos
                                                .setProductData(
                                                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                .setName("Donación al proyecto")
                                                                .build()
                                                )
                                                .build()
                                )
                                .build()
                )
                .build();

        try {
            Session stripeSession = Session.create(params);
            response.sendRedirect(stripeSession.getUrl());
        } catch (com.stripe.exception.StripeException e) {
            e.printStackTrace(); // o loguealo
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al crear sesión de pago");
        }
    }
}
