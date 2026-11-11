package com.microgrid.controller;

import com.microgrid.dao.TransaccionDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/comprarEnergia")
public class ComprarEnergiaServlet extends HttpServlet {
    private TransaccionDAO transaccionDAO;

    @Override
    public void init() {
        transaccionDAO = new TransaccionDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        int ofertaId = Integer.parseInt(request.getParameter("ofertaId"));
        int compradorId = Integer.parseInt(request.getParameter("compradorId"));

        boolean exito = transaccionDAO.procesarCompra(ofertaId, compradorId);

        if (exito) {
            response.sendRedirect("marketplace?msg=compra_exitosa");
        } else {
            response.sendRedirect("marketplace?error=saldo_insuficiente");
        }
    }
}
