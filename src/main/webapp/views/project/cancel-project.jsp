<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Cancelar Proyecto</title>
    <link rel="icon" href="${pageContext.request.contextPath}/assets/simbolo-dinero.png">
    
    <!-- Enlazamos a su propia hoja de estilos para evitar conflictos -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/project/styles/cancel-project.css">
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/common/styles/globals.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/fragments/styles/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/fragments/styles/footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/common/styles/glow-buttons.css">
</head>
<body>
    <jsp:include page="/views/fragments/header.jspf" />

    <main class="main-container">
        <div class="form-card">
            <h2>Cancelar Proyecto</h2>
            <p>Por favor, explícanos por qué deseas cancelar este proyecto.</p>

            <form action="${pageContext.request.contextPath}/cancelProject" method="post" class="form-content">
                <input type="hidden" name="idProyecto" value="${idProyecto}">

                <div class="form-group">
                    <label for="motivo">Motivo de Cancelación</label>
                    <textarea id="motivo" name="motivo" rows="5" placeholder="Ej: No logré conseguir el apoyo inicial esperado..." required></textarea>
                </div>

                <div class="form-buttons">
                    <button type="submit" class="glow-btn-danger">Confirmar Cancelación</button>
                    <a href="${pageContext.request.contextPath}/myProjects" class="glow-btn">Volver</a>
                </div>
            </form>
        </div>
    </main>

    <jsp:include page="/views/fragments/footer.jspf" />
</body>
</html>

