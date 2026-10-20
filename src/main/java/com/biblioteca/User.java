package com.biblioteca;

public class User {
    private String id;
    private String nombre;
    private String apellidos;
    private String email;
    private String telefono;
    private String direccion;
    private String fechaRegistro;
    private String estado;

    // Constructor con los 8 parámetros
    public User(String id, String nombre, String apellidos, String email, String telefono, String direccion, String fechaRegistro, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getApellidos() { return apellidos; }
    public String getEmail() { return email; }
    public String getTelefono() { return telefono; }
    public String getDireccion() { return direccion; }
    public String getFechaRegistro() { return fechaRegistro; }
    public String getEstado() { return estado; }
}