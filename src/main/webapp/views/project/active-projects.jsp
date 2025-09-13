<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Proyectos Activos</title>
    
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
        <h1>Proyectos Activos</h1>

        <div class="category-filters">
            <a href="${pageContext.request.contextPath}/activeProjects" 
               class="filter-btn ${empty selectedCategoryId ? 'active' : ''}">Todas</a>
            <c:forEach var="cat" items="${categories}">
                <a href="${pageContext.request.contextPath}/activeProjects?idCategoria=${cat.idCategoria}" 
                   class="filter-btn ${selectedCategoryId == cat.idCategoria ? 'active' : ''}">
                   ${cat.nombreCategoria}
                </a>
            </c:forEach>
        </div>

        <c:if test="${empty activeProjects}">
            <p class="no-projects">
                No se encontraron proyectos que coincidan con los filtros seleccionados.
            </p>
        </c:if>

        <div class="card-grid">
            <c:forEach var="p" items="${activeProjects}">
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
                        <c:choose>
                            <c:when test="${not empty sessionScope.usuario and sessionScope.usuario.telefono == null}">
                                <form action="${pageContext.request.contextPath}/deleteProject" method="post" style="display:inline;" onclick="event.stopPropagation();">
                                    <input type="hidden" name="idProyecto" value="${p.idProyecto}">
                                    <button type="submit" class="little-glow-btn-danger">Borrar</button>
                                </form>
                            </c:when>

                            <c:when test="${not empty sessionScope.usuario and sessionScope.usuario.idUsuario ne p.idCreador}">
                                <a href="${pageContext.request.contextPath}/views/user/donation.jsp?idProyecto=${p.idProyecto}" 
                                   class="little-glow-btn-inverse" onclick="event.stopPropagation();">Donar</a>

                                <c:if test="${donacionesMap[p.idProyecto]}">
                                    <a href="${pageContext.request.contextPath}/projectDetails?id=${p.idProyecto}#comments" 
                                       class="little-glow-btn" onclick="event.stopPropagation();">Comentar</a>
                                </c:if>
                            </c:when>

                            <c:when test="${not empty sessionScope.usuario and sessionScope.usuario.idUsuario eq p.idCreador}">
                            </c:when>

                            <c:otherwise>
                                <a href="${pageContext.request.contextPath}/login" 
                                   class="little-glow-btn-inverse" onclick="event.stopPropagation();">Donar</a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </c:forEach>
        </div>
    </main>

    <jsp:include page="/views/fragments/footer.jspf"/>
</body>
</html>
