<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Proyectos Apoyados</title>
    
    <link rel="icon" href="${pageContext.request.contextPath}/assets/simbolo-dinero.png">
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/project/styles/project.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/common/styles/globals.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/fragments/styles/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/fragments/styles/footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/fragments/styles/messages.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/common/styles/glow-buttons.css">
</head>
<body>
    <jsp:include page="/views/fragments/header.jspf"/>
    <%@ include file="/views/fragments/messages.jspf" %>

    <main class="projects-container">
        <h1 class="title-underline">Proyectos que Apoyaste</h1>

        <c:if test="${empty proyectosApoyados}">
            <p class="no-projects">
                Aún no has apoyado ningún proyecto.
            </p>
        </c:if>

        <div class="card-grid">
            <c:forEach var="p" items="${proyectosApoyados}">
                <div class="project-card" onclick="window.location.href='${pageContext.request.contextPath}/projectDetails?id=${p.idProyecto}'">
                    <span class="card-category">${p.categoria.nombreCategoria}</span>
					<img src="${pageContext.request.contextPath}/uploads/${p.foto}" alt="Imagen del Proyecto">
                    
                    <div class="project-details-wrapper">
                        <h2>${p.nombreProyecto}</h2>
                        <p><b>Descripción:</b> ${p.descripcion}</p>
                        <p><b>Monto Meta:</b> $${p.montoMeta}</p>
                        <p><b>Monto Recaudado:</b> $${p.montoRecaudado}</p>
                        <p><b>Fecha Fin:</b> ${p.fechaFin}</p>
                    </div>

					 <div class="card-actions">
					    <c:if test="${not empty sessionScope.usuario and not sessionScope.usuario.esAdmin()}">
					        <a href="${pageContext.request.contextPath}/views/user/donation.jsp?idProyecto=${p.idProyecto}" 
					           class="little-glow-btn-inverse" onclick="event.stopPropagation();">Donar de Nuevo</a>
					
					        <a href="${pageContext.request.contextPath}/projectAdvances?idProyecto=${p.idProyecto}" 
					           class="little-glow-btn" onclick="event.stopPropagation();">Ver Avances</a>
					    </c:if>
					</div>

                </div>
            </c:forEach>
        </div>
    </main>

    <jsp:include page="/views/fragments/footer.jspf"/>
</body>
</html>
