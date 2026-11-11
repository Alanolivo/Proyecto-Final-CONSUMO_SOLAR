package com.microgrid.controller;

import com.microgrid.dao.OfertaDAO;
import com.microgrid.model.OfertaEnergia;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/marketplace")
public class MarketplaceServlet extends HttpServlet {
    private OfertaDAO ofertaDAO;

    @Override
    public void init() {
        ofertaDAO = new OfertaDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<OfertaEnergia> ofertas = ofertaDAO.listarOfertasAbiertas();
        request.setAttribute("listaOfertas", ofertas);
        request.getRequestDispatcher("marketplace.jsp").forward(request, response);
    }
}
