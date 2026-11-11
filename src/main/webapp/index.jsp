<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>OLIVO Microgrid P2P - Dashboard</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <header class="navbar">
        <div class="brand">⚡ OLIVO <span>Microgrid P2P</span></div>
        <nav>
            <a href="index.jsp" class="active">Dashboard</a>
            <a href="marketplace">Marketplace</a>
            <a href="billetera.jsp">Billetera SolCoins</a>
        </nav>
    </header>

    <main class="container">
        <h1>Estado en Tiempo Real de la Microred Vecinal</h1>
        
        <div class="metrics-grid">
            <div class="card">
                <h3>Generación Solar Total</h3>
                <div class="metric-val text-amber">42.8 kWh</div>
                <p>Radiación ideal (12:30 PM)</p>
            </div>
            <div class="card">
                <h3>Consumo Vecinal</h3>
                <div class="metric-val text-blue">28.4 kWh</div>
                <p>Demanda moderada</p>
            </div>
            <div class="card">
                <h3>Excedente P2P Circulante</h3>
                <div class="metric-val text-emerald">14.4 kWh</div>
                <p>Disponible para transferencia</p>
            </div>
            <div class="card">
                <h3>Batería Comunal</h3>
                <div class="metric-val text-purple">88%</div>
                <p>12.5 / 15.0 kWh almacenados</p>
            </div>
        </div>

        <section class="section-box">
            <h2>Mapa de Nodos Residenciales (Vecinos)</h2>
            <div class="nodes-grid">
                <div class="node-card prosumer">
                    <h4>Casa 101 (Alan Olivo)</h4>
                    <span class="badge badge-prosumer">Prosumidor</span>
                    <p>Producción: <strong>5.4 kW</strong> | Consumo: <strong>1.2 kW</strong></p>
                    <p class="status-text text-emerald">Excedente: +4.2 kW (Ofreciendo P2P)</p>
                </div>
                <div class="node-card consumer">
                    <h4>Casa 102 (Familia Pérez)</h4>
                    <span class="badge badge-consumer">Consumidor</span>
                    <p>Producción: <strong>0.0 kW</strong> | Consumo: <strong>2.8 kW</strong></p>
                    <p class="status-text text-blue">Comprando Créditos P2P</p>
                </div>
                <div class="node-card prosumer">
                    <h4>Casa 103 (Carlos M.)</h4>
                    <span class="badge badge-prosumer">Prosumidor</span>
                    <p>Producción: <strong>4.1 kW</strong> | Consumo: <strong>1.5 kW</strong></p>
                    <p class="status-text text-emerald">Excedente: +2.6 kW</p>
                </div>
            </div>
        </section>
    </main>
</body>
</html>
