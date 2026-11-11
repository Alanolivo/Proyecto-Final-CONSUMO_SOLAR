<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ page import="java.util.List" %>
        <%@ page import="com.microgrid.model.Usuario" %>
            <%@ page import="com.microgrid.model.Transaccion" %>
                <!DOCTYPE html>
                <html lang="es">

                <head>
                    <meta charset="UTF-8">
                    <title>Panel de Administración - Microred</title>
                    <link rel="stylesheet" href="css/styles.css">
                </head>

                <body>
                    <header class="navbar">
                        <div class="brand">⚡ OLIVO <span>Microgrid (ADMIN)</span></div>
                        <nav>
                            <a href="adminDashboard" class="active">Panel Admin</a>
                            <a href="index.jsp">Vista Red</a>
                            <a href="marketplace">Marketplace</a>
                            <a href="logout" style="color:#ef4444;">Cerrar Sesión</a>
                        </nav>
                    </header>

                    <main class="container">
                        <div class="header-action">
                            <h1>Administración Central de Nodos y Energía</h1>
                            <button class="btn btn-primary"
                                onclick="document.getElementById('modalNuevoUsuario').style.display='block'">+ Registrar
                                Nuevo Vecino / Nodo</button>
                        </div>

                        <div class="metrics-grid" style="margin-top:1.5rem;">
                            <div class="card">
                                <h3>Capacidad Solar Instalada</h3>
                                <div class="metric-val text-amber">
                                    <%= request.getAttribute("capacidadTotalSolar") %> kW
                                </div>
                                <p>Generación potencial comunitaria</p>
                            </div>
                            <div class="card">
                                <h3>Nodos Prosumidores</h3>
                                <div class="metric-val text-emerald">
                                    <%= request.getAttribute("prosumidoresCount") %> Vecinos
                                </div>
                                <p>Generadores con excedente</p>
                            </div>
                            <div class="card">
                                <h3>Nodos Consumidores</h3>
                                <div class="metric-val text-blue">
                                    <%= request.getAttribute("consumidoresCount") %> Vecinos
                                </div>
                                <p>Compradores activos</p>
                            </div>
                        </div>

                        <section class="section-box">
                            <h2>Gestión de Nodos Vecinales</h2>
                            <table class="data-table">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Nombre / Vecino</th>
                                        <th>Dirección de Nodo</th>
                                        <th>Rol</th>
                                        <th>Capacidad Solar</th>
                                        <th>Saldo SolCoins</th>
                                        <th>Email</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
                                            if (usuarios != null) {
                                            for (Usuario u : usuarios) {
                                            %>
                                            <tr>
                                                <td>#<%= u.getId() %>
                                                </td>
                                                <td><strong>
                                                        <%= u.getNombre() %>
                                                    </strong></td>
                                                <td>
                                                    <%= u.getDireccionNodo() %>
                                                </td>
                                                <td>
                                                    <span class="badge <%= " ADMIN".equals(u.getRol()) ? "badge-free" :
                                                        ("PROSUMIDOR".equals(u.getRol()) ? "badge-prosumer"
                                                        : "badge-consumer" ) %>">
                                                        <%= u.getRol() %>
                                                    </span>
                                                </td>
                                                <td>
                                                    <%= u.getCapacidadSolarKw() %> kW
                                                </td>
                                                <td><strong class="text-emerald">
                                                        <%= u.getSaldoCreditos() %>
                                                    </strong></td>
                                                <td>
                                                    <%= u.getEmail() %>
                                                </td>
                                            </tr>
                                            <% } } %>
                                </tbody>
                            </table>
                        </section>
                    </main>

                    <!-- Modal Registrar Usuario -->
                    <div id="modalNuevoUsuario" class="modal">
                        <div class="modal-content">
                            <span class="close"
                                onclick="document.getElementById('modalNuevoUsuario').style.display='none'">&times;</span>
                            <h2>Alta de Nuevo Nodo Vecinal</h2>
                            <form action="registrarUsuario" method="POST">
                                <div class="form-group">
                                    <label>Nombre del Vecino / Titular:</label>
                                    <input type="text" name="nombre" required placeholder="Ej. Ana Torres">
                                </div>

                                <div class="form-group">
                                    <label>Dirección del Nodo:</label>
                                    <input type="text" name="direccionNodo" required
                                        placeholder="Ej. Nodo-C1 (Casa 105)">
                                </div>

                                <div class="form-group">
                                    <label>Rol en la Red:</label>
                                    <select name="rol" id="rolSelect" onchange="toggleSolarCap()">
                                        <option value="CONSUMIDOR">CONSUMIDOR (Solo compra energía)</option>
                                        <option value="PROSUMIDOR">PROSUMIDOR (Genera y vende energía solar)</option>
                                    </select>
                                </div>

                                <div class="form-group" id="solarGroup" style="display:none;">
                                    <label>Capacidad Solar Instalada (kW):</label>
                                    <input type="number" step="0.1" name="capacidadSolarKw" value="0.0">
                                </div>

                                <div class="form-group">
                                    <label>Saldo Inicial (SolCoins):</label>
                                    <input type="number" step="0.1" name="saldoInicial" value="50.0">
                                </div>

                                <div class="form-group">
                                    <label>Correo Electrónico (Login):</label>
                                    <input type="email" name="email" required placeholder="ana@microgrid.com">
                                </div>

                                <div class="form-group">
                                    <label>Contraseña Acceso:</label>
                                    <input type="password" name="password" required value="123">
                                </div>

                                <button type="submit" class="btn btn-primary" style="width:100%;">Registrar Nodo en la
                                    Microred</button>
                            </form>
                        </div>
                    </div>

                    <script>
                        function toggleSolarCap() {
                            var val = document.getElementById("rolSelect").value;
                            document.getElementById("solarGroup").style.display = (val === 'PROSUMIDOR') ? 'block' : 'none';
                        }
                    </script>
                </body>

                </html>