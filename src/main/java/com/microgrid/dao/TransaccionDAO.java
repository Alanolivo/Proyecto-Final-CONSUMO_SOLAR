package com.microgrid.dao;

import com.microgrid.model.OfertaEnergia;
import com.microgrid.model.Transaccion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransaccionDAO {

    public boolean procesarCompra(int ofertaId, int compradorId) {
        Connection con = null;
        try {
            con = ConexionBD.getConexion();
            con.setAutoCommit(false);

            OfertaDAO ofertaDAO = new OfertaDAO();
            OfertaEnergia oferta = ofertaDAO.obtenerPorId(ofertaId, con);

            if (oferta == null || !"ABIERTA".equals(oferta.getEstado())) {
                con.rollback();
                return false;
            }

            double totalCreditos = oferta.getKwhDisponible() * oferta.getPrecioPorKwh();

            String checkSaldo = "SELECT saldo_creditos FROM usuarios WHERE id = ?";
            PreparedStatement psSaldo = con.prepareStatement(checkSaldo);
            psSaldo.setInt(1, compradorId);
            ResultSet rsSaldo = psSaldo.executeQuery();

            if (!rsSaldo.next() || rsSaldo.getDouble("saldo_creditos") < totalCreditos) {
                con.rollback();
                return false;
            }

            String updateComprador = "UPDATE usuarios SET saldo_creditos = saldo_creditos - ? WHERE id = ?";
            PreparedStatement psComp = con.prepareStatement(updateComprador);
            psComp.setDouble(1, totalCreditos);
            psComp.setInt(2, compradorId);
            psComp.executeUpdate();

            String updateVendedor = "UPDATE usuarios SET saldo_creditos = saldo_creditos + ? WHERE id = ?";
            PreparedStatement psVend = con.prepareStatement(updateVendedor);
            psVend.setDouble(1, totalCreditos);
            psVend.setInt(2, oferta.getProsumidorId());
            psVend.executeUpdate();

            String updateOferta = "UPDATE ofertas_energia SET estado = 'COMPLETADA' WHERE id = ?";
            PreparedStatement psOf = con.prepareStatement(updateOferta);
            psOf.setInt(1, ofertaId);
            psOf.executeUpdate();

            String insertTx = "INSERT INTO transacciones (comprador_id, vendedor_id, kwh_transferidos, total_creditos) VALUES (?, ?, ?, ?)";
            PreparedStatement psTx = con.prepareStatement(insertTx);
            psTx.setInt(1, compradorId);
            psTx.setInt(2, oferta.getProsumidorId());
            psTx.setDouble(3, oferta.getKwhDisponible());
            psTx.setDouble(4, totalCreditos);
            psTx.executeUpdate();

            con.commit();
            return true;
        } catch (SQLException e) {
            if (con != null) {
                try { con.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
            e.printStackTrace();
            return false;
        } finally {
            if (con != null) {
                try { con.setAutoCommit(true); con.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
        }
    }

    public List<Transaccion> listarHistorial() {
        List<Transaccion> lista = new ArrayList<>();
        String sql = "SELECT t.*, u1.nombre AS comprador_nombre, u2.nombre AS vendedor_nombre " +
                     "FROM transacciones t " +
                     "JOIN usuarios u1 ON t.comprador_id = u1.id " +
                     "JOIN usuarios u2 ON t.vendedor_id = u2.id " +
                     "ORDER BY t.fecha DESC";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Transaccion(
                    rs.getInt("id"),
                    rs.getInt("comprador_id"),
                    rs.getInt("vendedor_id"),
                    rs.getString("comprador_nombre"),
                    rs.getString("vendedor_nombre"),
                    rs.getDouble("kwh_transferidos"),
                    rs.getDouble("total_creditos"),
                    rs.getTimestamp("fecha")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
