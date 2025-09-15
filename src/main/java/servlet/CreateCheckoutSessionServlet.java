package servlet;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import utils.Config;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;

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

        // Validar parámetros obligatorios
        String strMonto = request.getParameter("monto");
        String strIdProyecto = request.getParameter("idProyecto");
        String comentario = request.getParameter("comentario");

        if (strMonto == null || strIdProyecto == null || strMonto.isEmpty() || strIdProyecto.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Faltan parámetros obligatorios");
            return;
        }

        BigDecimal monto;
        int idProyecto;

        try {
            monto = new BigDecimal(strMonto);
            idProyecto = Integer.parseInt(strIdProyecto);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parámetros inválidos");
            return;
        }

        // Evitar reintentos duplicados
        String currentAttemptId = UUID.randomUUID().toString();
        session.setAttribute("paymentAttemptId", currentAttemptId);

        // Guardar temporalmente en sesión
        session.setAttribute("pendingMonto", monto);
        session.setAttribute("pendingComentario", comentario != null ? comentario : ""); // Valor por defecto vacío
        session.setAttribute("pendingIdProyecto", idProyecto);

        // Construir URLs absolutas
        String baseUrl = request.getRequestURL().toString().replace(request.getRequestURI(), request.getContextPath());
        String successUrl = baseUrl + "/donation-success";
        String cancelUrl = baseUrl + "/donation-cancel";

        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(successUrl)
                .setCancelUrl(cancelUrl)
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
            System.out.println("Stripe Session creada: " + stripeSession.getId());
            response.sendRedirect(stripeSession.getUrl());
        } catch (StripeException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al crear sesión de pago: " + e.getMessage());
        }
    }
}
