<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <!DOCTYPE html>
    <html lang="es">

    <head>
        <meta charset="UTF-8">
        <title>Iniciar Sesión - Microgrid</title>
        <link rel="stylesheet" href="css/styles.css">
    </head>

    <body style="display:flex; justify-content:center; align-items:center; height:100vh;">
        <div class="card" style="width: 380px;">
            <h2 style="text-align:center;">Microred Solar <span style="color:var(--accent-emerald);">P2P</span></h2>
            <p style="text-align:center; color:var(--text-muted);">Acceso a la Microred Solar</p>

            <% String error=(String) request.getAttribute("errorLogin"); if(error !=null) { %>
                <div class="alert alert-error"
                    style="background:rgba(239,68,68,0.2); color:#ef4444; padding:0.5rem; border-radius:4px; font-size:0.85rem; margin-bottom:1rem;">
                    <%= error %>
                </div>
                <% } %>

                    <form action="login" method="POST">
                        <div class="form-group">
                            <label>Correo Electrónico:</label>
                            <input type="email" name="email" required placeholder="admin@microgrid.com">
                        </div>

                        <div class="form-group">
                            <label>Contraseña:</label>
                            <input type="password" name="password" required placeholder="••••••••">
                        </div>

                        <button type="submit" class="btn btn-primary" style="width:100%; margin-top:1rem;">Ingresar al
                            Sistema</button>
                    </form>

                    <div style="margin-top:1.5rem; font-size:0.8rem; color:var(--text-muted); text-align:center;">
                        <strong>Cuentas Demo:</strong><br>
                        • Admin: admin@microgrid.com / admin123<br>
                        • Prosumidor: alan@microgrid.com / 123<br>
                        • Consumidor: perez@microgrid.com / 123
                    </div>
        </div>
    </body>

    </html>