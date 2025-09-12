<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Realizar Donación</title>
    <link rel="icon" href="${pageContext.request.contextPath}/assets/simbolo-dinero.png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/common/styles/globals.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/fragments/styles/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/fragments/styles/footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/common/styles/glow-buttons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/user/styles/donation.css">
</head>
<body>
    <jsp:include page="/views/fragments/header.jspf"/>

    <main class="donation-container">
        <div class="donation-card">
            <h1>Hacer una Donación</h1>
            <p>Estás a un paso de apoyar este proyecto.</p>

            <form action="${pageContext.request.contextPath}/DonationServlet" method="post" class="donation-form">
                
                <input type="hidden" name="idProyecto" value="${param.idProyecto}">

                <div class="form-group">
                    <input type="number" step="0.01" name="monto" id="monto" placeholder=" " required>
                    <label for="monto">Monto a donar (ARS)</label>
                </div>
                
                <div class="form-group">
                    <textarea name="comentario" id="comentario" rows="4" placeholder=" "></textarea>
                    <label for="comentario">Comentario (opcional)</label>
                </div>

                <button type="submit" class="glow-btn-inverse">Confirmar Donación</button>
            </form>
        </div>
    </main>

    <jsp:include page="/views/fragments/footer.jspf"/>
</body>
</html>

