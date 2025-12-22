<h1>🚀 ImpulsaMe - Plataforma de Crowdfunding en Java</h1>

<div align="center">
    <a href="https://github.com/martin-ratti/ProyectoJava-Crowdfunding" target="_blank" style="text-decoration: none;">
        <img src="https://img.shields.io/badge/💻%20Repo%20Principal-ImpulsaMe-0b7285?style=for-the-badge&logo=github&logoColor=white" alt="Repo ImpulsaMe"/>
    </a>
    <a href="https://drive.google.com/drive/folders/1-iHSWlcJdVT-4DLdjdrMbCkB9aubQ5DZ?usp=sharing" target="_blank" style="text-decoration: none;">
        <img src="https://img.shields.io/badge/📄%20Documentación%20Completa-Google%20Drive-34a853?style=for-the-badge&logo=googledrive&logoColor=white" alt="Docs Drive"/>
    </a>
</div>

<p align="center">
    <a href="https://github.com/agussantinelli" target="_blank" style="text-decoration: none;">
        <img src="https://img.shields.io/badge/👤%20Agustín%20Santinelli-agussantinelli-000000?style=for-the-badge&logo=github&logoColor=white" alt="Agus"/>
    </a>
    <a href="https://github.com/juaquin11" target="_blank" style="text-decoration: none;">
        <img src="https://img.shields.io/badge/👤%20Joaquín%20Peralta-juaquin11-000000?style=for-the-badge&logo=github&logoColor=white" alt="Joaquin"/>
    </a>
    <a href="https://github.com/martin-ratti" target="_blank" style="text-decoration: none;">
        <img src="https://img.shields.io/badge/👤%20Martín%20Ratti-martin--ratti-000000?style=for-the-badge&logo=github&logoColor=white" alt="Martin"/>
    </a>
</p>

<p align="center">
    <img src="https://img.shields.io/badge/Java-Servlets%20%7C%20JSP-007396?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java Badge"/>
    <img src="https://img.shields.io/badge/Web-Tomcat-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=black" alt="Tomcat Badge"/>
    <img src="https://img.shields.io/badge/DB-MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL Badge"/>
    <img src="https://img.shields.io/badge/Build-Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" alt="Maven Badge"/>
    <img src="https://img.shields.io/badge/Payments-Stripe-626CD9?style=for-the-badge&logo=stripe&logoColor=white" alt="Stripe Badge"/>
</p>

<hr>

<h2>🎯 Objetivo y Alcance</h2>

<p>
    <strong>ImpulsaMe</strong> es una plataforma web de <strong>crowdfunding</strong> desarrollada en Java (Servlets + JSP)
    que permite crear, explorar y financiar proyectos mediante donaciones.
</p>

<p>
    El sistema implementa un flujo completo de moderación y roles, donde cada proyecto pasa por estados
    (<code>Pendiente → Activo → Cancelado/Borrado</code>) y sólo los proyectos aprobados son visibles
    para recibir donaciones.
</p>

<ul>
    <li><strong>Creadores de proyectos</strong>: publican proyectos que quedan en estado pendiente hasta ser moderados.</li>
    <li><strong>Usuarios registrados</strong>: donan a proyectos activos y pueden comentar sólo si han donado.</li>
    <li><strong>Administradores</strong>: aprueban/rechazan proyectos y moderan comentarios.</li>
    <li><strong>Visitantes anónimos</strong>: navegan proyectos activos sin necesidad de autenticarse.</li>
</ul>

<p>
    Toda la documentación extendida (diagramas, casos de uso, modelo de dominio, etc.) se encuentra en:
    <a href="https://drive.google.com/drive/folders/1-iHSWlcJdVT-4DLdjdrMbCkB9aubQ5DZ?usp=sharing" target="_blank">
        Google Drive – ImpulsaMe
    </a>.
</p>

<hr>

<h2>⚙️ Stack Tecnológico</h2>

<table>
 <thead>
  <tr>
   <th>Componente</th>
   <th>Tecnología</th>
   <th>Notas</th>
  </tr>
 </thead>
 <tbody>
  <tr>
   <td><strong>Backend Web</strong></td>
   <td>Java Servlets + JSP + JSTL</td>
   <td>Implementa controladores, vistas y lógica de negocio.</td>
  </tr>
  <tr>
   <td><strong>Servidor de Aplicaciones</strong></td>
   <td>Apache Tomcat</td>
   <td>Contenedor de Servlets para desplegar la aplicación.</td>
  </tr>
  <tr>
   <td><strong>Base de datos</strong></td>
   <td>MySQL</td>
   <td>Almacena usuarios, proyectos, donaciones, comentarios y cancelaciones.</td>
  </tr>
  <tr>
   <td><strong>Persistencia</strong></td>
   <td>DAOs + JDBC</td>
   <td>Capa de acceso a datos orientada a objetos (DAO pattern).</td>
  </tr>
  <tr>
   <td><strong>Pagos</strong></td>
   <td>Stripe Checkout</td>
   <td>Procesamiento de donaciones en ARS con validaciones de monto.</td>
  </tr>
  <tr>
   <td><strong>Build &amp; Dependencias</strong></td>
   <td>Maven</td>
   <td>Gestión de dependencias y empaquetado en <code>.war</code>.</td>
  </tr>
  <tr>
   <td><strong>Configuración</strong></td>
   <td><code>config.properties</code>, <code>database.properties</code>, <code>web.xml</code></td>
   <td>Configuración de BD, Stripe, uploads y mapeo de servlets/filtros.</td>
  </tr>
 </tbody>
</table>

<hr>

<h2>🏗️ Arquitectura de la Solución</h2>

<p>ImpulsaMe sigue una arquitectura en <strong>tres capas</strong> bien definidas:</p>

<ul>
    <li><strong>Capa de Presentación (JSP)</strong>
        <ul>
            <li>Vistas que renderizan HTML y muestran datos a los usuarios.</li>
            <li>Formularios de login, registro, creación de proyectos, donaciones, etc.</li>
            <li>Ejemplos: <code>active-projects.jsp</code>, <code>project-details.jsp</code>, vistas de login/registro.</li>
        </ul>
    </li>
    <li><strong>Capa de Control (Servlets + Filtros)</strong>
        <ul>
            <li>Procesan peticiones HTTP y aplican reglas de negocio.</li>
            <li>Coordinan DAOs y seleccionan qué vista mostrar.</li>
            <li>Ejemplos: <code>ActiveProjectsServlet</code>, <code>CreateProjectServlet</code>, <code>CreateCheckoutSessionServlet</code>, <code>DisableCommentServlet</code>, <code>AuthFilter</code>.</li>
        </ul>
    </li>
    <li><strong>Capa de Acceso a Datos (DAOs)</strong>
        <ul>
            <li>Encapsulan el acceso a MySQL mediante JDBC.</li>
            <li>Mapean filas de la BD a objetos Java.</li>
            <li>Ejemplos: <code>ProyectoDAO</code>, <code>DonacionDAO</code>, <code>ComentarioDAO</code>, DAOs de usuario.</li>
        </ul>
    </li>
</ul>

<hr>

<h2>🧩 Entidades de Dominio Clave</h2>

<ul>
    <li><strong>Usuario</strong>
        <ul>
            <li>Campo <code>telefono</code>:
                <ul>
                    <li><code>NULL</code> → usuario administrador.</li>
                    <li><code>NO NULL</code> → usuario regular.</li>
                </ul>
            </li>
        </ul>
    </li>
    <li><strong>Proyecto</strong>
        <ul>
            <li>Estados: <code>Pendiente</code>, <code>Activo</code>, <code>Cancelado</code>, <code>Borrado</code>.</li>
            <li>Campo <code>foto</code>: nombre de archivo UUID, con imágenes en <code>uploads/</code>.</li>
            <li>Relación con el creador (<code>idCreador</code>) para identificar al dueño del proyecto.</li>
        </ul>
    </li>
    <li><strong>Donación</strong>
        <ul>
            <li>Relaciona usuario ↔ proyecto + monto donado.</li>
            <li>Integra con Stripe mediante <code>paymentAttemptId</code> (UUID) para evitar duplicados.</li>
        </ul>
    </li>
    <li><strong>Comentario</strong>
        <ul>
            <li>Campo <code>estado</code> con valores como <code>Activo</code> / <code>Ignorado</code>.</li>
            <li>Soft delete: comentarios ignorados no se muestran, pero quedan en BD.</li>
        </ul>
    </li>
    <li><strong>Cancelación de Proyecto</strong>
        <ul>
            <li>Tabla opcional (<code>cancelacion_proyecto</code>) con información cuando un proyecto es cancelado por su dueño.</li>
        </ul>
    </li>
</ul>

<hr>

<h2>👤 Roles y Reglas de Negocio</h2>

<table>
    <thead>
      <tr>
        <th>Rol</th>
        <th>Identificación</th>
        <th>Puede</th>
        <th>No puede</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td><strong>Visitante anónimo</strong></td>
        <td>Sin sesión</td>
        <td>
          <ul>
            <li>Navegar proyectos activos.</li>
            <li>Ver detalles de proyectos.</li>
          </ul>
        </td>
        <td>
          <ul>
            <li>Donar.</li>
            <li>Comentar.</li>
            <li>Crear proyectos.</li>
          </ul>
        </td>
      </tr>
      <tr>
        <td><strong>Usuario registrado</strong></td>
        <td><code>telefono != NULL</code></td>
        <td>
          <ul>
            <li>Crear proyectos (quedan en estado Pendiente).</li>
            <li>Donar a proyectos activos.</li>
            <li>Comentar proyectos a los que ha donado.</li>
          </ul>
        </td>
        <td>
          <ul>
            <li>Moderación de proyectos.</li>
            <li>Deshabilitar comentarios.</li>
          </ul>
        </td>
      </tr>
      <tr>
        <td><strong>Administrador</strong></td>
        <td><code>telefono = NULL</code></td>
        <td>
          <ul>
            <li>Aprobar / rechazar proyectos pendientes.</li>
            <li>Deshabilitar comentarios inapropiados.</li>
          </ul>
        </td>
        <td>
          <ul>
            <li>Crear proyectos.</li>
            <li>Donar a proyectos.</li>
          </ul>
        </td>
      </tr>
      <tr>
        <td><strong>Dueño del proyecto</strong></td>
        <td><code>idUsuario == idCreador</code></td>
        <td>
          <ul>
            <li>Agregar avances/actualizaciones a su proyecto.</li>
            <li>Cancelar su propio proyecto activo.</li>
          </ul>
        </td>
        <td>
          <ul>
            <li>Donar a su propio proyecto.</li>
            <li>Comentar su propio proyecto.</li>
          </ul>
        </td>
      </tr>
    </tbody>
</table>

<p><strong>Reglas destacadas:</strong></p>
<ul>
    <li>Para comentar en un proyecto primero hay que haber donado.</li>
    <li>Admins no pueden crear proyectos ni donar.</li>
    <li>Dueños no pueden donar ni comentar su propio proyecto.</li>
    <li>Sólo proyectos en estado <code>Activo</code> pueden recibir donaciones.</li>
</ul>

<hr>

<h2>📈 Flujo de Proyectos y Donaciones</h2>

<h3>Estado de los proyectos</h3>

<ol>
    <li><strong>Creación</strong> – un usuario crea un proyecto, se guarda como <code>Pendiente</code>.</li>
    <li><strong>Moderación</strong> – el administrador revisa la cola en <code>/pendingProjects</code> y aprueba/rechaza.</li>
    <li><strong>Recaudación Activa</strong> – los proyectos aprobados pasan a <code>Activo</code> y aparecen en <code>/activeProjects</code>.</li>
    <li><strong>Cancelación</strong> – el dueño puede cancelar un proyecto activo (se registra en <code>cancelacion_proyecto</code> si aplica).</li>
    <li><strong>Borrado lógico</strong> – se marca como <code>Borrado</code> y se excluye de las consultas estándar.</li>
</ol>

<h3>Integración de pagos con Stripe</h3>

<ul>
    <li>Moneda: <strong>ARS</strong> (peso argentino).</li>
    <li>Rango de montos permitido: <strong>1000 – 999.999,99</strong>.</li>
    <li>Clave secreta: se configura en <code>config.properties</code> como <code>stripe.secret.key</code>.</li>
    <li>Se usa un <strong>UUID</strong> (<code>paymentAttemptId</code>) para evitar donaciones duplicadas por reenvío de formularios.</li>
</ul>

<hr>

<h2>🛠️ Archivos de Configuración Clave</h2>

<table>
    <thead>
      <tr>
        <th>Archivo</th>
        <th>Rol</th>
        <th>Propiedades destacadas</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td><code>config.properties</code></td>
        <td>Configuración general de la app</td>
        <td>
          <ul>
            <li><code>upload.dir</code> – ruta física donde se guardan las imágenes de proyectos.</li>
            <li><code>stripe.secret.key</code> – clave secreta de Stripe.</li>
          </ul>
        </td>
      </tr>
      <tr>
        <td><code>database.properties</code></td>
        <td>Conexión a MySQL</td>
        <td>
          Contiene el URL JDBC, usuario, contraseña y driver para la base MySQL.
        </td>
      </tr>
      <tr>
        <td><code>web.xml</code></td>
        <td>Descriptor de despliegue</td>
        <td>
          Define mapeos de servlets, filtros (incluyendo <code>AuthFilter</code>) y páginas de bienvenida.
        </td>
      </tr>
    </tbody>
</table>

<hr>

<h2>🧭 Puntos de Navegación</h2>

<h3>Páginas públicas (sin login)</h3>
<ul>
    <li><code>/home</code> – landing page con contenido destacado.</li>
    <li><code>/activeProjects</code> – listado de proyectos activos con filtros.</li>
    <li><code>/projectDetails</code> – detalle de un proyecto individual.</li>
    <li><code>/login</code>, <code>/register</code> – autenticación y registro.</li>
</ul>

<h3>Páginas de usuario (requiere autenticación)</h3>
<ul>
    <li><code>/myProjects</code> – proyectos creados por el usuario logueado.</li>
    <li><code>/createProject</code> – alta de nuevos proyectos.</li>
    <li><code>/supportedProjects</code> – proyectos a los que el usuario ha donado.</li>
    <li><code>/addAdvance</code> – carga de avances/actualizaciones del proyecto.</li>
</ul>

<h3>Páginas de administración (solo admin)</h3>
<ul>
    <li><code>/pendingProjects</code> – cola de proyectos pendientes de aprobación.</li>
    <li><code>/approveProject</code>, <code>/rejectProject</code> – acciones de moderación.</li>
    <li><code>/disableComment</code> – deshabilitar comentarios inapropiados.</li>
</ul>

<hr>

<h2>🚀 Puesta en Marcha (Setup Local)</h2>

<h3>1. Requisitos</h3>
<ul>
    <li><strong>JDK</strong> (por ejemplo Java 17+).</li>
    <li><strong>Maven</strong> instalado y en el <code>PATH</code>.</li>
    <li><strong>MySQL</strong> en ejecución (local o remoto).</li>
    <li><strong>Apache Tomcat</strong> (u otro contenedor de Servlets compatible).</li>
    <li>Cuenta de <strong>Stripe</strong> y clave de prueba (test key).</li>
</ul>

<h3>2. Configuración</h3>

<ol>
    <li>Clonar el repositorio:
        <pre><code>git clone https://github.com/martin-ratti/ProyectoJava-Crowdfunding.git
</code></pre>
    </li>
    <li>Crear la base de datos MySQL (por ejemplo <code>impulsame</code>) e importar el esquema/tablas según los scripts o documentación del proyecto.</li>
    <li>Configurar <code>database.properties</code> con el JDBC URL, usuario y contraseña de tu instancia MySQL.</li>
    <li>Configurar <code>config.properties</code> con:
        <ul>
            <li><code>upload.dir</code> apuntando a una carpeta válida para subir imágenes.</li>
            <li><code>stripe.secret.key</code> con tu clave de prueba o producción de Stripe.</li>
        </ul>
    </li>
    <li>Construir el proyecto:
        <pre><code>mvn clean package</code></pre>
    </li>
    <li>Desplegar el <code>.war</code> generado en tu instancia de Tomcat (o ejecutar desde tu IDE apuntando al servidor).</li>
    <li>Acceder a la app en el navegador (ejemplo):
        <pre><code>http://localhost:8080</code></pre>
    </li>
</ol>

<p>
    Para comandos específicos, scripts SQL y diagramas, ver la carpeta de documentación en Google Drive:
    <a href="https://drive.google.com/drive/folders/1-iHSWlcJdVT-4DLdjdrMbCkB9aubQ5DZ?usp=sharing" target="_blank">
        Documentación ImpulsaMe
    </a>.
</p>

<hr>

<h2>📚 Documentación Adicional</h2>

<p>La documentación completa del proyecto (en español) incluye:</p>

<ul>
    <li>Diagramas de arquitectura del sistema.</li>
    <li>Modelo de dominio y diagrama entidad-relación.</li>
    <li>Flujos de navegación y diagramas de secuencia.</li>
    <li>Detalles del modelo de seguridad y control de acceso.</li>
    <li>Descripción detallada de endpoints, casos de uso y decisiones de diseño.</li>
</ul>

<p>
    Todo esto está disponible en:
    <a href="https://drive.google.com/drive/folders/1-iHSWlcJdVT-4DLdjdrMbCkB9aubQ5DZ?usp=sharing" target="_blank">
        Google Drive – ImpulsaMe
    </a>.
</p>

<hr>

<h2>👥 Equipo</h2>

<ul>
    <li><strong>Agustín Santinelli</strong> – <a href="https://github.com/agussantinelli" target="_blank">@agussantinelli</a></li>
    <li><strong>Joaquín Peralta</strong> – <a href="https://github.com/juaquin11" target="_blank">@juaquin11</a></li>
    <li><strong>Martín Ratti</strong> – <a href="https://github.com/martin-ratti" target="_blank">@martin-ratti</a></li>
</ul>

<p>Proyecto académico desarrollado en equipo para la UTN FRRO (Catedra Lenguaje de Programación Java).</p>

<hr>

<h2>🤝 Contribuir</h2>

<ol>
    <li>Haz un <strong>fork</strong> del repositorio.</li>
    <li>Crea una rama con el formato <code>feature/...</code> o <code>fix/...</code>.</li>
    <li>Realiza los cambios con buenas prácticas (nombres claros, separación por capas, etc.).</li>
    <li>Incluye, si es posible, tests o ejemplos de uso para la nueva funcionalidad.</li>
    <li>Abre un <strong>Pull Request</strong> describiendo claramente:
        <ul>
            <li>Qué problema resuelve.</li>
            <li>Qué partes del sistema toca (Servlets, DAOs, JSP, etc.).</li>
            <li>Si requiere cambios en configuración o BD.</li>
        </ul>
    </li>
</ol>

<hr>

<h2>⚖️ Licencia</h2>

<p>
    La licencia del proyecto se especifica en el archivo <code>LICENSE</code> de este repositorio.
</p>

<p><em>ImpulsaMe – Plataforma de crowdfunding en Java con moderación, pagos y control de acceso por roles.</em></p>
