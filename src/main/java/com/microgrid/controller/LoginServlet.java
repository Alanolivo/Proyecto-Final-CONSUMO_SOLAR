package com.microgrid.controller;

import com.microgrid.dao.UsuarioDAO;
import com.microgrid.model.Usuario;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UsuarioDAO usuarioDAO;

    @Override
    public void init() {
        usuarioDAO = new UsuarioDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Usuario usuario = usuarioDAO.validarLogin(email, password);

        if (usuario != null) {
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogueado", usuario);

            if ("ADMIN".equals(usuario.getRol())) {
                response.sendRedirect("adminDashboard");
            } else {
                response.sendRedirect("index.jsp");
            }
        } else {
            request.setAttribute("errorLogin", "Credenciales invalidas. Verifica tu correo y contrasena.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
