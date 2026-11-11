package com.microgrid.controller;

import com.microgrid.dao.TransaccionDAO;
import com.microgrid.dao.UsuarioDAO;
import com.microgrid.model.Transaccion;
import com.microgrid.model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/adminDashboard")
public class AdminDashboardServlet extends HttpServlet {
    private UsuarioDAO usuarioDAO;
    private TransaccionDAO transaccionDAO;

    @Override
    public void init() {
        usuarioDAO = new UsuarioDAO();
        transaccionDAO = new TransaccionDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Usuario logueado = (session != null) ? (Usuario) session.getAttribute("usuarioLogueado") : null;

        if (logueado == null || !"ADMIN".equals(logueado.getRol())) {
            response.sendRedirect("login");
            return;
        }

        List<Usuario> listaUsuarios = usuarioDAO.listarTodos();
        List<Transaccion> listaTransacciones = transaccionDAO.listarHistorial();

        double capacidadTotalSolar = 0.0;
        int prosumidoresCount = 0;
        int consumidoresCount = 0;

        for (Usuario u : listaUsuarios) {
            if ("PROSUMIDOR".equals(u.getRol())) {
                capacidadTotalSolar += u.getCapacidadSolarKw();
                prosumidoresCount++;
            } else if ("CONSUMIDOR".equals(u.getRol())) {
                consumidoresCount++;
            }
        }

        request.setAttribute("usuarios", listaUsuarios);
        request.setAttribute("transacciones", listaTransacciones);
        request.setAttribute("capacidadTotalSolar", capacidadTotalSolar);
        request.setAttribute("prosumidoresCount", prosumidoresCount);
        request.setAttribute("consumidoresCount", consumidoresCount);

        request.getRequestDispatcher("admin_dashboard.jsp").forward(request, response);
    }
}
