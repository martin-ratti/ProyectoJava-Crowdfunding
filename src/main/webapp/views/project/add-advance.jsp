<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Añadir Nuevo Avance</title>

    <link rel="icon" href="${pageContext.request.contextPath}/assets/simbolo-dinero.png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/common/styles/globals.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/fragments/styles/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/fragments/styles/messages.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/fragments/styles/footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/common/styles/glow-buttons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/project/styles/create-project.css">
</head>
<body>
    <jsp:include page="/views/fragments/header.jspf" />
	<jsp:include page="/views/fragments/messages.jspf" />

    <main class="main-container">
        <div class="form-container">
            <h2>Añadir Nuevo Avance</h2>
            <p>Comparte el progreso de tu proyecto con tus seguidores.</p>

            <form action="${pageContext.request.contextPath}/addAdvance"
                  method="post"
                  class="form-content"
                  enctype="multipart/form-data">

                <input type="hidden" name="idProyecto" value="${param.idProyecto}">

                <div class="form-group">
                    <label for="descripcion">Descripción del Avance</label>
	                <textarea id="descripcion" name="descripcion" rows="5"
					    placeholder="Describe qué has logrado, muestra los resultados o cuenta los próximos pasos..."
					    required>${sessionScope.descripcionTemp}</textarea>
					<%
					    session.removeAttribute("descripcionTemp");
					%>
                </div>

                

                <div class="form-group file-input">
                    <label for="foto" class="file-input-button">Selecciona una imagen para el avance</label>
                    <input type="file" id="foto" name="foto" accept="image/*">
                </div>

                <div class="form-buttons">
                    <button type="submit" class="glow-btn-inverse">Publicar Avance</button>
                    <a href="${pageContext.request.contextPath}/myProjects" class="glow-btn">Cancelar</a>
                </div>
            </form>
        </div>
    </main>

    <jsp:include page="/views/fragments/footer.jspf" />
</body>
</html>

