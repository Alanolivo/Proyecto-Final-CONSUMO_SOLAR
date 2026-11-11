package com.microgrid.controller;

import com.microgrid.dao.UsuarioDAO;
import com.microgrid.model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/registrarUsuario")
public class RegistrarUsuarioServlet extends HttpServlet {
    private UsuarioDAO usuarioDAO;

    @Override
    public void init() {
        usuarioDAO = new UsuarioDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);
        Usuario logueado = (session != null) ? (Usuario) session.getAttribute("usuarioLogueado") : null;

        if (logueado == null || !"ADMIN".equals(logueado.getRol())) {
            response.sendRedirect("login");
            return;
        }

        String nombre = request.getParameter("nombre");
        String direccionNodo = request.getParameter("direccionNodo");
        String rol = request.getParameter("rol");
        double capacidadSolarKw = Double.parseDouble(request.getParameter("capacidadSolarKw"));
        double saldoInicial = Double.parseDouble(request.getParameter("saldoInicial"));
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Usuario nuevo = new Usuario();
        nuevo.setNombre(nombre);
        nuevo.setDireccionNodo(direccionNodo);
        nuevo.setRol(rol);
        nuevo.setCapacidadSolarKw("PROSUMIDOR".equals(rol) ? capacidadSolarKw : 0.0);
        nuevo.setSaldoCreditos(saldoInicial);
        nuevo.setEmail(email);
        nuevo.setPassword(password);

        boolean exito = usuarioDAO.registrarUsuario(nuevo);

        if (exito) {
            response.sendRedirect("adminDashboard?msg=usuario_registrado");
        } else {
            response.sendRedirect("adminDashboard?error=error_registro");
        }
    }
}
