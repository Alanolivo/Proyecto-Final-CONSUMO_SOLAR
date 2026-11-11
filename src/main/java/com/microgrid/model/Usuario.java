package com.microgrid.model;

import java.io.Serializable;

public class Usuario implements Serializable {
    private int id;
    private String nombre;
    private String direccionNodo;
    private String rol;
    private double saldoCreditos;
    private double capacidadSolarKw;
    private String email;
    private String password;

    public Usuario() {}

    public Usuario(int id, String nombre, String direccionNodo, String rol, double saldoCreditos, double capacidadSolarKw, String email, String password) {
        this.id = id;
        this.nombre = nombre;
        this.direccionNodo = direccionNodo;
        this.rol = rol;
        this.saldoCreditos = saldoCreditos;
        this.capacidadSolarKw = capacidadSolarKw;
        this.email = email;
        this.password = password;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDireccionNodo() { return direccionNodo; }
    public void setDireccionNodo(String direccionNodo) { this.direccionNodo = direccionNodo; }
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
    public double getSaldoCreditos() { return saldoCreditos; }
    public void setSaldoCreditos(double saldoCreditos) { this.saldoCreditos = saldoCreditos; }
    public double getCapacidadSolarKw() { return capacidadSolarKw; }
    public void setCapacidadSolarKw(double capacidadSolarKw) { this.capacidadSolarKw = capacidadSolarKw; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
