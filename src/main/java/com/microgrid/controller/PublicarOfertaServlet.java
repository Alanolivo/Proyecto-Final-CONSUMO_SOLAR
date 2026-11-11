package com.microgrid.controller;

import com.microgrid.dao.OfertaDAO;
import com.microgrid.model.OfertaEnergia;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/publicarOferta")
public class PublicarOfertaServlet extends HttpServlet {
    private OfertaDAO ofertaDAO;

    @Override
    public void init() {
        ofertaDAO = new OfertaDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        int prosumidorId = Integer.parseInt(request.getParameter("prosumidorId"));
        double kwhDisponible = Double.parseDouble(request.getParameter("kwhDisponible"));
        double precioPorKwh = Double.parseDouble(request.getParameter("precioPorKwh"));
        String tipoOferta = request.getParameter("tipoOferta");

        OfertaEnergia nuevaOferta = new OfertaEnergia();
        nuevaOferta.setProsumidorId(prosumidorId);
        nuevaOferta.setKwhDisponible(kwhDisponible);
        nuevaOferta.setPrecioPorKwh(tipoOferta.equals("CESION_GRATUITA") ? 0.0 : precioPorKwh);
        nuevaOferta.setTipoOferta(tipoOferta);

        boolean exito = ofertaDAO.insertarOferta(nuevaOferta);

        if (exito) {
            response.sendRedirect("marketplace?msg=publicado");
        } else {
            response.sendRedirect("marketplace?error=error_publicacion");
        }
    }
}
