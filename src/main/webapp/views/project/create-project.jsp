<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="modelo.Usuario" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Crear Nuevo Proyecto - ImpulsaMe</title>
    
    <link rel="icon" href="${pageContext.request.contextPath}/assets/simbolo-dinero.png">
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/project/styles/create-project.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/common/styles/globals.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/fragments/styles/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/fragments/styles/messages.css">
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/fragments/styles/footer.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/views/common/styles/glow-buttons.css">
    
</head>
<body>
    <jsp:include page="/views/fragments/header.jspf" />

    <main class="main-container">
        <div class="form-container">
            <h2>Lanza tu Proyecto</h2>
            <p>Completa los siguientes campos para dar de alta tu campaña de crowdfunding.</p>
            
            <jsp:include page="/views/fragments/messages.jspf" />
            
            <% 
                Usuario usuario = (Usuario) session.getAttribute("usuario");
                if (usuario == null) {
                    response.sendRedirect(request.getContextPath() + "/login");
                    return;
                }
            %>

            <form action="${pageContext.request.contextPath}/createProject" 
		      method="post" 
		      class="form-content" 
		      enctype="multipart/form-data">
			    <div class="form-group">
			        <label for="nombre">Título del Proyecto</label>
			        <input type="text" id="nombre" name="nombre" placeholder="Ej: Aplicación para reciclar plástico" required>
			    </div>
			
			    <div class="form-group">
			        <label for="descripcion">Descripción</label>
			        <textarea id="descripcion" name="descripcion" rows="5" placeholder="Describe en detalle tu proyecto..." required></textarea>
			    </div>
			
			    <div class="form-group">
			        <label for="monto_objetivo">Monto Objetivo ($)</label>
			        <input type="number" id="monto_objetivo" name="monto_objetivo" step="0.01" min="1" placeholder="Ej: 50000" required>
			    </div>
			
			    <div class="form-group">
			        <label for="fecha_limite">Fecha Límite</label>
			        <input type="date" id="fecha_limite" name="fecha_limite" required>
			    </div>
			
			    <div class="form-group">
			        <label for="categoria">Categoría</label>
			        <select id="categoria" name="categoria" required>
			            <option value="" disabled selected>&lt;&lt;Seleccione una opción&gt;&gt;</option>
			            <c:forEach var="c" items="${categorias}">
			                <option value="${c.idCategoria}">${c.nombreCategoria}</option>
			            </c:forEach>
			        </select>
			    </div>
			
			    <div class="form-group">
			        <label for="pais">País</label>
			        <select id="pais" name="pais" required>
			            <option value="" disabled selected>&lt;&lt;Seleccione una opción&gt;&gt;</option>
			            <c:forEach var="p" items="${paises}">
			                <option value="${p.idPais}">${p.nombrePais}</option>
			            </c:forEach>
			        </select>
			    </div>
			
				<div class="form-group file-input">
				    <label for="foto" class="file-input-button" id="file-label-create">Selecciona una imagen para tu proyecto</label>
				    <input type="file" id="foto" name="foto" accept="image/*">
				</div>
			
			    <button type="submit" class="btn-submit">Crear Proyecto</button>
			</form>

        </div>
    </main>

    <jsp:include page="/views/fragments/footer.jspf" />
    
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const fileInput = document.getElementById('foto');
            const fileLabel = document.getElementById('file-label-create');
            const originalLabelText = fileLabel.textContent;
    
            fileInput.addEventListener('change', function() {
                if (this.files && this.files.length > 0) {
                    fileLabel.textContent = this.files[0].name;
                    fileLabel.classList.add('file-selected');
                } else {
                    fileLabel.textContent = originalLabelText;
                    fileLabel.classList.remove('file-selected');
                }
            });
        });
    </script>
</body>
</html>
