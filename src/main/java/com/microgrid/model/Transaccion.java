package com.microgrid.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Transaccion implements Serializable {
    private int id;
    private int compradorId;
    private int vendedorId;
    private String nombreComprador;
    private String nombreVendedor;
    private double kwhTransferidos;
    private double totalCreditos;
    private Timestamp fecha;

    public Transaccion() {}

    public Transaccion(int id, int compradorId, int vendedorId, String nombreComprador, String nombreVendedor, double kwhTransferidos, double totalCreditos, Timestamp fecha) {
        this.id = id;
        this.compradorId = compradorId;
        this.vendedorId = vendedorId;
        this.nombreComprador = nombreComprador;
        this.nombreVendedor = nombreVendedor;
        this.kwhTransferidos = kwhTransferidos;
        this.totalCreditos = totalCreditos;
        this.fecha = fecha;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getCompradorId() { return compradorId; }
    public void setCompradorId(int compradorId) { this.compradorId = compradorId; }
    public int getVendedorId() { return vendedorId; }
    public void setVendedorId(int vendedorId) { this.vendedorId = vendedorId; }
    public String getNombreComprador() { return nombreComprador; }
    public void setNombreComprador(String nombreComprador) { this.nombreComprador = nombreComprador; }
    public String getNombreVendedor() { return nombreVendedor; }
    public void setNombreVendedor(String nombreVendedor) { this.nombreVendedor = nombreVendedor; }
    public double getKwhTransferidos() { return kwhTransferidos; }
    public void setKwhTransferidos(double kwhTransferidos) { this.kwhTransferidos = kwhTransferidos; }
    public double getTotalCreditos() { return totalCreditos; }
    public void setTotalCreditos(double totalCreditos) { this.totalCreditos = totalCreditos; }
    public Timestamp getFecha() { return fecha; }
    public void setFecha(Timestamp fecha) { this.fecha = fecha; }
}
