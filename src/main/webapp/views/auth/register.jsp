<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registro - ImpulsaMe</title>

    <link rel="icon" href="${pageContext.request.contextPath}/assets/simbolo-dinero.png">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/auth/styles/form.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/views/common/styles/glow-buttons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/common/styles/globals.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/fragments/styles/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/fragments/styles/footer.css">
</head>
<body>
    <jsp:include page="/views/fragments/header.jspf" />

    <main class="main-container">
        <div class="form-card">
            <h2>Crea tu Cuenta</h2>
            <form action="${pageContext.request.contextPath}/register" method="post">
                <div class="form-group">
                    <input type="text" id="nombre" name="nombre" placeholder=" " required>
                    <label for="nombre">Nombre</label>
                </div>
                <div class="form-group">
                    <input type="text" id="apellido" name="apellido" placeholder=" " required>
                    <label for="apellido">Apellido</label>
                </div>
                <div class="form-group">
                    <input type="email" id="email" name="email" placeholder=" " required>
                    <label for="email">Correo electrónico</label>
                </div>
                <div class="form-group">
                    <input type="password" id="password" name="password" placeholder=" " required>
                    <label for="password">Contraseña</label>
                </div>
                <div class="form-group">
                    <input type="tel" id="telefono" name="telefono" placeholder=" " required>
                    <label for="telefono">Teléfono</label>
                </div>
                <div class="form-group">
                    <input type="date" id="fecha_nacimiento" name="fecha_nacimiento" required>
                    <label for="fecha_nacimiento">Fecha de Nacimiento</label>
                </div>
                <button type="submit" class="glow-btn">Registrarse</button>
            </form>
            <div class="form-footer">
                <p>¿Ya tienes una cuenta? <a href="${pageContext.request.contextPath}/login">Inicia Sesión</a></p>
            </div>
        </div>
    </main>

    <jsp:include page="/views/fragments/footer.jspf" />
</body>
</html>
