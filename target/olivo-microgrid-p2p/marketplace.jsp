<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.microgrid.model.OfertaEnergia" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Marketplace de Energía - OLIVO P2P</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <header class="navbar">
        <div class="brand">⚡ OLIVO <span>Microgrid P2P</span></div>
        <nav>
            <a href="index.jsp">Dashboard</a>
            <a href="marketplace" class="active">Marketplace</a>
            <a href="billetera.jsp">Billetera SolCoins</a>
        </nav>
    </header>

    <main class="container">
        <div class="header-action">
            <h1>Libro de Órdenes de Energía Vecinal</h1>
            <button class="btn btn-primary" onclick="document.getElementById('modalOferta').style.display='block'">+ Publicar Mi Excedente</button>
        </div>

        <% String msg = request.getParameter("msg"); 
           if("publicado".equals(msg)) { %>
            <div class="alert alert-success">¡Oferta de energía publicada correctamente en la microred!</div>
        <% } else if("compra_exitosa".equals(msg)) { %>
            <div class="alert alert-success">¡Transferencia de energía y créditos completada con éxito!</div>
        <% } %>

        <table class="data-table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Vecino (Prosumidor)</th>
                    <th>Excedente (kWh)</th>
                    <th>Precio / kWh</th>
                    <th>Tipo</th>
                    <th>Fecha</th>
                    <th>Acción</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<OfertaEnergia> ofertas = (List<OfertaEnergia>) request.getAttribute("listaOfertas");
                    if (ofertas != null && !ofertas.isEmpty()) {
                        for (OfertaEnergia o : ofertas) {
                %>
                <tr>
                    <td>#<%= o.getId() %></td>
                    <td><strong><%= o.getNombreProsumidor() %></strong></td>
                    <td><span class="text-emerald font-bold"><%= o.getKwhDisponible() %> kWh</span></td>
                    <td>
                        <% if("CESION_GRATUITA".equals(o.getTipoOferta())) { %>
                            <span class="badge badge-free">GRATIS (Social)</span>
                        <% } else { %>
                            <strong><%= o.getPrecioPorKwh() %> SolCoins</strong>
                        <% } %>
                    </td>
                    <td><%= o.getTipoOferta() %></td>
                    <td><%= o.getFechaPublicacion() %></td>
                    <td>
                        <form action="comprarEnergia" method="POST" style="display:inline;">
                            <input type="hidden" name="ofertaId" value="<%= o.getId() %>">
                            <input type="hidden" name="compradorId" value="2">
                            <button type="submit" class="btn btn-sm btn-success">Adquirir Energía</button>
                        </form>
                    </td>
                </tr>
                <% 
                        }
                    } else { 
                %>
                <tr>
                    <td colspan="7" style="text-align:center;">No hay ofertas abiertas de energía en este momento.</td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </main>

    <div id="modalOferta" class="modal">
        <div class="modal-content">
            <span class="close" onclick="document.getElementById('modalOferta').style.display='none'">&times;</span>
            <h2>Publicar Excedente Solar</h2>
            <form action="publicarOferta" method="POST">
                <input type="hidden" name="prosumidorId" value="1">
                
                <div class="form-group">
                    <label>Cantidad de Energía Excedente (kWh):</label>
                    <input type="number" step="0.1" name="kwhDisponible" required placeholder="Ej. 3.5">
                </div>

                <div class="form-group">
                    <label>Modalidad de Oferta:</label>
                    <select name="tipoOferta" id="tipoOfertaSelect" onchange="togglePrecioInput()">
                        <option value="VENTA">Venta Comercial (SolCoins)</option>
                        <option value="CESION_GRATUITA">Cesión Gratuita / Donación Vecinal</option>
                    </select>
                </div>

                <div class="form-group" id="precioGroup">
                    <label>Precio por kWh (SolCoins):</label>
                    <input type="number" step="0.01" name="precioPorKwh" value="0.12" placeholder="0.12">
                </div>

                <button type="submit" class="btn btn-primary" style="width:100%;">Publicar Oferta P2P</button>
            </form>
        </div>
    </div>

    <script>
        function togglePrecioInput() {
            var val = document.getElementById("tipoOfertaSelect").value;
            document.getElementById("precioGroup").style.display = (val === 'CESION_GRATUITA') ? 'none' : 'block';
        }
    </script>
</body>
</html>
