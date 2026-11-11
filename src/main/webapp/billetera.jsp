<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.microgrid.dao.TransaccionDAO" %>
<%@ page import="com.microgrid.model.Transaccion" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Billetera de Créditos - OLIVO P2P</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <header class="navbar">
        <div class="brand">⚡ OLIVO <span>Microgrid P2P</span></div>
        <nav>
            <a href="index.jsp">Dashboard</a>
            <a href="marketplace">Marketplace</a>
            <a href="billetera.jsp" class="active">Billetera SolCoins</a>
        </nav>
    </header>

    <main class="container">
        <h1>Mi Billetera Digital de Créditos Energéticos</h1>

        <div class="wallet-banner">
            <div class="balance-block">
                <span>Saldo Disponible</span>
                <h2>145.80 <small>SolCoins</small></h2>
                <p>≈ 1,215 kWh de respaldo comunitario</p>
            </div>
            <div class="carbon-block">
                <span>Impacto Ecológico Acumulado</span>
                <h2>342.5 kg</h2>
                <p>CO₂ evitado este mes en la microred</p>
            </div>
        </div>

        <section class="section-box">
            <h2>Historial Contable Inmutable (Microgrid Ledger)</h2>
            <table class="data-table">
                <thead>
                    <tr>
                        <th>ID Tx</th>
                        <th>Origen (Vendedor/Cedente)</th>
                        <th>Destino (Comprador)</th>
                        <th>Energía (kWh)</th>
                        <th>Total Créditos</th>
                        <th>Fecha & Hora</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        TransaccionDAO dao = new TransaccionDAO();
                        List<Transaccion> txs = dao.listarHistorial();
                        if (txs != null && !txs.isEmpty()) {
                            for(Transaccion t : txs) {
                    %>
                    <tr>
                        <td>#TX-00<%= t.getId() %></td>
                        <td><%= t.getNombreVendedor() %></td>
                        <td><%= t.getNombreComprador() %></td>
                        <td><strong><%= t.getKwhTransferidos() %> kWh</strong></td>
                        <td><%= t.getTotalCreditos() %> SolCoins</td>
                        <td><%= t.getFecha() %></td>
                    </tr>
                    <%      }
                        } else {
                    %>
                    <tr>
                        <td colspan="6" style="text-align:center;">No hay transacciones registradas en el libro contable.</td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </section>
    </main>
</body>
</html>
