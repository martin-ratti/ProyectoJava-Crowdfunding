<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Mis Proyectos</title>
    
    <link rel="icon" href="${pageContext.request.contextPath}/assets/simbolo-dinero.png">
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/project/styles/project.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/common/styles/globals.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/fragments/styles/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/fragments/styles/footer.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/views/common/styles/glow-buttons.css">

    
</head>
<body>
    <jsp:include page="/views/fragments/header.jspf"/>

    <main class="projects-container">
        <h1>Mis Proyectos</h1>

        <c:if test="${empty myProjects}">
            <p class="no-projects">Aún no has creado proyectos.</p>
        </c:if>

        <div class="card-grid">
            <c:forEach var="p" items="${myProjects}">
			    <c:if test="${p.estado ne 'Borrado'}">
			        <div class="project-card status-${p.estado.toLowerCase()}">			        
			            <img src="${pageContext.request.contextPath}/uploads/${p.foto}" alt="Imagen del Proyecto" onclick="window.location.href='${pageContext.request.contextPath}/projectDetails?id=${p.idProyecto}'">
			            <div class="project-details-wrapper">
			                <h2 onclick="window.location.href='${pageContext.request.contextPath}/projectDetails?id=${p.idProyecto}'">${p.nombreProyecto}</h2>
			                <p><b>Descripción:</b> ${p.descripcion}</p>
			                <p><b>Monto Meta:</b> $${p.montoMeta}</p>
			                <p><b>Monto Recaudado:</b> $${p.montoRecaudado}</p>
			                <p><b>Fecha Fin:</b> ${p.fechaFin}</p>
			                <p><b>Estado:</b> <span class="status-label status-${p.estado.toLowerCase()}">${p.estado}</span></p>
			                <p><b>Categoría:</b> ${p.categoria.nombreCategoria}</p>
			                <p><b>País:</b> ${p.pais.nombrePais}</p>
			
			                <c:if test="${p.estado eq 'Cancelado'}">
			                    <p><b>Motivo Cancelación:</b> ${p.cancelacion.motivo}</p>
			                    <p><b>Fecha Cancelación:</b> ${p.cancelacion.fecha}</p>
			                </c:if>
			
                            <div class="card-actions">
                                <c:choose>
                                    <c:when test="${p.estado eq 'Cancelado' or p.estado eq 'Rechazado'}">
                                        <form action="${pageContext.request.contextPath}/deleteProject" method="post">
                                            <input type="hidden" name="idProyecto" value="${p.idProyecto}">
                                            <button type="submit" class="little-glow-btn-danger">Borrar</button>
                                        </form>
                                    </c:when>
                                    <c:when test="${p.estado eq 'Activo'}">
                                        <a href="${pageContext.request.contextPath}/addAdvance?idProyecto=${p.idProyecto}" class="little-glow-btn-inverse">Añadir Avance</a>
                                        <%-- CORRECCIÓN: Se cambió "avancesProyecto" a "projectAdvances" para que coincida con el servlet --%>
                                        <a href="${pageContext.request.contextPath}/projectAdvances?idProyecto=${p.idProyecto}" class="little-glow-btn">Ver Avances</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="${pageContext.request.contextPath}/editProject?idProyecto=${p.idProyecto}" class="little-glow-btn-inverse">Editar</a>                     
                                        <a href="${pageContext.request.contextPath}/cancelProject?idProyecto=${p.idProyecto}" class="little-glow-btn">Cancelar</a>
                                    </c:otherwise>
                                </c:choose>
                            </div>
			            </div>
			        </div>
			    </c:if>
			</c:forEach>
        </div>
    </main>

    <jsp:include page="/views/fragments/footer.jspf"/>
</body>
</html>
