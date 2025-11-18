<!DOCTYPE html>
<html lang="en">
<body>

  <h1>ImpulsaMe – Java Crowdfunding Platform</h1>

  <p>
    <strong>Organization Name:</strong> ImpulsaMe<br />
    <strong>Full project documentation (Spanish):</strong>
    <a href="https://drive.google.com/drive/folders/1-iHSWlcJdVT-4DLdjdrMbCkB9aubQ5DZ?usp=sharing" target="_blank">
      Google Drive – Project Docs
    </a>
  </p>

  <hr />

  <h2>Overview</h2>
  <p>
    <strong>ImpulsaMe</strong> is a web-based crowdfunding platform built with Java Servlets and JSP.
    It enables users to create, browse, and fund projects through donations, with a strong focus on
    moderation, role-based access control, and a clear project lifecycle.
  </p>
  <p>
    This repository contains the source code of the application, including the presentation layer (JSP),
    control layer (Servlets and filters), and data access layer (DAOs backed by PostgreSQL).
  </p>

  <h3>Purpose &amp; Scope</h3>
  <p>
    The goal of ImpulsaMe is to provide a structured environment where:
  </p>
  <ul>
    <li>
      <strong>Project Creators</strong> can submit projects for funding, which enter a pending state
      awaiting admin approval.
    </li>
    <li>
      <strong>Regular Users</strong> can browse active projects, donate money via Stripe, and comment on
      projects they have supported.
    </li>
    <li>
      <strong>Administrators</strong> can moderate content by approving/rejecting projects and disabling
      inappropriate comments.
    </li>
    <li>
      <strong>Anonymous Visitors</strong> can browse active projects without authentication, but cannot
      donate, comment, or create projects.
    </li>
  </ul>
  <p>
    All new projects must be approved by an administrator before they become publicly visible and can
    receive donations.
  </p>

  <p>
    For more detailed aspects of the system, please refer to:
  </p>
  <ul>
    <li><strong>Getting Started:</strong> environment setup, build, and deployment.</li>
    <li><strong>Architecture Overview:</strong> diagrams and deeper explanation of layers and components.</li>
    <li><strong>Authentication &amp; Authorization:</strong> details of login, sessions, and role enforcement.</li>
  </ul>

  <hr />

  <h2>Core Features</h2>

  <table border="1" cellspacing="0" cellpadding="6">
    <thead>
      <tr>
        <th>Feature</th>
        <th>Description</th>
        <th>Key Components</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td><strong>Project Lifecycle Management</strong></td>
        <td>
          Create, moderate, activate, and cancel projects through a controlled state machine
          (<code>Pendiente → Activo → Cancelado/Borrado</code>).
        </td>
        <td>
          <code>CreateProjectServlet</code>, <code>ProyectoDAO</code>, <code>proyecto.estado</code> field
        </td>
      </tr>
      <tr>
        <td><strong>Payment Processing</strong></td>
        <td>
          Donations via Stripe with ARS (Argentine Peso), enforcing a 1000–999999.99 range and preventing
          duplicate submissions.
        </td>
        <td>
          <code>CreateCheckoutSessionServlet</code>, <code>DonacionDAO</code>, Stripe Checkout Session API
        </td>
      </tr>
      <tr>
        <td><strong>Comment System</strong></td>
        <td>
          Donation-gated comments: users can comment only on projects they have donated to. Admins can
          disable comments without deleting them.
        </td>
        <td>
          <code>ComentarioDAO</code>, <code>DisableCommentServlet</code>, <code>comentario.estado</code>
          (<code>Activo</code> / <code>Ignorado</code>)
        </td>
      </tr>
      <tr>
        <td><strong>Project Discovery</strong></td>
        <td>
          Browse and search active projects, with filters by category and country, using dedicated JSP
          pages and server-side queries.
        </td>
        <td>
          <code>ActiveProjectsServlet</code>, <code>active-projects.jsp</code>, search/filter forms
        </td>
      </tr>
      <tr>
        <td><strong>File Management</strong></td>
        <td>
          UUID-based image uploads for project photos. Filenames are stored in the database, and physical
          files go to a configurable upload directory.
        </td>
        <td>
          <code>proyecto.foto</code> (UUID), filesystem <code>uploads/</code>, <code>config.properties</code>
          (<code>upload.dir</code>)
        </td>
      </tr>
      <tr>
        <td><strong>Access Control</strong></td>
        <td>
          Role-based authorization enforced by filters and session-based user identification, with
          restrictions based on database attributes.
        </td>
        <td>
          <code>AuthFilter</code>, HTTP session, role checks in JSPs and Servlets
        </td>
      </tr>
    </tbody>
  </table>

  <hr />

  <h2>Technology Stack</h2>

  <h3>Core Technologies</h3>
  <ul>
    <li><strong>Java EE:</strong> Servlets, JSP, JSTL (Jakarta tags)</li>
    <li><strong>PostgreSQL:</strong> relational database for all persistent data</li>
    <li><strong>Stripe API:</strong> payment gateway for donation processing</li>
    <li><strong>Maven:</strong> project management and build tool</li>
    <li><strong>Tomcat (implied):</strong> servlet container used to run the web application</li>
  </ul>

  <hr />

  <h2>System Architecture at a Glance</h2>
  <p>
    ImpulsaMe follows a classic three-tier architecture, with a clear separation between presentation,
    control, and data access responsibilities.
  </p>

  <h3>Tier Responsibilities</h3>
  <ul>
    <li>
      <strong>Presentation Layer (JSP):</strong> renders HTML views, displays data to users, and contains
      forms for user input (e.g., <code>active-projects.jsp</code>, <code>project-details.jsp</code>).
    </li>
    <li>
      <strong>Control Layer (Servlets):</strong> processes HTTP requests, enforces business rules, and
      coordinates DAOs (e.g., <code>ActiveProjectsServlet</code>, <code>CreateProjectServlet</code>,
      payment-related servlets, and filters like <code>AuthFilter</code>).
    </li>
    <li>
      <strong>Data Access Layer (DAOs):</strong> encapsulates SQL queries and maps database rows to Java
      objects (e.g., <code>ProyectoDAO</code>, <code>DonacionDAO</code>, <code>ComentarioDAO</code>).
    </li>
  </ul>

  <p>
    For a more detailed view of request routing, filters, and controller interactions, refer to the
    <em>Request Flow &amp; Routing</em> section in the project documentation.
  </p>

  <hr />

  <h2>Core Domain Entities</h2>
  <p>
    The main entities of the system are modeled in the database and reflected in the DAOs and domain
    objects. Some notable fields and conventions:
  </p>
  <ul>
    <li>
      <strong><code>usuario.telefono</code>:</strong> used to distinguish roles. A <code>NULL</code> value
      indicates an admin user; a non-null value indicates a regular user.
    </li>
    <li>
      <strong><code>proyecto.estado</code>:</strong> controls the project lifecycle and visibility
      (e.g., <code>Pendiente</code>, <code>Activo</code>, <code>Cancelado</code>, <code>Borrado</code>).
    </li>
    <li>
      <strong><code>proyecto.foto</code>:</strong> stores a UUID filename, while the actual images are
      stored under <code>webapp/uploads/</code>.
    </li>
    <li>
      <strong><code>comentario.estado</code>:</strong> implements a soft-delete pattern. The
      <code>Ignorado</code> state hides comments without removing them from the database.
    </li>
    <li>
      <strong><code>cancelacion_proyecto</code>:</strong> optional table that stores cancellation
      information when a project is cancelled by its owner.
    </li>
  </ul>

  <hr />

  <h2>User Roles &amp; Access Patterns</h2>
  <p>
    The system distinguishes four user types based on authentication state and database attributes:
  </p>

  <table border="1" cellspacing="0" cellpadding="6">
    <thead>
      <tr>
        <th>Role</th>
        <th>Identification</th>
        <th>Can Do</th>
        <th>Cannot Do</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td><strong>Anonymous</strong></td>
        <td>No session</td>
        <td>
          <ul>
            <li>Browse active projects</li>
            <li>View project details</li>
          </ul>
        </td>
        <td>
          <ul>
            <li>Donate</li>
            <li>Comment</li>
            <li>Create projects</li>
          </ul>
        </td>
      </tr>
      <tr>
        <td><strong>Regular User</strong></td>
        <td><code>telefono != NULL</code></td>
        <td>
          <ul>
            <li>Create projects</li>
            <li>Donate to active projects</li>
            <li>Comment on projects they have donated to</li>
          </ul>
        </td>
        <td>
          <ul>
            <li>Approve or reject projects</li>
            <li>Disable comments</li>
          </ul>
        </td>
      </tr>
      <tr>
        <td><strong>Admin</strong></td>
        <td><code>telefono == NULL</code></td>
        <td>
          <ul>
            <li>Approve/reject pending projects</li>
            <li>Disable comments</li>
            <li>Moderate the platform</li>
          </ul>
        </td>
        <td>
          <ul>
            <li>Create projects</li>
            <li>Donate to projects</li>
          </ul>
        </td>
      </tr>
      <tr>
        <td><strong>Project Owner</strong></td>
        <td><code>idUsuario == idCreador</code></td>
        <td>
          <ul>
            <li>Add advances/updates to their own projects</li>
            <li>Cancel their own active projects</li>
          </ul>
        </td>
        <td>
          <ul>
            <li>Donate to their own project</li>
            <li>Comment on their own project</li>
          </ul>
        </td>
      </tr>
    </tbody>
  </table>

  <p><strong>Notable business rules:</strong></p>
  <ul>
    <li>Users must donate to a project before they can comment on it.</li>
    <li>Admins cannot create projects or donate.</li>
    <li>Project owners cannot donate to or comment on their own projects.</li>
    <li>Only active projects can receive donations.</li>
  </ul>

  <hr />

  <h2>Project Workflow Overview</h2>
  <p>
    Projects are governed by a state machine implemented via the <code>proyecto.estado</code> field.
    Typical lifecycle:
  </p>
  <ol>
    <li>
      <strong>Creation:</strong> A new project is submitted and stored with state
      <code>Pendiente</code>.
    </li>
    <li>
      <strong>Moderation:</strong> Admins review pending projects in the <code>/pendingProjects</code>
      view and can approve or reject them.
    </li>
    <li>
      <strong>Active Fundraising:</strong> Approved projects become <code>Activo</code> and appear in
      <code>/activeProjects</code>, where users can donate.
    </li>
    <li>
      <strong>Cancellation:</strong> Project owners can cancel active projects, recording information in
      <code>cancelacion_proyecto</code> (if applicable).
    </li>
    <li>
      <strong>Deletion:</strong> A soft delete is performed by setting
      <code>estado = 'Borrado'</code>, which filters the project out of standard queries.
    </li>
  </ol>

  <hr />

  <h2>Payment Integration</h2>
  <p>
    Donations are handled via the Stripe Checkout Session API, configured specifically for Argentine
    Pesos (ARS).
  </p>

  <h3>Payment Configuration</h3>
  <ul>
    <li><strong>Currency:</strong> ARS (Argentine Peso)</li>
    <li><strong>Allowed amount range:</strong> 1000 – 999999.99</li>
    <li>
      <strong>API key:</strong> configured in <code>config.properties</code> as
      <code>stripe.secret.key</code>
    </li>
    <li>
      <strong>Duplicate prevention:</strong> a UUID-based <code>paymentAttemptId</code> is used to
      avoid duplicate form submissions.
    </li>
  </ul>

  <hr />

  <h2>Key Configuration Files</h2>
  <p>
    Application behavior and environment-specific settings are centralized in configuration files:
  </p>
  <table border="1" cellspacing="0" cellpadding="6">
    <thead>
      <tr>
        <th>File</th>
        <th>Purpose</th>
        <th>Key Properties / Content</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td><code>config.properties</code></td>
        <td>Application configuration</td>
        <td>
          <ul>
            <li><code>upload.dir</code> – file storage path for project images</li>
            <li><code>stripe.secret.key</code> – Stripe payment API secret key</li>
          </ul>
        </td>
      </tr>
      <tr>
        <td><code>database.properties</code></td>
        <td>Database connection</td>
        <td>
          Typically includes JDBC URL, username, password, and driver class for PostgreSQL.
        </td>
      </tr>
      <tr>
        <td><code>web.xml</code></td>
        <td>Servlet and filter configuration</td>
        <td>
          Contains servlet mappings, URL patterns, filter configuration (e.g. <code>AuthFilter</code>),
          and welcome files.
        </td>
      </tr>
    </tbody>
  </table>

  <hr />

  <h2>Navigation Entry Points</h2>
  <p>
    Users interact with the platform through a set of public, user, and admin pages:
  </p>

  <h3>Public Pages (No Authentication Required)</h3>
  <ul>
    <li><code>/home</code> – landing page with featured content.</li>
    <li><code>/activeProjects</code> – list and filters for all active projects.</li>
    <li><code>/projectDetails</code> – details of a single project.</li>
    <li><code>/login</code>, <code>/register</code> – authentication and sign-up forms.</li>
  </ul>

  <h3>User Pages (Authentication Required)</h3>
  <ul>
    <li><code>/myProjects</code> – dashboard listing projects created by the logged-in user.</li>
    <li><code>/createProject</code> – form to submit a new project for approval.</li>
    <li><code>/supportedProjects</code> – projects to which the user has donated.</li>
    <li><code>/addAdvance</code> – add progress updates/advances to owned projects.</li>
  </ul>

  <h3>Admin Pages (Admin Only)</h3>
  <ul>
    <li><code>/pendingProjects</code> – queue of projects waiting for approval.</li>
    <li><code>/approveProject</code>, <code>/rejectProject</code> – moderation actions.</li>
    <li><code>/disableComment</code> – comment moderation endpoint.</li>
  </ul>

  <hr />

  <h2>Relevant Source Files (Examples)</h2>
  <p>
    Some of the most relevant source files and descriptors mentioned in the documentation:
  </p>
  <ul>
    <li><code>ActiveProjectsServlet</code> – controller for listing and filtering active projects.</li>
    <li><code>active-projects.jsp</code> – main view for project discovery.</li>
    <li><code>project-details.jsp</code> – project detail page, donation and comments logic.</li>
    <li><code>CreateProjectServlet</code> – project creation workflow.</li>
    <li><code>CreateCheckoutSessionServlet</code> – Stripe checkout integration.</li>
    <li><code>ProyectoDAO</code>, <code>DonacionDAO</code>, <code>ComentarioDAO</code> – data access layer.</li>
    <li><code>AuthFilter</code> – role-based access and session enforcement.</li>
  </ul>

  <hr />

  <h2>Getting Started (High-Level)</h2>
  <p>
    Detailed setup instructions are available in the project documentation (Google Drive link at the top).
    A typical high-level setup flow looks like:
  </p>
  <ol>
    <li>Configure PostgreSQL and create the database/schema as documented.</li>
    <li>Update <code>database.properties</code> and <code>config.properties</code> with your environment values.</li>
    <li>Provide valid <code>stripe.secret.key</code> for payment processing (or use test keys).</li>
    <li>Build the project with Maven.</li>
    <li>Deploy the generated <code>.war</code> (or equivalent) to a servlet container such as Tomcat.</li>
    <li>Access the application via the configured context path (e.g. <code>http://localhost:8080/impulsame</code>).</li>
  </ol>

  <p>
    For precise commands, environment variables, and deployment details, please refer to the
    <strong>Getting Started</strong> section in the full documentation.
  </p>

  <hr />

  <h2>Project Documentation</h2>
  <p>
    All diagrams (system architecture, domain model, request flow, payment integration, state machine, etc.)
    as well as extended documentation (in Spanish) are available at:
  </p>
  <p>
    <a href="https://drive.google.com/drive/folders/1-iHSWlcJdVT-4DLdjdrMbCkB9aubQ5DZ?usp=sharing" target="_blank">
      https://drive.google.com/drive/folders/1-iHSWlcJdVT-4DLdjdrMbCkB9aubQ5DZ?usp=sharing
    </a>
  </p>

  <hr />

  <p><em>ImpulsaMe – Crowdfunding platform built with Java Servlets, JSP, PostgreSQL, and Stripe.</em></p>

</body>
</html>
